// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MecanumDriveCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//hi
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController controller =
            new CommandXboxController(OperatorConstants.kDriverControllerPort);

    double deadZone(double control){
        if (Math.abs(control)<Constants.deadzone){
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
                            var input = 0.0; //-controller.getLeftX(); //put negative here to change polarity
                            SmartDashboard.putNumber("strafing", input);
                            return deadZone(input);
                        },
                        () -> {
                            var input = controller.getLeftY();
                            SmartDashboard.putNumber("forwards", input);
                            return deadZone(input);
                        },
                        () -> {
                            var input =0.0; // -controller.getRightX();
                            SmartDashboard.putNumber("rotation", input);
                            return deadZone(input);
                        }
                )
        );
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
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
//  public Command getAutonomousCommand() {
//    // An example command will be run in autonomous
////    return Autos.exampleAuto(m_exampleSubsystem);
//  }
}
