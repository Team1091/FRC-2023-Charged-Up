package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BreakSubsystem extends SubsystemBase {

    private DoubleSolenoid solenoid;

    public BreakSubsystem() {
        solenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.breakWheelIn,
                Constants.breakWheelOut);
        rightOut();
        
    }

    public void rightIn() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void rightOut() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public boolean isIn() {
        return solenoid.get() == DoubleSolenoid.Value.kReverse;
    }
}
