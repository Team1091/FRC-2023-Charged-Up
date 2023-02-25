package frc.robot.commands.autoStartCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.AutoArmMovementCommand;
import frc.robot.commands.ClawCommand;
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.commands.TurnCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Rotation;

public class PositionBCommand {
    //Score Pick Dock
    private static final double toCubeORCone = 100000.0;//Set Proper distance
    private static final double rotationAmount = 180;

    public static Command create(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, DriveTrainSubsystem driveTrainSubsystem) {
        return new SequentialCommandGroup(new AutoArmMovementCommand(armSubsystem, ArmPosition.HIGH),
                new ClawCommand(clawSubsystem, false),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.IN),
                new DistanceDriveCommand(driveTrainSubsystem, -toCubeORCone),
                new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(rotationAmount).toRadians()),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.GROUND),
                new ClawCommand(clawSubsystem, true),
                new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(rotationAmount).toRadians()),
                new DistanceDriveCommand(driveTrainSubsystem, toCubeORCone),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.MIDDLE),
                new ClawCommand(clawSubsystem, false),
                new AutoArmMovementCommand(armSubsystem, ArmPosition.IN)
        );
    }
}
