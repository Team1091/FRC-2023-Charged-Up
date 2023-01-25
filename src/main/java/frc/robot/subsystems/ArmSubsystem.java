package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

    private CANSparkMax motor;
    private DoubleSolenoid solenoid;
    public ArmSubsystem() {
        motor = new CANSparkMax(Constants.armMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushless);
        solenoid = new DoubleSolenoid( PneumaticsModuleType.CTREPCM,
                Constants.armInChannel,
                Constants.armOutChannel);
    }

    public void armIn() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void armOut() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }


}
