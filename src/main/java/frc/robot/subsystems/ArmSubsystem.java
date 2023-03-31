package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

    private final CANSparkMax motor;

    private final DoubleSolenoid breakSolenoid;
    private double targetSpeed = 0;

    private final Encoder encoder;

    private final DigitalInput lowSwitch;
    private final DigitalInput highSwitch;
    private double deviation = 0;

    //private long lastTimeToggled;


    public ArmSubsystem() {
        motor = new CANSparkMax(Constants.armMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushed);

        encoder = new Encoder(1, 2);


        breakSolenoid = new DoubleSolenoid(
                Constants.everythingPcm,
                PneumaticsModuleType.CTREPCM,
                Constants.motorBreakIn,
                Constants.motorBreakOut);
        setArmBreak(true);
        //lastTimeToggled = System.currentTimeMillis();
        lowSwitch = new DigitalInput(Constants.minSwitchChannel);
        highSwitch = new DigitalInput(Constants.maxSwitchChannel);
        encoder.reset();
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



    public void setMotor(double speed) {
//        if (!lowSwitch.get()) {
//            encoder.reset();
//            deviation = 0;
//            if (speed < 0) {
//                targetSpeed = 0;
//                return;
//            }
//        }
//
//        if (!highSwitch.get()) {
//            deviation = encoder.get() - Constants.maxArmPosEncoder;
//            if (speed > 0) {
//                targetSpeed = 0;
//                return;
//            }
//        }

        targetSpeed = speed;
//        SmartDashboard.putNumber("Actual Arm Speed", speed);
    }

    @Override
    public void periodic() {
        double motorSpeed = targetSpeed;
        if (!lowSwitch.get()) {
            encoder.reset();
            deviation = 0;
            if (targetSpeed < 0) {
                motorSpeed = 0;
            }
        }

        if (highSwitch.get()) {
            deviation = encoder.get() - Constants.highestEncoderPosition;
            if (targetSpeed > 0) {
                motorSpeed = 0;
            }
        }

        if (encoder.get() >= Constants.maxEncoderValue && targetSpeed > 0) {
            motorSpeed = 0;
        }

        motor.set(motorSpeed);
        SmartDashboard.putNumber("Arm Encoder value", getMotorPosition());
        SmartDashboard.putBoolean("High Switch", highSwitch.get());
        SmartDashboard.putBoolean("Low Switch", lowSwitch.get());

    }

    public double getMotorPosition() {
        return encoder.getDistance() - deviation;
    }
}
