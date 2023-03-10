package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSubsystem extends SubsystemBase {

    private final Solenoid clawSolenoid;
    public ClawSubsystem() {
        clawSolenoid = new Solenoid(
                Constants.clawInChannel,
                PneumaticsModuleType.CTREPCM,
                Constants.clawOutChannel);
        clawOut();
    }

    public void clawIn() {
        clawSolenoid.set(false);
    }

    public void clawOut() {
        clawSolenoid.set(true);
    }
}
