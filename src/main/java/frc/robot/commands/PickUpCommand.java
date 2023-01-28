package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.ColorSubsystem;

public class PickUpCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    private final ColorSubsystem colorSubsystem;
    private boolean pickUpCube;

    public PickUpCommand(ClawSubsystem clawSubsystem, ColorSubsystem colorSubsystem, boolean pickUpCube) {
        this.clawSubsystem = clawSubsystem;
        this.colorSubsystem = colorSubsystem;
        this.pickUpCube = pickUpCube;
        addRequirements(clawSubsystem);
    }
    @Override
    public void initialize() {

    }

    @Override
    public void execute(){
        if (colorSubsystem.isCone()) {
            clawSubsystem.rightIn();
            clawSubsystem.leftIn();
        } else if (colorSubsystem.isCube()){
            clawSubsystem.leftIn();
        } else{
            System.out.println("NO OBJECT");
        }
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}
