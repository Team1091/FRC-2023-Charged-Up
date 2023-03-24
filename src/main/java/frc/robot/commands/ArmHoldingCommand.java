package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.subsystems.ArmPneumaticSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class ArmHoldingCommand extends CommandBase {

        public static Command create(ArmSubsystem armSubsystem, ArmPneumaticSubsystem armPneumaticSubsystem){
            if (!armPneumaticSubsystem.isArmIn()) {
                return (new SequentialCommandGroup(
                        new ParallelCommandGroup(

                                new ToggleArmActuationCommand(armPneumaticSubsystem),
                                new AutoArmMovementCommand(armSubsystem, ArmPosition.HOLDING))

                ));
            }
            return new SequentialCommandGroup(
                    new AutoArmMovementCommand(armSubsystem, ArmPosition.HOLDING)
            );
        }
}
