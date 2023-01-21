// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AprilTagLocation;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import java.util.ArrayList;
import java.util.List;

public class PhotonVisionSubsystem extends SubsystemBase implements IAprilVisionSubsystem {

    private PhotonCamera photonCamera;

    public PhotonVisionSubsystem() {
        photonCamera = new PhotonCamera(Constants.cameraName);
        Shuffleboard.getTab("Vision");
        SmartDashboard.putNumber("Update Parameters?", 0);
    }


    @Override
    public void periodic() {
        getAllTargets();
        //SmartDashboard.putNumber("pitch", photoFnCamera.getLatestResult().getBestTarget().getPitch());

        Shuffleboard.selectTab("Vision");
//        Shuffleboard.getTab("Vision").add("Camera radians", Constants.cameraPitchRadians)
//                .withWidget(BuiltInWidgets.kNumberSlider)
//                //.withProperties(Map.of("min", 0, "max", 1)) // specify widget properties here
//                .getEntry();
//
//        Shuffleboard.getTab("Vision").add("Camera height", Constants.cameraHeightMeters)
//                .withWidget(BuiltInWidgets.kNumberSlider)
//                //.withProperties(Map.put("min", 0, "max", 1)) // specify widget properties here
//                .getEntry();
//
//        Shuffleboard.getTab("Vision").add("Target height", Constants.targetHeightInMeters)
//                .withWidget(BuiltInWidgets.kNumberSlider)
//                //.withProperties(Map("min", 0, "max", 1)) // specify widget properties here
//                .getEntry();



        if (SmartDashboard.getNumber("Update Parameters?",0) != 0) {
            Constants.cameraPitchRadians = SmartDashboard.getNumber("camera radians", Constants.cameraPitchRadians);
            Constants.cameraHeightMeters = SmartDashboard.getNumber("camera height", Constants.cameraPixelHeight);
            //Constants.targetHeightInMeters = SmartDashboard.getNumber("target height", Constants.targetHeightInMeters);

            SmartDashboard.putNumber("camera radians", Constants.cameraPitchRadians);
            SmartDashboard.putNumber("camera height", Constants.cameraHeightMeters);
            SmartDashboard.putNumber("target height", Constants.targetHeightInMeters);

            SmartDashboard.putNumber("Update Parameters?", 0);
        }



        //Shuffleboard.getTab("default");
    }

    public List<AprilTagLocation> getAllTargets() {
        var currentResult = photonCamera.getLatestResult();
        var result = new ArrayList<AprilTagLocation>();
        for(PhotonTrackedTarget target : currentResult.getTargets()) {
            //target.getBestCameraToTarget()
            var distance = PhotonUtils.calculateDistanceToTargetMeters(Constants.cameraHeightMeters,
                    getTargetHeight(target.getFiducialId()),
                    Constants.cameraPitchRadians,
                    (target.getPitch())*Math.PI/180);
            var horizontalRelativePos = convertPixelCordsToRelativeWidth(getCenterOfRect(target.getMinAreaRectCorners()));
            result.add(new AprilTagLocation(target.getFiducialId(), distance, horizontalRelativePos));
        }


        //if(test) {
        for (AprilTagLocation displayer : result) {
            //Shuffleboard.getTab("Vision").add("April Tag" + displayer.getIdNumber(),displayer.toString());
            Shuffleboard.selectTab("Vision");
            SmartDashboard.putString("April Tag" + displayer.getIdNumber(),displayer.toString());
        }

        return result;
    }

    private double getTargetHeight(int ID) {
        double returner = Constants.targetHeightInMeters;
        if (ID == 4 || ID == 5) {
            returner = Constants.stationTargetHeightInMeters;
        }
        return returner;
    }

    private TargetCorner getCenterOfRect(List<TargetCorner> cords) {//returns 2 doubles corresponding to x and y coordinate of center of rect
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

    private double convertPixelCordsToRelativeWidth(TargetCorner centerPoint) {
        return ((Constants.cameraPixelWidth /2) - centerPoint.x)/Constants.cameraPixelWidth;
    }
}
