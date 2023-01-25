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
    public ClawSubsystem() {
        motor = new CANSparkMax(Constants.armMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSolenoid = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,
                Constants.armInChannel,
                Constants.armOutChannel);

        leftSolenoid = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,
                Constants.armInChannel,
                Constants.armOutChannel);
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


}
