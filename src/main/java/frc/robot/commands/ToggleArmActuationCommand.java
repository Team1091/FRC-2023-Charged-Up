// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmPneumaticSubsystem;
import frc.robot.subsystems.ArmSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class ToggleArmActuationCommand extends CommandBase {

    private final ArmPneumaticSubsystem armPneumaticSubsystem;

    public ToggleArmActuationCommand(ArmPneumaticSubsystem armPneumaticSubsystem) {
        this.armPneumaticSubsystem = armPneumaticSubsystem;
        addRequirements(armPneumaticSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (armPneumaticSubsystem.isArmIn()) {
            armPneumaticSubsystem.armOut();
            return;
        }

        armPneumaticSubsystem.armIn();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
