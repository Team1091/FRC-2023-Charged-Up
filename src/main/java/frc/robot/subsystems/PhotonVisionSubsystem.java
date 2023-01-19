// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import java.util.ArrayList;
import java.util.List;

public class PhotonVisionSubsystem extends SubsystemBase {

    private PhotonCamera photonCamera = new PhotonCamera("Cameron");
    private PhotonPipelineResult currentResult;
    public List<PhotonTrackedTarget> trackedTargets;
    public double distanceToTarget;
    public double rotationNeededToMatchTarget;
    public double strafeNeededToCenterTarget;

    private int testCounter = 0;
    public PhotonVisionSubsystem() {

    }

    //PhotonCamera

    /**
     * Example command factory method.
     *
     * @return a command
     */
    public CommandBase exampleMethodCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    /* one-time action goes here */
                });
    }

    /**
     * An example method querying a boolean state of the subsystem (for example, a digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
    public boolean exampleCondition() {
        // Query some boolean state, such as a digital sensor.
        return false;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        currentResult = photonCamera.getLatestResult();

        if (currentResult.hasTargets()) {
            //trackedTargets = currentResult.getTargets(); we will worry about this later if need be
            //for now we just want it to follow 1 target
            PhotonTrackedTarget bestTarget = currentResult.getBestTarget();
            int targetID = bestTarget.getFiducialId(); //gets number of the April Tag ID


            double targetHeightInMeters = 0.384175;

            if (targetID == 4 || targetID == 5) {
                targetHeightInMeters = 0.619252;
            }


            distanceToTarget = PhotonUtils.calculateDistanceToTargetMeters(Constants.cameraHeightMeters,
                    targetHeightInMeters,
                    Constants.cameraPitchRadians,
                    bestTarget.getPitch());

            rotationNeededToMatchTarget = bestTarget.getYaw();


            testCounter += 1;
            if (testCounter %100 == 0) {
                System.out.println("Connected to camera:\t" + photonCamera.getName());
                System.out.println("April tag ID:\t" + bestTarget.getFiducialId());
                System.out.println("Skew:\t" + bestTarget.getSkew());
                System.out.println("Rect corners:\t" + bestTarget.getMinAreaRectCorners());
                System.out.println("Center of bounded rect" + getCenterofRect(bestTarget.getMinAreaRectCorners()));

                System.out.println("\n\n");
                System.out.println("Distance:\t" + distanceToTarget);
                System.out.println("Rot needed:\t" + rotationNeededToMatchTarget);
                System.out.println("Strafe needed:\t" + strafeNeededToCenterTarget);
            }

        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    public double getRotationNeededToMatchTarget() {
        return rotationNeededToMatchTarget;
    }

    public double getDistanceToTarget() {
        return distanceToTarget;
    }

    public double getStrafeNeededToCenterTarget() {
        return strafeNeededToCenterTarget;
    }

    public TargetCorner getCenterofRect(List<TargetCorner> cords) {//returns 2 doubles corresponding to x and y coordinate of center of rect
        int num = cords.size();
        double xSum = 0;
        for (TargetCorner cord : cords) {
            xSum += cord.x;
        }

        double ySum = 0;
        for (TargetCorner cord : cords) {
            ySum += cord.y;
        }
        return new TargetCorner(xSum/num, ySum/num);
    }
}
