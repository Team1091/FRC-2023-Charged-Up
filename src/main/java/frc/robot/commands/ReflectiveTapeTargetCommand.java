package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.AprilTagLocation;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.DummyVisionSubsystem;
import frc.robot.subsystems.IAprilVisionSubsystem;
import frc.robot.subsystems.ReflectiveTapeSubsystem;
import org.photonvision.targeting.TargetCorner;

import java.util.List;

public class ReflectiveTapeTargetCommand extends CommandBase { //WE Probably won't use this
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final ReflectiveTapeSubsystem reflectiveTapeSubsystem;

    private boolean isDone = false;

    private final double tolerance = 0.05;

    private final double targetDistance = 1;

    private final double maxForwardSpeed = 0.25;

    private final double maxStrafeSpeed = 0.25;

    public ReflectiveTapeTargetCommand(DriveTrainSubsystem driveTrainSubsystem, ReflectiveTapeSubsystem reflectiveTapeSubsystem) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.reflectiveTapeSubsystem = reflectiveTapeSubsystem;
        addRequirements(this.driveTrainSubsystem);
        addRequirements(this.reflectiveTapeSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (reflectiveTapeSubsystem.getCurrentTarget() == null) return;
        if (Math.abs(reflectiveTapeSubsystem.getCurrentTargetRelativeX()) < tolerance) {
            isDone = true;
            return;
        }

        double strafeSpeed = 0;

        if (reflectiveTapeSubsystem.getCurrentTargetRelativeX() > 0) {
            //is to the right, move right
            strafeSpeed = maxStrafeSpeed;
        } else {
            strafeSpeed = -maxStrafeSpeed;
        }

        driveTrainSubsystem.mecanumDrive(strafeSpeed, 0, 0);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //This never ends
        return isDone;
    }

}
