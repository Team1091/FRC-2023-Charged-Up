package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;

public class AccelerationCurve {
    private final Timer timer = new Timer();
    private double currentSpeed = 0;
    private final double acceleration;
    private final double minimumPower;
    private final double maximumPower;

    public AccelerationCurve(double acceleration, double minimumPower, double maximumPower) {
        timer.start();
        this.acceleration = acceleration;
        this.minimumPower = minimumPower;
        this.maximumPower = maximumPower;
    }

    public void set(double input) {
        set(input, minimumPower, maximumPower);
    }

    public void set(double input, double minPowerOverride, double maxPowerOverride) {
        double seconds = timer.get();
        timer.reset();

        double velocityChange = acceleration * seconds;
        if (input == 0) {
            currentSpeed = 0;
            return;
        }

        if (input > 0) {
            if (currentSpeed >= 0) {
                currentSpeed = (input > currentSpeed) ? currentSpeed + velocityChange : input;
                currentSpeed = MathUtil.clamp(currentSpeed, minPowerOverride, maxPowerOverride);
                return;
            }

            currentSpeed = minPowerOverride;
            return;
        }

        if (input < 0) {
            if (currentSpeed <= 0) {
                currentSpeed = (input < currentSpeed) ? currentSpeed - velocityChange : input;
                currentSpeed = MathUtil.clamp(currentSpeed, -maxPowerOverride, -minPowerOverride);
                return;
            }

            currentSpeed = -minPowerOverride;
        }
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getMinimumPower() {
        return minimumPower;
    }

    public double getMaximumPower() {
        return maximumPower;
    }
}
