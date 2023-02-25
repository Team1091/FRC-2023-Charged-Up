package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

    private final CANSparkMax motor;
    private final DoubleSolenoid solenoid;
    private final DoubleSolenoid breakSolenoid;
    private double motorSpeed = 0;

    private final Encoder encoder;

    //private long lastTimeToggled;


    public ArmSubsystem() {
        motor = new CANSparkMax(Constants.armMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushed);

        encoder = new Encoder(1, 2);

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
        //lastTimeToggled = System.currentTimeMillis();
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
//        if (System.currentTimeMillis() < lastTimeToggled + 500) {
//            if (engageBreak) {
//                breakSolenoid.set(DoubleSolenoid.Value.kForward);
//                return;
        }
        breakSolenoid.set(DoubleSolenoid.Value.kReverse);
//
//            breakSolenoid.set(DoubleSolenoid.Value.kReverse);
//            lastTimeToggled = System.currentTimeMillis();
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
        SmartDashboard.putNumber("Arm Encoder value", getMotorPosition());
    }

    public double getMotorPosition() {
        return encoder.getDistance();
    }
}
