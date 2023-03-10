package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;

public class ClawCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    private final boolean closeClaw;

    public ClawCommand(ClawSubsystem clawSubsystem, boolean closeClaw) {
        this.clawSubsystem = clawSubsystem;
        this.closeClaw = closeClaw;

        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (!closeClaw) {
            clawSubsystem.clawOut();
            return;
        }

        clawSubsystem.clawIn();

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
