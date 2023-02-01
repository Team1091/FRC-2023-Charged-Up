package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.GamePieceType;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ColorSubsystem;

import java.lang.reflect.Type;

public class ClawCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    private final ColorSubsystem colorSubsystem;
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

        if (closeClaw){
            if(colorSubsystem.getGamePiece() == GamePieceType.CONE){
                clawSubsystem.rightIn();
                clawSubsystem.leftIn();
            } else if (colorSubsystem.getGamePiece() == GamePieceType.CUBE){
                clawSubsystem.rightIn();
            } else if(colorSubsystem.getGamePiece() == GamePieceType.NONE){
                //do nothing
            }
        } else {
            clawSubsystem.rightOut();
            clawSubsystem.leftOut();
        }


    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
