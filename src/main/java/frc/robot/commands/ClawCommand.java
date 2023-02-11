package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.GamePieceType;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ColorSubsystem;

import java.lang.reflect.Type;

public class ClawCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    private final ColorSubsystem colorSubsystem;

    private final boolean cone = false;
    private boolean closeClaw;
    public ClawCommand(ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, boolean closeClaw) {
        this.clawSubsystem = clawSubsystem;
        this.colorSubsystem = colorSubsystem;
        this.closeClaw = closeClaw;

        addRequirements(clawSubsystem);
    }
    @Override
    public void initialize() {

    }

    @Override
    public void execute(){
        if (!closeClaw){
            clawSubsystem.rightOut();
            clawSubsystem.leftOut();
            return;
        }

        if(cone){//colorSubsystem.getGamePiece() == GamePieceType.CONE){
            clawSubsystem.rightIn();
            clawSubsystem.leftIn();
        }

        if (!cone){//colorSubsystem.getGamePiece() == GamePieceType.CUBE){
            clawSubsystem.rightIn();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
