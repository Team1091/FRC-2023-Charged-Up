// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.GyroBalanceSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class StabilizePitchRollCommand extends CommandBase { //WE DO NOT USE THIS, Delete Once we make sure balance command works
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final GyroBalanceSubsystem gyroSubsystem;
    private final DriveTrainSubsystem driveSubsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public StabilizePitchRollCommand(GyroBalanceSubsystem subsystem, DriveTrainSubsystem driveSystem) {
        gyroSubsystem = subsystem;
        driveSubsystem = driveSystem;
        addRequirements(gyroSubsystem);
        addRequirements(driveSubsystem);

        //want pitch and roll to be 0
        //TODO May want to add a YAW system that aligns the robot to be perfectly straight when on the charging station
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double forwardsMove = 0;
        if (gyroSubsystem.getPitch() > Constants.gyroDeadZoneThreshold) {
            forwardsMove = Constants.gyroCommandMoveSpeed;
        } else if (gyroSubsystem.getPitch() < Constants.gyroDeadZoneThreshold) {
            forwardsMove = -Constants.gyroCommandMoveSpeed;
        }

        double horizontalMove = 0;
        if (gyroSubsystem.getRoll() > Constants.gyroDeadZoneThreshold) {
            horizontalMove = Constants.gyroCommandMoveSpeed;
        } else if (gyroSubsystem.getRoll() < Constants.gyroDeadZoneThreshold) {
            horizontalMove = -Constants.gyroCommandMoveSpeed;
        }

        driveSubsystem.mecanumDrive(horizontalMove, forwardsMove, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //This never ends
        return false;
    }
}
