// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AprilTagLocation;

import java.util.ArrayList;
import java.util.List;

public class DummyVisionSubsystem extends SubsystemBase implements IVisionSubsystem {
    public List<AprilTagLocation> bruh;
    Timer timer;
    int currentTargetIndex = 0;

    public DummyVisionSubsystem() {
        bruh = new ArrayList<AprilTagLocation>();

        bruh.add(new AprilTagLocation(1, 5.0, .5));
        bruh.add(new AprilTagLocation(1, 4.0, .5));
        bruh.add(new AprilTagLocation(1, 4.0, .5));
        bruh.add(new AprilTagLocation(1, 3.0, .5));
        bruh.add(new AprilTagLocation(1, 2.0, .5));
        bruh.add(new AprilTagLocation(1, 1.0, .5));
        bruh.add(new AprilTagLocation(1, 1.0, .4));
        bruh.add(new AprilTagLocation(1, 1.0, .3));
        bruh.add(new AprilTagLocation(1, 1.0, .2));
        bruh.add(new AprilTagLocation(1, 1.0, .1));
        bruh.add(new AprilTagLocation(1, 1.0, 0.0));

        timer = new Timer();
        timer.start();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Dummy Index", currentTargetIndex);
        // This method will be called once per scheduler run
        if (bruh.isEmpty() || currentTargetIndex == -1) {
            return;
        }

        if (timer.hasElapsed(2)) {
            if (currentTargetIndex == bruh.size() - 1) {
                currentTargetIndex = -1;
                return;
            }

            currentTargetIndex++;
            timer.reset();
        }
    }

    @Override
    public List<AprilTagLocation> getAllTargets() {

        List dummyResult = new ArrayList<>();
        if (bruh.isEmpty() || currentTargetIndex == -1) {
            return dummyResult;
        }
        dummyResult.add(bruh.get(currentTargetIndex));
        return dummyResult;
    }

    public void Reset() {
        currentTargetIndex = 0;
    }


}
