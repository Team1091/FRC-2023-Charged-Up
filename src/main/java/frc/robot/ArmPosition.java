package frc.robot;

public enum ArmPosition {
    IN(Constants.lowestEncoderPosition),
    GROUND(Constants.groundEncoderPosition),
    MIDDLE(Constants.middleEncoderPosition),
    HIGH(Constants.highestEncoderPosition),
    INSIDE(0);

    public final double encoderPosition;

    ArmPosition(double encoderPos) {
        encoderPosition = encoderPos;
    }
}
