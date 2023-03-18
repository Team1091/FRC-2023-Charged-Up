package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSubsystem extends SubsystemBase {

    private final DoubleSolenoid clawSolenoid;
    public ClawSubsystem() {
        clawSolenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.clawInChannel,
                Constants.clawOutChannel);
    }

    public void clawIn() {
        clawSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void clawOut() {
        clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public  boolean isClawIn(){
        return clawSolenoid.get() == DoubleSolenoid.Value.kForward;
    }
}

