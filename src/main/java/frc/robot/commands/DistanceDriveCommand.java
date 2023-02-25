package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Distance;

public class DistanceDriveCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final Double xDistance;
    private double leftEncoderTarget;
    private final boolean isReverse;

    public DistanceDriveCommand(DriveTrainSubsystem driveTrainSubsystem, Distance distance) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.xDistance = distance.toMeters() * -1;
        this.isReverse = xDistance < 0;
        addRequirements(this.driveTrainSubsystem);
    }

    @Override
    public void initialize() {
        this.leftEncoderTarget = driveTrainSubsystem.getFrontLeftEncoder() + xDistance;
    }

    @Override
    public void execute() {
        driveTrainSubsystem.mecanumDrive(0, 0.25 * (isReverse ? -1 : 1), 0);
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
