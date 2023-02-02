package frc.robot.commands.AutoStartCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.utils.Rotation;

public class PositionACommand extends SequentialCommandGroup {
    //Score Pick Score Dock
    Pose2d chargingStation = new Pose2d();//Make it the Charging Station
    private double toCubeORCone = 100000.0;//Set Proper distance
    private double rotationAmount = 180;
    public PositionACommand(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, DriveTrainSubsystem driveTrainSubsystem, GyroBalanceSubsystem gyroBalanceSubsystem, PoseEstimationSubsystem poseEstimationSubsystem){
        addCommands(
                new ArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new ClawCommand(clawSubsystem, colorSubsystem, false),
                new ArmMovementCommand(armSubsystem, ArmPosition.IN),
                new DistanceDriveCommand(driveTrainSubsystem, -toCubeORCone),
                new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(rotationAmount).toRadians()),
                new ArmMovementCommand(armSubsystem, ArmPosition.MIDDLE),
                new ClawCommand(clawSubsystem, colorSubsystem, true),
                new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(rotationAmount).toRadians()),
                new DistanceDriveCommand(driveTrainSubsystem, toCubeORCone),
                new ArmMovementCommand(armSubsystem, ArmPosition.IN),
                new ClawCommand(clawSubsystem, colorSubsystem, false),
                new DriveToPoseCommand(driveTrainSubsystem,poseEstimationSubsystem, chargingStation),
                new BalanceCommand(gyroBalanceSubsystem, driveTrainSubsystem));

    }
}
