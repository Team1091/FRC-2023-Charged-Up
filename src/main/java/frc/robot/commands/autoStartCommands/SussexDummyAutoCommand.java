package frc.robot.commands.autoStartCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.utils.Distance;

public class SussexDummyAutoCommand extends SequentialCommandGroup {
    public SussexDummyAutoCommand(DriveTrainSubsystem driveTrainSubsystem) {
        addCommands(
                new DistanceDriveCommand(driveTrainSubsystem, Distance.inInches(-25.0)),
                new DistanceDriveCommand(driveTrainSubsystem, Distance.inInches(50.0))
        );

    }
}
