package frc.robot.commands.AutoStartCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.utils.Rotation;

public class PositionCCommand extends SequentialCommandGroup {
    //Score Dock
    Pose2d chargingStation = new Pose2d(new Translation2d(0,0),new Rotation2d(0));//Make it the Charging Station
    public PositionCCommand(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, DriveTrainSubsystem driveTrainSubsystem, GyroBalanceSubsystem gyroBalanceSubsystem, PoseEstimationSubsystem poseEstimationSubsystem){
        addCommands( new ArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new ClawCommand(clawSubsystem, colorSubsystem, false),
                new ArmMovementCommand(armSubsystem, ArmPosition.IN),
               new DriveToPoseCommand(driveTrainSubsystem,poseEstimationSubsystem,chargingStation),
                new BalanceCommand(gyroBalanceSubsystem, driveTrainSubsystem));
    }
}
