// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.utils.Rotation;
import org.photonvision.PhotonCamera;
//hi

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final PhotonCamera photonCamera = new PhotonCamera(Constants.cameraName);

    // The robot's subsystems and commands are defined here...
    private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
    //private final IAprilVisionSubsystem aprilTagVisionSubsystem = new DummyVisionSubsystem();
    //private final IAprilVisionSubsystem aprilTagVisionSubsystem = new PhotonVisionSubsystem(photonCamera);
    private final GyroBalanceSubsystem gyroSubsystem = new GyroBalanceSubsystem();

    private final PoseEstimationSubsystem poseEstimationSubsystem = new PoseEstimationSubsystem(photonCamera, driveTrainSubsystem, gyroSubsystem);

    private final ClawSubsystem clawSubsystem = new ClawSubsystem();

    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final PhotonColorVisionSubsystem photonColorVisionSubsystem = new PhotonColorVisionSubsystem(photonCamera);
    private final ColorSubsystem colorSubsystem = new ColorSubsystem(photonColorVisionSubsystem);
    private final SendableChooser<StartingPositions> startPosChooser = new SendableChooser<StartingPositions>();


    private final StabilizePitchRollCommand stabilizePitchRollCommand = new StabilizePitchRollCommand(gyroSubsystem, driveTrainSubsystem);

    private final DriveToPoseCommand driveToPoseCommand = new DriveToPoseCommand(driveTrainSubsystem, poseEstimationSubsystem,
            new Pose2d(
                    new Translation2d(10.0, 3.0),
                    new Rotation2d(10)
            )
    );

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController controller =
            new CommandXboxController(OperatorConstants.kDriverControllerPort);

    double deadZone(double control) {
        if (Math.abs(control) < Constants.deadzone) {
            return 0.0;
        }
        return control;
    }


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {


        // Configure the trigger bindings
        configureBindings();
        driveTrainSubsystem.setDefaultCommand(
                new MecanumDriveCommand(
                        driveTrainSubsystem,
                        () -> {
                            var input = -controller.getLeftX(); //put negative here to change polarity
                            if (controller.getHID().getBButton()) {
                                input = input / 4;
                            }
                            SmartDashboard.putNumber("strafing", input);
                            return deadZone(input);
                        },
                        () -> {
                            var input = -controller.getLeftY();
                            if (controller.getHID().getBButton()) {
                                input = input / 4;
                            }
                            SmartDashboard.putNumber("forwards", input);
                            return deadZone(input);
                        },
                        () -> {
                            var input = controller.getRightX();
                            if (controller.getHID().getBButton()) {
                                input = input / 4;
                            }
                            SmartDashboard.putNumber("rotation", input);
                            return deadZone(input);
                        }
                )
        );


        for (StartingPositions p : StartingPositions.values()) {
            startPosChooser.addOption(p.name(), p);
        }
        startPosChooser.setDefaultOption(StartingPositions.A.name(), StartingPositions.A);
        SmartDashboard.putData(startPosChooser);
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
//    new Trigger(m_exampleSubsystem::exampleCondition)
//        .onTrue(new ExampleCommand(m_exampleSubsystem));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
        // cancelling on release.
//    controller.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

        controller.x().whileTrue(stabilizePitchRollCommand);
        controller.y().whileTrue(driveToPoseCommand);


    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        //Getting smart-dashboard value
      StartingPositions startPos = startPosChooser.getSelected();
      SmartDashboard.putString("Current Auto Start Config",startPos.name());
       if (startPos == StartingPositions.A){
            return new SequentialCommandGroup(
                    new ArmMovementCommand(armSubsystem,ArmPosition.HIGH,driveTrainSubsystem),
                    new ClawCommand(clawSubsystem, colorSubsystem, false),
                    new ArmMovementCommand(armSubsystem, ArmPosition.GROUND, driveTrainSubsystem),
                    new DistanceDriveCommand(driveTrainSubsystem, -100000.0),
                    new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(180).toRadians()),
                    new ArmMovementCommand(armSubsystem, ArmPosition.MIDDLE, driveTrainSubsystem),
                    new ClawCommand(clawSubsystem, colorSubsystem, true),
                    new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(180).toRadians()),
                    new DistanceDriveCommand(driveTrainSubsystem, 100000.0),
                    new ArmMovementCommand(armSubsystem, ArmPosition.GROUND, driveTrainSubsystem),
                    new ClawCommand(clawSubsystem, colorSubsystem, false)

            );

       } else if (startPos == StartingPositions.B){
           return new SequentialCommandGroup();

       } else if (startPos == StartingPositions.C){
           return new SequentialCommandGroup();

       } else if (startPos == StartingPositions.D){
           return new SequentialCommandGroup();

       }
        return new SequentialCommandGroup();
    }
}
