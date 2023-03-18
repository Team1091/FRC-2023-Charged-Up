package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.ArmPosition;
import frc.robot.subsystems.ArmPneumaticSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class ArmResetCommand extends CommandBase {

        public static Command create(ArmSubsystem armSubsystem, ArmPneumaticSubsystem armPneumaticSubsystem){
            if (!armPneumaticSubsystem.isArmIn()&& armSubsystem.getMotorPosition()>0) {
                return (new SequentialCommandGroup(
                        new AutoArmMovementCommand(armSubsystem, ArmPosition.MIDDLE),
                        new ToggleArmActuationCommand(armPneumaticSubsystem),
                        new AutoArmMovementCommand(armSubsystem, ArmPosition.IN)

                ));
            }
            return new SequentialCommandGroup();
        }
}
