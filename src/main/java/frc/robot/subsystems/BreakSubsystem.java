package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BreakSubsystem extends SubsystemBase {

    private CANSparkMax motor;
    private DoubleSolenoid solenoid;

    private boolean in;
    public BreakSubsystem() {
        solenoid = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,
                Constants.breakWheelIn,
                Constants.breakWheelOut);

        in = true;
    }

    public void rightIn() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        in =true;
    }

    public void rightOut() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        in = false;
    }

    public boolean isIn() {
        return in;
    }



}
