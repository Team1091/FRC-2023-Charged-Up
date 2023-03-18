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

public class DockingCommand {
    //Score Dock
    private static final Pose2d chargingStation = new Pose2d(new Translation2d(0, 0), new Rotation2d(0));//Make it the Charging Station
    private static final Distance toCubeORCone = Distance.inFeet(10.0);//TODO: Set Proper distance
    private static final Distance forward = Distance.inFeet(1.0);
    private static final Distance back = Distance.inFeet(-2.0);
    private static final Rotation rotationAmount = Rotation.inDegrees(180);


    public static Command create(
            ArmSubsystem armSubsystem,
            ClawSubsystem clawSubsystem,
            DriveTrainSubsystem driveTrainSubsystem,
            GyroBalanceSubsystem gyroBalanceSubsystem,
            PoseEstimationSubsystem poseEstimationSubsystem,
            ArmPneumaticSubsystem armPneumaticSubsystem
    ) {
        return new SequentialCommandGroup(
                new ClawCommand(clawSubsystem),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new DistanceDriveCommand(driveTrainSubsystem, forward),
                new ClawCommand(clawSubsystem),
                new DistanceDriveCommand(driveTrainSubsystem, back),
                new ToggleArmActuationCommand(armPneumaticSubsystem),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.GROUND),
                new DistanceDriveCommand(driveTrainSubsystem, toCubeORCone.reversed()),
                new TurnCommand(driveTrainSubsystem, rotationAmount),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.MIDDLE),
                new ToggleArmActuationCommand(armPneumaticSubsystem),
                new ClawCommand(clawSubsystem),
                new TurnCommand(driveTrainSubsystem, rotationAmount),
                new DistanceDriveCommand(driveTrainSubsystem, toCubeORCone),
                new DistanceDriveCommand(driveTrainSubsystem, forward),
                new ClawCommand(clawSubsystem),
                new ToggleArmActuationCommand(armPneumaticSubsystem),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.GROUND),
                new DriveToPoseCommand(driveTrainSubsystem, poseEstimationSubsystem, chargingStation),
                new BalanceCommand(gyroBalanceSubsystem, driveTrainSubsystem));
    }

}
