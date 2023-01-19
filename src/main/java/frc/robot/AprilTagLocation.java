package frc.robot;

import java.util.List;

public class AprilTagLocation {
    private int idNumber;
    private double targetDistance;
    public AprilTagLocation(int idNumber, double targetDistance) {
       idNumber = this.idNumber;
       targetDistance = this.targetDistance;
    }
    public int getIdNumber(){
        return idNumber;
    }

    public double getTargetDistance(){
        return targetDistance;
    }
}
