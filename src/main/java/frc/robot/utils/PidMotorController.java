package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PidMotorController implements MotorController {

    private final MotorController motorController;
    private final RelativeEncoder encoder;
    private final PIDController pid;

    private final double maxSpeed;
    private final String name;

    public PidMotorController(String name, CANSparkMax motorController, double maxSpeed, double kP, double kI, double kD) {
        this.name = name;
        this.motorController = motorController;
        this.maxSpeed = maxSpeed;
        this.encoder = motorController.getEncoder();
        pid = new PIDController(kP, kI, kD);
    }

    private double maxForward = 0.0;
    private double minForward = 0.0;

    @Override
    public void set(double speed) {
        // todo: pid stuff

        minForward = Math.min(encoder.getVelocity(), minForward);
        maxForward = Math.max(encoder.getVelocity(), maxForward);
        SmartDashboard.putNumber(name + "max vel", maxForward);
        SmartDashboard.putNumber(name + "min vel", minForward);


        SmartDashboard.putNumber(name + "encoder velocity", encoder.getVelocity());
        var pidSpeed = pid.calculate(encoder.getVelocity() / maxSpeed, speed);
        SmartDashboard.putString(name, "position error: " + pid.getPositionError() +
                " velocity error: " + pid.getVelocityError());

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
