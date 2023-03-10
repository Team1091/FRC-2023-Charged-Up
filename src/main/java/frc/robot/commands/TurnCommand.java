package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Rotation;

public class TurnCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final Double turnDistance;
    private double leftEncoderTarget;
    private final boolean isReverse;

    public TurnCommand(DriveTrainSubsystem driveTrainSubsystem, Rotation turnDistance) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.turnDistance = turnDistance.toRadians();
        this.isReverse = this.turnDistance < 0;
        addRequirements(this.driveTrainSubsystem);
    }

    @Override
    public void initialize() {
        this.leftEncoderTarget = driveTrainSubsystem.getFrontLeftEncoder() + turnDistance;
    }

    @Override
    public void execute() {
        driveTrainSubsystem.mecanumDrive(0, 0, 0.25 * (isReverse ? -1 : 1));
    }

    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.mecanumDrive(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        if (isReverse) { //this could be backwards, fix if found during trial and error
            return (driveTrainSubsystem.getFrontLeftEncoder() < leftEncoderTarget);
        }

        return (driveTrainSubsystem.getFrontLeftEncoder() > leftEncoderTarget);
    }
}
