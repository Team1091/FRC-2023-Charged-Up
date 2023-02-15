package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.PidMotorController;

public class DriveTrainSubsystem extends SubsystemBase {

    private final MecanumDrive mecanumDrive;
    private final RelativeEncoder frontLeftEncoder;
    private final RelativeEncoder frontRightEncoder;
    private final RelativeEncoder backLeftEncoder;
    private final RelativeEncoder backRightEncoder;
    double forwardBackwardVelocity, strafeVelocity, rotationVelocity = 0;

    private final boolean PIDToggle = false;

    public DriveTrainSubsystem() {
        var frontLeftMotor = new CANSparkMax(
                Constants.DriveTrain.frontLeftMotorChannel,
                CANSparkMaxLowLevel.MotorType.kBrushless
        );
        var backLeftMotor = new CANSparkMax(
                Constants.DriveTrain.backLeftMotorChannel,
                CANSparkMaxLowLevel.MotorType.kBrushless
        );
        var frontRightMotor = new CANSparkMax(
                Constants.DriveTrain.frontRightMotorChannel,
                CANSparkMaxLowLevel.MotorType.kBrushless
        );
        var backRightMotor = new CANSparkMax(
                Constants.DriveTrain.backRightMotorChannel,
                CANSparkMaxLowLevel.MotorType.kBrushless
        );

        frontRightMotor.setInverted(true);
        backRightMotor.setInverted(true);

        frontLeftEncoder = frontLeftMotor.getEncoder();
        frontRightEncoder = frontRightMotor.getEncoder();
        backLeftEncoder = backLeftMotor.getEncoder();
        backRightEncoder = backRightMotor.getEncoder();

        if (PIDToggle) {
            var maxSpeed = 1000.0;
            mecanumDrive = new MecanumDrive(
                    new PidMotorController("fl", frontLeftMotor, maxSpeed, 0.01, 0.01, 0),
                    new PidMotorController("bl", backLeftMotor, maxSpeed, 0.01, 0.01, 0),
                    new PidMotorController("fr", frontRightMotor, maxSpeed, 0.01, 0.01, 0),
                    new PidMotorController("br", backRightMotor, maxSpeed, 0.01, 0.01, 0)
            );
        } else {
            mecanumDrive = new MecanumDrive(
                    frontLeftMotor,
                    backLeftMotor,
                    frontRightMotor,
                    backRightMotor
            );
        }

    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("F/B", forwardBackwardVelocity);
        SmartDashboard.putNumber("Strafe", strafeVelocity);
        SmartDashboard.putNumber("Rotation", rotationVelocity);
        mecanumDrive.driveCartesian(
                forwardBackwardVelocity,
                strafeVelocity,
                rotationVelocity);
    }

    public void mecanumDrive(double strafeVelocity, double forwardBackwardVelocity, double rotationVelocity) {
        this.strafeVelocity = strafeVelocity;
        this.forwardBackwardVelocity = forwardBackwardVelocity;
        this.rotationVelocity = rotationVelocity;
    }


    public double getFrontLeftEncoder() {
        return frontLeftEncoder.getPosition();
    }

    public double getFrontRightEncoder() {
        return frontRightEncoder.getPosition();
    }

    public MecanumDriveWheelPositions getWheelPositions() {

        return new MecanumDriveWheelPositions(
                getEncoderDistance(frontLeftEncoder.getPosition()),
                getEncoderDistance(frontRightEncoder.getPosition()),
                getEncoderDistance(backLeftEncoder.getPosition()),
                getEncoderDistance(backRightEncoder.getPosition())
        );
    }

    private double getEncoderDistance(double rotations) {
        return rotations / Constants.meterToRealMeter;
//        return Math.PI * inchesToMeters(Constants.DriveTrain.wheelDiameterInches) * rotations;
    }
}
