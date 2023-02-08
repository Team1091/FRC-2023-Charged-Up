package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSubsystem extends SubsystemBase {

    private CANSparkMax motor;
    private DoubleSolenoid rightSolenoid;
    private DoubleSolenoid leftSolenoid;

    private boolean rightIn;
    private boolean leftIn;
    public ClawSubsystem() {
        rightSolenoid = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,
                Constants.rightInChannel,
                Constants.rightOutChannel);

        leftSolenoid = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,
                Constants.leftInChannel,
                Constants.leftOutChannel);
        rightIn = false;
        leftIn = false;
    }

    public void rightIn() {
        rightSolenoid.set(DoubleSolenoid.Value.kForward);
        rightIn=true;
    }

    public void rightOut() {
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightIn=false;
    }

    public void leftIn() {
        leftSolenoid.set(DoubleSolenoid.Value.kForward);
        leftIn=true;
    }

    public void leftOut() {
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        leftIn=false;
    }
    public boolean isRightIn() {
        return rightIn;
    }
    public boolean isLeftIn() {
        return leftIn;
    }


}
