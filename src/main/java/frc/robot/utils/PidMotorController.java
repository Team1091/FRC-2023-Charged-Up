package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class PidMotorController implements MotorController {

    private final MotorController motorController;
    private final RelativeEncoder encoder;
    private final PIDController pid;

    private final double maxSpeed;

    public PidMotorController(CANSparkMax motorController, double maxSpeed, double kP, double kI, double kD) {
        this.motorController = motorController;
        this.maxSpeed = maxSpeed;
        this.encoder = motorController.getEncoder();
        pid = new PIDController(kP, kI, kD);
    }

    @Override
    public void set(double speed) {
        // todo: pid stuff
        var pidSpeed = pid.calculate(encoder.getVelocity() / maxSpeed, speed);
        motorController.set(pidSpeed);
    }

//    @Override
//    public void setVoltage(double outputVolts) {
//        MotorController.super.setVoltage(outputVolts);
//    }

    @Override
    public double get() {
        return motorController.get();
    }

    @Override
    public void setInverted(boolean isInverted) {
        motorController.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return motorController.getInverted();
    }

    @Override
    public void disable() {
        motorController.disable();
    }

    @Override
    public void stopMotor() {
        motorController.stopMotor();
    }
}
