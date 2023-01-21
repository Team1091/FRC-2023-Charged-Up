package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TurnCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final Double turnDistance;
    private double leftEncoderTarget;
    private boolean isReverse;

    public TurnCommand(
            DriveTrainSubsystem driveTrainSubsystem,
            Double turnDistance
    ) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.turnDistance = turnDistance;
        this.isReverse = turnDistance < 0;
        addRequirements(this.driveTrainSubsystem);
    }

    @Override
    public void initialize() {
        this.leftEncoderTarget = driveTrainSubsystem.getLeftEncoder() + turnDistance;
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
            return (driveTrainSubsystem.getLeftEncoder() < leftEncoderTarget);
        }

        return (driveTrainSubsystem.getLeftEncoder() > leftEncoderTarget);
    }
}
