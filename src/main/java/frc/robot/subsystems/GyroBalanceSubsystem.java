// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class GyroBalanceSubsystem extends SubsystemBase {

    private final AHRS gyro;

    public GyroBalanceSubsystem() {
        gyro = new AHRS(Constants.gyroPort);
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
        SmartDashboard.putNumber("Gyro roll", gyro.getRoll());
        SmartDashboard.putNumber("Gyro Pitch", gyro.getPitch());
    }

    public double getPitch() {
        return gyro.getPitch();
    }

    public double getRoll() {
        return gyro.getRoll();
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    public double getYaw() {
        return gyro.getYaw();
    }
}
