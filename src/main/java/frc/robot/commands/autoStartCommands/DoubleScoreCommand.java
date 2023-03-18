package frc.robot.commands.autoStartCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.*;
import frc.robot.subsystems.ArmPneumaticSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Distance;
import frc.robot.utils.Rotation;

import javax.management.DescriptorKey;

public class DoubleScoreCommand {
    //Score Pick Score
    private static final Distance toCubeORCone = Distance.inFeet(10.0);//TODO: Set Proper distance
    private static final Rotation rotationAmount = Rotation.inDegrees(180);

    public static Command create(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, DriveTrainSubsystem driveTrainSubsystem, ArmPneumaticSubsystem armPneumaticSubsystem) {
        return new SequentialCommandGroup(
                new ClawCommand(clawSubsystem),
                new DelayCommand(1000),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new ToggleArmActuationCommand(armPneumaticSubsystem),
                new DelayCommand(3500),
                new ParallelCommandGroup(
                        new DelayCommand(500),
                        new DistanceDriveCommand(driveTrainSubsystem, Distance.inFeet(10))
                ),
                new ClawCommand(clawSubsystem)
        );
    }
}
