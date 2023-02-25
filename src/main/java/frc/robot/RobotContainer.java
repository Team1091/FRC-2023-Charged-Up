// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoStartCommands.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
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
    private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
    //private final IAprilVisionSubsystem aprilTagVisionSubsystem = new DummyVisionSubsystem();
    //private final IAprilVisionSubsystem aprilTagVisionSubsystem = new PhotonVisionSubsystem(photonCamera);
    private final GyroBalanceSubsystem gyroSubsystem = new GyroBalanceSubsystem();
    private final PoseEstimationSubsystem poseEstimationSubsystem = new PoseEstimationSubsystem(photonCamera, driveTrainSubsystem, gyroSubsystem);
    private final ClawSubsystem clawSubsystem = new ClawSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final BreakSubsystem breakSubsystem = new BreakSubsystem();
    private final PhotonColorVisionSubsystem photonColorVisionSubsystem = new PhotonColorVisionSubsystem(photonCamera);
    private final ColorSubsystem colorSubsystem = new ColorSubsystem(photonColorVisionSubsystem);
    private final CompressorSubsystem compressorSubsystem = new CompressorSubsystem();
    private final SendableChooser<StartingPositions> startPosChooser = new SendableChooser<StartingPositions>();


    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController controller = new CommandXboxController(OperatorConstants.K_DRIVER_CONTROLLER_PORT);

    double deadZone(double control) {
        if (Math.abs(control) < Constants.deadzone) {
            return 0.0;
        }
        return control;
    }

    double tank(double control) {
        if (controller.rightStick().getAsBoolean()) {
            return 0.0;
        }
        return control;
    }

    double slowMode(double control) {
        if (controller.leftTrigger().getAsBoolean()) {
            return control * 0.25;
        }
        return control;
    }

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        CameraServer.startAutomaticCapture();
        configureBindings();
        driveTrainSubsystem.setDefaultCommand(
                new MecanumDriveCommand(
                        driveTrainSubsystem,
                        () -> {
                            var input = controller.getLeftX(); //put negative here to change polarity
                            if (controller.getHID().getBButton()) {
                                input = input / 4;
                            }
                            SmartDashboard.putNumber("strafing", input);
                            return slowMode(tank(deadZone(input)));
                        },
                        () -> {
                            var input = -controller.getLeftY();
                            if (controller.getHID().getBButton()) {
                                input = input / 4;
                            }
                            SmartDashboard.putNumber("forwards", input);
                            return slowMode(deadZone(input));
                        },
                        () -> {
                            var input = controller.getRightX();
                            if (controller.getHID().getBButton()) {
                                input = input / 4;
                            }
                            SmartDashboard.putNumber("rotation", input);
                            return slowMode(tank(deadZone(input)));
                        }
                )
        );

        //start da compressor
        compressorSubsystem.enableDaCompressor();
        for (StartingPositions p : StartingPositions.values()) {
            startPosChooser.addOption(p.name(), p);
        }

        startPosChooser.setDefaultOption(StartingPositions.Sussex.name(), StartingPositions.Sussex);
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
        controller.x().onTrue(new ClawCommand(clawSubsystem, true));
        controller.a().onTrue(new ClawCommand(clawSubsystem, false));
        controller.rightBumper().whileTrue(new ManualArmMovementCommand(armSubsystem, () -> 0.5));
        controller.leftBumper().whileTrue(new ManualArmMovementCommand(armSubsystem, () -> -0.5));
        controller.back().onTrue(new BreakCommand((breakSubsystem)));
        controller.y().onTrue(new ToggleArmActuationCommand(armSubsystem));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        //Getting smart-dashboard value
        StartingPositions startPos = startPosChooser.getSelected();
        SmartDashboard.putString("Current Auto Start Config", startPos.name());
        switch (startPos) {
            case A:
                return new PositionBCommand(armSubsystem, clawSubsystem, colorSubsystem, driveTrainSubsystem);
            case B:
                return new PositionACommand(armSubsystem, clawSubsystem, colorSubsystem, driveTrainSubsystem, gyroSubsystem, poseEstimationSubsystem);
            case C:
                return new PositionCCommand(armSubsystem, clawSubsystem, colorSubsystem, driveTrainSubsystem, gyroSubsystem, poseEstimationSubsystem);
            case D:
                return new PositionDCommand(armSubsystem, clawSubsystem, colorSubsystem, driveTrainSubsystem);
            case Sussex:
                return new SussexDummyAutoCommand(driveTrainSubsystem);
            default:
                return new SequentialCommandGroup();
        }
    }
}
