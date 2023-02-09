// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.BreakSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class BreakCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final BreakSubsystem breakSubsystem;



    public BreakCommand(BreakSubsystem breakSubsystem) {
        this.breakSubsystem = breakSubsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(breakSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!breakSubsystem.isIn()){
            breakSubsystem.rightIn();
        }else{
            breakSubsystem.rightOut();
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
