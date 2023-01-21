package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.AprilTagLocation;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.DummyVisionSubsystem;
import frc.robot.subsystems.IAprilVisionSubsystem;

import java.util.List;

public class VisionTargetCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final IAprilVisionSubsystem photonVisionSubsystem;

    private final double tolerance = 0.05;

    private final double targetDistance = 1;

    private final double forwardSpeed = 0.25;

    private final double strafeSpeed = -0.25;

    private final int numberToFind;

    public VisionTargetCommand(DriveTrainSubsystem driveTrainSubsystem, IAprilVisionSubsystem photonVisionSubsystem, int numberToFind) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.photonVisionSubsystem = photonVisionSubsystem;
        this.numberToFind = numberToFind;
        ((DummyVisionSubsystem) photonVisionSubsystem).Reset();
        addRequirements(this.driveTrainSubsystem);
        addRequirements(this.photonVisionSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        List<AprilTagLocation> visableTargets = photonVisionSubsystem.getAllTargets();

        var target = visableTargets
                .stream()
                .filter((AprilTagLocation it) -> it.getIdNumber() == numberToFind).findFirst();


        if (!target.isPresent()) {

            driveTrainSubsystem.mecanumDrive(0, 0, 0);
            return;
        }

        var horizontalOffset = target.get().getHorizontalPosOfCenter();

        if (target.get().getTargetDistance() > targetDistance) {
            driveTrainSubsystem.mecanumDrive(0, forwardSpeed, 0);
            return;
        }

        if (Math.abs(horizontalOffset) > tolerance) {
            if (horizontalOffset < 0) {
                driveTrainSubsystem.mecanumDrive(-strafeSpeed, 0, 0);
                return;
            }

            driveTrainSubsystem.mecanumDrive(strafeSpeed, 0, 0);
            return;
        }

        driveTrainSubsystem.mecanumDrive(0, 0, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
