// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmPneumaticSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomusArmActuationCommand extends CommandBase {

    private final ArmPneumaticSubsystem armPneumaticSubsystem;
    private final boolean out;

    public AutonomusArmActuationCommand(ArmPneumaticSubsystem armPneumaticSubsystem, boolean out) {
        this.out = out;
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
        if (out) {
            armPneumaticSubsystem.armOut();
        }else{
            armPneumaticSubsystem.armIn();
        }

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
