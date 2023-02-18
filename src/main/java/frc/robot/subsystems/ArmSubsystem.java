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
    private double motorSpeed;

    private boolean armIn = true;


    public ArmSubsystem() {
        motor = new CANSparkMax(Constants.armMotorChannel, CANSparkMaxLowLevel.MotorType.kBrushed);
        solenoid = new DoubleSolenoid( Constants.everythingPcm, PneumaticsModuleType.CTREPCM,
                Constants.armInChannel,
                Constants.armOutChannel);
        breakSolenoid = new DoubleSolenoid(Constants.armBreakPcm, PneumaticsModuleType.CTREPCM, Constants.motorBreakIn, Constants.motorBreakOut);
        motorSpeed=0;
       // armIn();
        setArmBreak(true);
    }

    public void armIn() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        armIn = true;
    }

    public void armOut() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        armIn = false;
    }

    public void setArmBreak(boolean engageBreak){
        if (engageBreak){
            breakSolenoid.set(DoubleSolenoid.Value.kForward);
        }else {
            breakSolenoid.set(DoubleSolenoid.Value.kReverse);
        }

    }

    public boolean isBreakEngaged(){
        return breakSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public  boolean isArmIn(){
        return armIn;
    }
    public void setMotor(double speed) {
        motorSpeed = speed;
    }

    @Override
    public void periodic() {
        motor.set(motorSpeed);
    }

    public double getMotorPosition(){
        return motor.getEncoder().getPosition();
    }


}
