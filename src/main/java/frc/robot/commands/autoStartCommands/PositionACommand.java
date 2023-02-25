package frc.robot.commands.autoStartCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.utils.Distance;
import frc.robot.utils.Rotation;

public class PositionACommand {
    //Score Pick Score Dock
    private static final Pose2d chargingStation = new Pose2d(new Translation2d(0, 0), new Rotation2d(0));//Make it the Charging Station
    private static final Distance toCubeORCone = Distance.inFeet(10.0);//TODO: Set Proper distance
    private static final Rotation rotationAmount = Rotation.inDegrees(180);


    public static Command create(
            ArmSubsystem armSubsystem,
            ClawSubsystem clawSubsystem,
            DriveTrainSubsystem driveTrainSubsystem,
            GyroBalanceSubsystem gyroBalanceSubsystem,
            PoseEstimationSubsystem poseEstimationSubsystem
    ) {
        return new SequentialCommandGroup(
                new AutoArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new ClawCommand(clawSubsystem, false),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.IN),
                new DistanceDriveCommand(driveTrainSubsystem, toCubeORCone.reversed()),
                new TurnCommand(driveTrainSubsystem, rotationAmount),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.MIDDLE),
                new ClawCommand(clawSubsystem, true),
                new TurnCommand(driveTrainSubsystem, rotationAmount),
                new DistanceDriveCommand(driveTrainSubsystem, toCubeORCone),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.IN),
                new ClawCommand(clawSubsystem, false),
                new DriveToPoseCommand(driveTrainSubsystem, poseEstimationSubsystem, chargingStation),
                new BalanceCommand(gyroBalanceSubsystem, driveTrainSubsystem));
    }

}
