package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ColorSubsystem;

public class ClawCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    private final boolean cone = true;
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
            clawSubsystem.rightOut();
            clawSubsystem.leftOut();
            return;
        }

        if (cone) {//colorSubsystem.getGamePiece() == GamePieceType.CONE){
            clawSubsystem.rightIn();
            clawSubsystem.leftIn();
        }

        if (!cone) {//colorSubsystem.getGamePiece() == GamePieceType.CUBE){
            clawSubsystem.rightIn();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
