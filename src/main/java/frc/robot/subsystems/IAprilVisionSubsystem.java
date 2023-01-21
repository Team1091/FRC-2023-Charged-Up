package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.AprilTagLocation;

import java.util.List;

public interface IAprilVisionSubsystem extends Subsystem {
    List<AprilTagLocation> getAllTargets();
}
