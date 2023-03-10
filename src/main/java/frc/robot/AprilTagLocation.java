package frc.robot;

public class AprilTagLocation {
    private final int idNumber;
    private final double targetDistance;
    private final double horizontalPosOfCenter; //relative distance where -1 is left most, and 1.0 is rightmost, and 0 is center

    public AprilTagLocation(int idNumber, double targetDistance, double horizontalPosOfCenter) {
        this.idNumber = idNumber;
        this.targetDistance = targetDistance;
        this.horizontalPosOfCenter = horizontalPosOfCenter;

    }

    public int getIdNumber() {
        return idNumber;
    }

    public double getTargetDistance() {
        return targetDistance;
    }

    public double getHorizontalPosOfCenter() {
        return horizontalPosOfCenter;
    }

    @Override
    public String toString() {
        return "ID: " + idNumber + "     Target Distance (inch): " + targetDistance * 39.37 + "       HorizontalOffset: " + horizontalPosOfCenter;
    }
}