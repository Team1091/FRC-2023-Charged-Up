package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BreakSubsystem extends SubsystemBase {

    private DoubleSolenoid solenoid;

    private boolean in;
    public BreakSubsystem() {
        solenoid = new DoubleSolenoid( Constants.everythingPcm, PneumaticsModuleType.CTREPCM,
                Constants.breakWheelIn,
                Constants.breakWheelOut);
        rightOut();
        in = true;
    }

    public void rightIn() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        in = true;
    }

    public void rightOut() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        in = false;
    }

    public boolean isIn() {
        return in;
    }



}
