// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;

import java.io.IOException;
import java.util.Optional;

public class PoseEstimationSubsystem extends SubsystemBase {

    private final PhotonCamera photonCamera;
    private final AprilTagFieldLayout aprilTagFieldLayout;
    private final MecanumDrivePoseEstimator poseEstimator;
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final GyroBalanceSubsystem gyroBalanceSubsystem;
    private final Field2d field2d = new Field2d();
    private double previousPipelineTimestamp = 0;

    /**
     * Standard deviations of model states. Increase these numbers to trust your model's state estimates less. This
     * matrix is in the form [x, y, theta]ᵀ, with units in meters and radians, then meters.
     */
    private static final Vector<N3> stateStdDevs = VecBuilder.fill(0.05, 0.05, Units.degreesToRadians(5));

    /**
     * Standard deviations of the vision measurements. Increase these numbers to trust global measurements from vision
     * less. This matrix is in the form [x, y, theta]ᵀ, with units in meters and radians.
     */
    private static final Vector<N3> visionMeasurementStdDevs = VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(10));

    private ShuffleboardTab tab = null;

    public PoseEstimationSubsystem(
            PhotonCamera photonCamera,
            DriveTrainSubsystem drivetrainSubsystem,
            GyroBalanceSubsystem gyroBalanceSubsystem) {
        this.photonCamera = photonCamera;
        this.driveTrainSubsystem = drivetrainSubsystem;
        this.gyroBalanceSubsystem = gyroBalanceSubsystem;
        AprilTagFieldLayout layout;
        try {
            layout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);
            var alliance = DriverStation.getAlliance();
            layout.setOrigin(alliance == DriverStation.Alliance.Blue ?
                    AprilTagFieldLayout.OriginPosition.kBlueAllianceWallRightSide :
                    AprilTagFieldLayout.OriginPosition.kRedAllianceWallRightSide);

            tab = Shuffleboard.getTab("Vision");
        } catch(IOException e) {
            DriverStation.reportError("Failed to load AprilTagFieldLayout", e.getStackTrace());
            SmartDashboard.putString("April field layout", "Failed");
            layout = null;
        }
        this.aprilTagFieldLayout = layout;
        poseEstimator =  new MecanumDrivePoseEstimator(
                Constants.DriveTrain.KINEMATICS,
                Rotation2d.fromDegrees(this.gyroBalanceSubsystem.getYaw()),
                drivetrainSubsystem.getWheelPositions(),
                new Pose2d(),
                stateStdDevs,
                visionMeasurementStdDevs);
    }

    @Override
    public void periodic() {
        // Update pose estimator with the best visible target
        var pipelineResult = photonCamera.getLatestResult();
        var resultTimestamp = pipelineResult.getTimestampSeconds();
        if (resultTimestamp != previousPipelineTimestamp && pipelineResult.hasTargets()) {//don't process same input twice
            previousPipelineTimestamp = resultTimestamp;
            var target = pipelineResult.getBestTarget();
            var fiducialId = target.getFiducialId();
            // Get the tag pose from field layout - consider that the layout will be null if it failed to load
            Optional<Pose3d> tagPose = aprilTagFieldLayout == null ? Optional.empty() : aprilTagFieldLayout.getTagPose(fiducialId);
            if (target.getPoseAmbiguity() <= .2 && fiducialId >= 0 && tagPose.isPresent()) {
                var targetPose = tagPose.get();
                Transform3d camToTarget = target.getBestCameraToTarget();
                Pose3d camPose = targetPose.transformBy(camToTarget.inverse());

                var visionMeasurement = camPose.transformBy(Constants.CAMERA_TO_ROBOT);
                poseEstimator.addVisionMeasurement(visionMeasurement.toPose2d(), resultTimestamp);
            }
        }
        // Update pose estimator with drivetrain sensors
        poseEstimator.update(
                Rotation2d.fromDegrees(this.gyroBalanceSubsystem.getYaw()),
                driveTrainSubsystem.getWheelPositions());
        field2d.setRobotPose(getCurrentPose());

        try {
            tab.addString("yo yo yo",()->"bruh");
            tab.addString("Pose", this::getFormattedPose).withPosition(0, 0).withSize(2, 0);
            tab.add("Field", field2d).withPosition(2, 0).withSize(6, 4);
        } catch (Exception e) {
            SmartDashboard.putString("error", "a fatal error has occurred, good luck figuring out what it is");
        }
    }

    public Pose2d getCurrentPose() {
        return poseEstimator.getEstimatedPosition();
    }

    private String getFormattedPose() {
        var pose = getCurrentPose();
        return String.format("(%.2f, %.2f) %.2f degrees",
                pose.getX(),
                pose.getY(),
                pose.getRotation().getDegrees());
    }

    public Field2d getField2d() {
        return field2d;
    }
}
