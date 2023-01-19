package frc.robot;

import java.util.List;

public class AprilTagLocation {
    private int idNumber;
    private double targetDistance;

    private int targetXPos;
    public AprilTagLocation(int idNumber, double targetDistance, int targetXPos) {
       idNumber = this.idNumber;
       targetDistance = this.targetDistance;
       targetXPos = this.targetXPos;
    }
    public int getIdNumber(){
        return idNumber;
    }

    public double getTargetDistance(){
        return targetDistance;
    }
    public int getTargetXPos(){
        return targetXPos;
    }
}
