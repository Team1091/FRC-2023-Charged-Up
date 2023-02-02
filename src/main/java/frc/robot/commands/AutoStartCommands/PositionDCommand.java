package frc.robot.commands.AutoStartCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.commands.ArmMovementCommand;
import frc.robot.commands.ClawCommand;
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.commands.TurnCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Rotation;

public class PositionDCommand extends SequentialCommandGroup {
    //Score Pick Score
    public PositionDCommand(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, DriveTrainSubsystem driveTrainSubsystem){
        addCommands( new ArmMovementCommand(armSubsystem, ArmPosition.HIGH,driveTrainSubsystem),
                new ClawCommand(clawSubsystem, colorSubsystem, false),
                new ArmMovementCommand(armSubsystem, ArmPosition.GROUND, driveTrainSubsystem),//change Ground to In
                new DistanceDriveCommand(driveTrainSubsystem, -100000.0),//Set Proper distance
                new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(180).toRadians()),
                new ArmMovementCommand(armSubsystem, ArmPosition.MIDDLE, driveTrainSubsystem),
                new ClawCommand(clawSubsystem, colorSubsystem, true),
                new TurnCommand(driveTrainSubsystem, Rotation.inDegrees(180).toRadians()),
                new DistanceDriveCommand(driveTrainSubsystem, 100000.0),//Set Proper distance
                new ArmMovementCommand(armSubsystem, ArmPosition.GROUND, driveTrainSubsystem), //change Ground to In
                new ClawCommand(clawSubsystem, colorSubsystem, false));

    }
}
