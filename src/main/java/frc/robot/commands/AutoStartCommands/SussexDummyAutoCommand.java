package frc.robot.commands.AutoStartCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.subsystems.DriveTrainSubsystem;

public class SussexDummyAutoCommand extends SequentialCommandGroup {
    public SussexDummyAutoCommand(DriveTrainSubsystem driveTrainSubsystem){
        addCommands(
                new DistanceDriveCommand(driveTrainSubsystem,-50.0)
        );

    }
}
