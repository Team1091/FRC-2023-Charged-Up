package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class NewClawCommand extends CommandBase {

    private ClawSubsystem clawSubsystem;

    public NewClawCommand(ClawSubsystem clawSubsystem){
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
        clawSubsystem.leftOut();
        clawSubsystem.rightOut();
    }

    @Override
    public void end(boolean interrupted) {
        clawSubsystem.leftIn();
        clawSubsystem.rightIn();
    }

    @Override
    public boolean isFinished() {
        return false;

    }

}
