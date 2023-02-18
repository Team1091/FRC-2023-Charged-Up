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
    private DoubleSolenoid breakSolenoid;
    private double motorSpeed = 0;

    public ArmSubsystem() {
        motor = new CANSparkMax(Constants.armMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushed);
        solenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.armInChannel,
                Constants.armOutChannel);
        breakSolenoid = new DoubleSolenoid(
                Constants.armBreakPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.motorBreakIn,
                Constants.motorBreakOut);
        setArmBreak(true);
    }

    public void armIn() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void armOut() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setArmBreak(boolean engageBreak) {
        if (engageBreak) {
            breakSolenoid.set(DoubleSolenoid.Value.kForward);
            return;
        }

        breakSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isBreakEngaged() {
        return breakSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public boolean isArmIn() {
        return solenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public void setMotor(double speed) {
        motorSpeed = speed;
    }

    @Override
    public void periodic() {
        motor.set(motorSpeed);
    }

    public double getMotorPosition() {
        return motor.getEncoder().getPosition();
    }
}
