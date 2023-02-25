package frc.robot.commands.AutoStartCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.AutoArmMovementCommand;
import frc.robot.commands.BalanceCommand;
import frc.robot.commands.ClawCommand;
import frc.robot.commands.DriveToPoseCommand;
import frc.robot.subsystems.*;

public class PositionCCommand extends SequentialCommandGroup {
    //Score Dock
    Pose2d chargingStation = new Pose2d(new Translation2d(0, 0), new Rotation2d(0));//Make it the Charging Station

    public PositionCCommand(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, DriveTrainSubsystem driveTrainSubsystem, GyroBalanceSubsystem gyroBalanceSubsystem, PoseEstimationSubsystem poseEstimationSubsystem) {
        addCommands(new AutoArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new ClawCommand(clawSubsystem, false),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.IN),
                new DriveToPoseCommand(driveTrainSubsystem, poseEstimationSubsystem, chargingStation),
                new BalanceCommand(gyroBalanceSubsystem, driveTrainSubsystem));
    }
}
