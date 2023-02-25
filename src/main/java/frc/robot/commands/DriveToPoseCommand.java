package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.PoseEstimationSubsystem;

public class DriveToPoseCommand extends CommandBase {
    private static final double DELTA_DISTANCE = 0.1;
    private static final double DELTA_ROTATION = 0.1;
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final PoseEstimationSubsystem poseEstimationSubsystem;

    private final Pose2d target;

//    private final double tolerance = 0.05;
//
//    private final double targetDistance = 1;
//
//    private final double forwardSpeed = 0.25;
//
//    private final double strafeSpeed = -0.25;

//    private final int numberToFind;

    public DriveToPoseCommand(DriveTrainSubsystem driveTrainSubsystem, PoseEstimationSubsystem poseEstimationSubsystem, Pose2d target) {
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.poseEstimationSubsystem = poseEstimationSubsystem;
        this.target = target;
//        this.numberToFind = numberToFind;
//        ((DummyVisionSubsystem) photonVisionSubsystem).reset();
        addRequirements(this.driveTrainSubsystem);
        addRequirements(this.poseEstimationSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        // get our current position
        var curPose = poseEstimationSubsystem.getCurrentPose();
        // 2D pose translation = target - current
        var translation = target.getTranslation().minus(curPose.getTranslation());
        var localPerspectiveTrans = translation.rotateBy(Rotation2d.fromRadians(-curPose.getRotation().getRadians()));
        var rotation = turnLeftOrRight(curPose.getRotation().getRadians(), target.getRotation().getRadians());

        SmartDashboard.putString("perspective:", localPerspectiveTrans.toString());
        SmartDashboard.putNumber("rotation:", rotation);


        // drive that way
        driveTrainSubsystem.mecanumDrive(localPerspectiveTrans.getX(), localPerspectiveTrans.getY(), rotation);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.mecanumDrive(0.0, 0.0, 0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // if rotation and translation are close enough

        var curPose = poseEstimationSubsystem.getCurrentPose();
        var distance = curPose.getTranslation().getDistance(target.getTranslation());
        var rotation = turnLeftOrRight(curPose.getRotation().getRadians(), target.getRotation().getRadians());

        return (distance < DELTA_DISTANCE && Math.abs(rotation) < DELTA_ROTATION);
    }

    /**
     * Given a current angle, find out how to turn to target angle.
     *
     * @param current The angle you are at in radians
     * @param target  The angle you want to turn to in radians
     * @return the turn (positive is left, negative is right)
     */
    private double turnLeftOrRight(double current, double target) {
        var alpha = target - current;
        var beta = target - current + Math.PI * 2;
        var gamma = target - current - Math.PI * 2;

        var alphaAbs = Math.abs(alpha);
        var betaAbs = Math.abs(beta);
        var gammaAbs = Math.abs(gamma);


        if (alphaAbs <= betaAbs && alphaAbs <= gammaAbs) {
            return alpha;
        } else if (betaAbs <= alphaAbs && betaAbs <= gammaAbs) {
            return beta;
        } else {
            return gamma;
        }
    }
}
