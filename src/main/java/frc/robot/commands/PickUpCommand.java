package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;

public class PickUpCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;
    private boolean pickUpCube;

    public PickUpCommand(ClawSubsystem clawSubsystem, boolean pickUpCube) {
        this.clawSubsystem = clawSubsystem;
        this.pickUpCube = pickUpCube;
        addRequirements(clawSubsystem);
    }
    @Override
    public void initialize() {

    }
}
