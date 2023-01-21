package frc.robot.utils;

public class Rotation {
    private final double valueInRadians;

    private Rotation(double value){
        this.valueInRadians = value;
    }

    public static Rotation inRadians(double value){
        return new Rotation(value);
    }

    public static Rotation inDegrees(double value){
        return  new Rotation(value / 57.2957795);
    }

    public double toRadians() {
        return valueInRadians;
    }

    public double toDegrees(){
        return valueInRadians * 57.2957795;
    }
}
