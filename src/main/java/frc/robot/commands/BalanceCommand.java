package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.GyroBalanceSubsystem;

public class BalanceCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;

    private final GyroBalanceSubsystem gyroBalanceSubsystem;

    private final double tolerance = 5.0;

    private static final double speed = 0.25;

    public BalanceCommand(GyroBalanceSubsystem gyroBalanceSubsystem, DriveTrainSubsystem driveTrainSubsystem) {

        this.driveTrainSubsystem = driveTrainSubsystem;
        this.gyroBalanceSubsystem = gyroBalanceSubsystem;
        addRequirements(this.driveTrainSubsystem);
        addRequirements(this.gyroBalanceSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double pitch = gyroBalanceSubsystem.getPitch();
        if (Math.abs(pitch) < tolerance) {
            return;
        }

        if (pitch > 0) {
            driveTrainSubsystem.mecanumDrive(0, speed, 0);
            return;
        }

        driveTrainSubsystem.mecanumDrive(0, -speed, 0);
    }

    @Override
    public void end(boolean end) {
        driveTrainSubsystem.mecanumDrive(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        //this will not work, you'll drive past 0 since we don't slow down
        return Math.abs(gyroBalanceSubsystem.getPitch()) < tolerance;
    }
}
