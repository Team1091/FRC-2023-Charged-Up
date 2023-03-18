// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import java.util.Comparator;
import java.util.List;

public class ReflectiveTapeSubsystem extends SubsystemBase {

    private final PhotonCamera photonCamera;
    private PhotonTrackedTarget currentTarget;

    public ReflectiveTapeSubsystem(PhotonCamera reflectiveCamera) {
        photonCamera = reflectiveCamera;
        ShuffleboardTab visionTab = Shuffleboard.getTab("Vision");
        SmartDashboard.putNumber("Update Parameters?", 0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("Inputs Exist?", photonCamera.getLatestResult().getTargets().size()>0?"Yes has targets":"No");

        var target = photonCamera.getLatestResult().getTargets().stream()
                .min(Comparator.comparingDouble((t) ->
                        Math.abs(convertPixelCordsToRelativeWidth(getCenterOfRect(t.getDetectedCorners())))
                ));

        SmartDashboard.putString("Target", target.toString());
        SmartDashboard.putNumber("Latest Result exists", target.isPresent()? 1:0);

        if (target.isPresent()) {
            // do the calculations
            currentTarget = target.get();
            SmartDashboard.putString("curTar", currentTarget.getDetectedCorners().toString());

            SmartDashboard.putNumber("RelativeX of current target", getCurrentTargetRelativeX());
            SmartDashboard.putString("Numbers!", currentTarget.getDetectedCorners().toString());
        }else{
            //we didnt see it
            currentTarget = null;
        }

    }

    public PhotonTrackedTarget getCurrentTarget() {
        return currentTarget;
    }

    public double getCurrentTargetRelativeX() {
        return convertPixelCordsToRelativeWidth(getCenterOfRect(currentTarget.getDetectedCorners()));
    }

    private TargetCorner getCenterOfRect(List<TargetCorner> cords) {//returns 2 doubles corresponding to x and y coordinate of center of rect
        double xSum = 0;
        for (TargetCorner cord : cords) {
            xSum += cord.x;
        }

        double ySum = 0;
        for (TargetCorner cord : cords) {
            ySum += cord.y;
        }
        int num = cords.size();
        return new TargetCorner(xSum / num, ySum / num);
    }

    //-1 means left most, +1 means right most
    private double convertPixelCordsToRelativeWidth(TargetCorner centerPoint) {
        return (centerPoint.x - (Constants.cameraPixelWidth / 2.0)) / (Constants.cameraPixelWidth/2.0);
    }
}
