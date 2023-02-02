package frc.robot.commands.AutoStartCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.utils.Rotation;

public class PositionCCommand extends SequentialCommandGroup {
    //Score Dock
    Pose2d chargingStation = new Pose2d();//Make it the Charging Station
    public PositionCCommand(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, DriveTrainSubsystem driveTrainSubsystem, GyroBalanceSubsystem gyroBalanceSubsystem, PoseEstimationSubsystem poseEstimationSubsystem){
        addCommands( new ArmMovementCommand(armSubsystem, ArmPosition.HIGH,driveTrainSubsystem),
                new ClawCommand(clawSubsystem, colorSubsystem, false),
                new ArmMovementCommand(armSubsystem, ArmPosition.IN, driveTrainSubsystem),
               new DriveToPoseCommand(driveTrainSubsystem,poseEstimationSubsystem,chargingStation),
                new BalanceCommand(gyroBalanceSubsystem, driveTrainSubsystem));
    }
}
