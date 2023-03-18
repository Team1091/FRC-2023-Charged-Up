package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmPneumaticSubsystem extends SubsystemBase {
    private final DoubleSolenoid solenoid;
    public ArmPneumaticSubsystem(){
        solenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.armInChannel,
                Constants.armOutChannel);
    }

    public void armIn() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void armOut() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isArmIn() {
        return solenoid.get() == DoubleSolenoid.Value.kForward;
    }
}
