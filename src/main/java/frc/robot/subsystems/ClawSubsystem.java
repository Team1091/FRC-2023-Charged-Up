package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSubsystem extends SubsystemBase {

    private DoubleSolenoid rightSolenoid;
    private DoubleSolenoid leftSolenoid;

    public ClawSubsystem() {
        rightSolenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.rightInChannel,
                Constants.rightOutChannel);
        leftSolenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.leftInChannel,
                Constants.leftOutChannel);
        rightOut();
        leftOut();
    }

    public void rightIn() {
        rightSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void rightOut() {
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void leftIn() {
        leftSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void leftOut() {
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isRightIn() {
        return rightSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public boolean isLeftIn() {
        return leftSolenoid.get() == DoubleSolenoid.Value.kForward;
    }
}
