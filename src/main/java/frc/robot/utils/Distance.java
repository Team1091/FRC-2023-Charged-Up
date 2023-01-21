package frc.robot.utils;

public class Distance {
    private final double valueInMeters;

    private Distance(double value) {
        this.valueInMeters = value;
    }

    public static Distance inMeters(double value) {
        return new Distance(value);
    }

    public static Distance inFeet(double value) {
        return new Distance(value / 3.2808399);
    }

    public static Distance inInches(double value) {
        return new Distance(value / 39.3700787);
    }

    public double toMeters() {
        return valueInMeters;
    }

    public double toFeet() {
        return valueInMeters * 3.2808399;
    }

    public double toInches() {
        return valueInMeters * 39.3700787;
    }
}
