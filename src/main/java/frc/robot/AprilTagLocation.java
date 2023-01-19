package frc.robot;

public class AprilTagLocation {
    private int idNumber;
    private double targetDistance;
    private double horizontalPosOfCenter; //relative distance where -1 is left most, and 1.0 is rightmost, and 0 is center
    public AprilTagLocation(int idNumber, double targetDistance, double horizontalPosOfCenter) {
        this.idNumber = idNumber;
        this.targetDistance = targetDistance;
        this.horizontalPosOfCenter = horizontalPosOfCenter;

    }
    public int getIdNumber(){
        return idNumber;
    }

    public double getTargetDistance(){
        return targetDistance;
    }
}