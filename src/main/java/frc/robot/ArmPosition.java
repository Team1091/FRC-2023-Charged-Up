package frc.robot;

public enum ArmPosition {
    GROUND (Constants.groundEncoderPosition),
    MIDDLE(Constants.middleEncoderPosition),
    HIGH(Constants.highestEncoderPosition),
    INSIDE(0);

    public final double encoderPosition;

    ArmPosition(double encoderPos) {
        encoderPosition = encoderPos;
    }
}
