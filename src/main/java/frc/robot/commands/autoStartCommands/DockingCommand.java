package frc.robot.commands.autoStartCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
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
                new DelayCommand(1000),
                new ParallelCommandGroup(
                        new AutoArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                        new AutonomusArmActuationCommand(armPneumaticSubsystem, true)
                ),
                new ParallelCommandGroup(
                        new DelayCommand(1000),
                        new DistanceDriveCommand(driveTrainSubsystem, Distance.inFeet(10))
                ),
                new ClawCommand(clawSubsystem),
                new ParallelCommandGroup(
                        new AutoArmMovementCommand(armSubsystem, ArmPosition.IN),
                        new AutonomusArmActuationCommand(armPneumaticSubsystem, false)
                ),
                new ParallelRaceGroup(
                        new DelayCommand(500),
                        new MecanumDriveCommand(driveTrainSubsystem, ()->0.0, ()->-7.5, ()->0.0)
                ),
                new DelayCommand(1000),
                new ParallelRaceGroup(
                        new DelayCommand(250),
                        new MecanumDriveCommand(driveTrainSubsystem, ()->0.0, ()->-1.0, ()->0.0)
                ),
                new ParallelRaceGroup(
                        new DelayCommand(500),
                        new MecanumDriveCommand(driveTrainSubsystem, ()->0.0, ()->0.8, ()->0.0)
                ),
                new BreakCommand(new BreakSubsystem()
                )
        );
    }


}
