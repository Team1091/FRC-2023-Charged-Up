package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.AprilTagLocation;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IVisionSubsystem;
import frc.robot.subsystems.PhotonVisionSubsystem;

import java.util.List;

public class VisionTargetCommand extends CommandBase {
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final IVisionSubsystem photonVisionSubsystem;

    private final int numberToFind;

    public VisionTargetCommand(DriveTrainSubsystem driveTrainSubsystem, IVisionSubsystem photonVisionSubsystem, int numberToFind) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.photonVisionSubsystem = photonVisionSubsystem;
        this.numberToFind = numberToFind;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        List<AprilTagLocation> visableTargets = photonVisionSubsystem.getTargets();

        var target = visableTargets
                .stream()
                .filter((AprilTagLocation it) -> it.getIdNumber() == numberToFind)
                .findFirst();
        if (target.isPresent()) {

            }

        else {
            driveTrainSubsystem.mecanumDrive(0,0,0);
        }
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
