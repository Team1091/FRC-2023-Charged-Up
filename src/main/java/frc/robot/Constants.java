// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.utils.Distance;
import frc.robot.utils.Rotation;

import static edu.wpi.first.math.util.Units.inchesToMeters;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants { //put final here
    public static final double DEAD_ZONE = 0.1;

    public final static int armMotorChannel = 5;

    public static final class DriveTrain {
        public final static int backLeftMotorChannel = 1;
        public final static int frontLeftMotorChannel = 2;
        public final static int frontRightMotorChannel = 3;
        public final static int backRightMotorChannel = 4;
        public final static double originTolerance = 0.3;
        public final static double wheelDiameterInches = 8;
        public final static int ticksPerRotation = 10;

        /**
         * The left-to-right distance between the drivetrain wheels
         * <p>
         * Should be measured from center to center.
         */
        public static final double DRIVETRAIN_TRACK_WIDTH_METERS = inchesToMeters(21.0);
        /**
         * The front-to-back distance between the drivetrain wheels.
         * <p>
         * Should be measured from center to center.
         */
        public static final double DRIVETRAIN_WHEELBASE_METERS = Distance.inInches(21.0).toMeters();

        public static final MecanumDriveKinematics KINEMATICS = new MecanumDriveKinematics(
                // Front left
                new Translation2d(DRIVETRAIN_WHEELBASE_METERS / 2.0, DRIVETRAIN_TRACK_WIDTH_METERS / 2.0),
                // Front right
                new Translation2d(DRIVETRAIN_WHEELBASE_METERS / 2.0, -DRIVETRAIN_TRACK_WIDTH_METERS / 2.0),
                // Back left
                new Translation2d(-DRIVETRAIN_WHEELBASE_METERS / 2.0, DRIVETRAIN_TRACK_WIDTH_METERS / 2.0),
                // Back right
                new Translation2d(-DRIVETRAIN_WHEELBASE_METERS / 2.0, -DRIVETRAIN_TRACK_WIDTH_METERS / 2.0)
        );
    }

    public static class OperatorConstants {
        public static final int K_DRIVER_CONTROLLER_PORT = 0;
    }

    /**
     * Physical location of the camera on the robot, relative to the center of the robot.
     */
    public static final Transform3d CAMERA_TO_ROBOT =
            new Transform3d(new Translation3d(-0.3425, 0.0, -0.233), new Rotation3d());
    public final static String cameraName = "Microsoft_LifeCam_HD-3000";
    public final static int cameraPixelHeight = 180;
    public final static int cameraPixelWidth = 640;
    //public final static double cameraHeightMeters = 0.2;
    public static final Distance cameraHeight = Distance.inMeters(.249);
    public static final Rotation cameraPitch = Rotation.inDegrees(0.0);
    public final static Distance targetHeight = Distance.inMeters(.384175);
    //public static double targetHeightInMeters = 0.8128;
    public final static Distance stationTargetHeight = Distance.inMeters(.619252);
    public final static SPI.Port gyroPort = SPI.Port.kMXP;
    public final static double gyroDeadZoneThreshold = 10;
    public final static double gyroCommandMoveSpeed = 0.25;

    public final static double meterToRealMeter = 19.5243986;

    //pneumatic channel numbers
    public final static int everythingPcm = 1;

    public final static int breakWheelIn = 0;
    public final static int breakWheelOut = 1;
    public final static int armInChannel = 2;
    public final static int armOutChannel = 3;
    public final static int clawInChannel = 4;
    public final static int clawOutChannel = 5;
    public final static int motorBreakIn = 6;
    public final static int motorBreakOut = 7;

    public final static double lowestEncoderPosition = 0;
    public final static double groundEncoderPosition = 120;
    public final static double middleEncoderPosition = 500;
    public final static double highestEncoderPosition = 622;
    public final static double lowCubePosition = 50;

    public final static double maxEncoderValue = 650;

    public final static double holdingEncoderPosition = 70;

    public final static double autoEncoderPosition = 30;

    public final static double armEncoderThreshold = 10.0;
    public final static double armAutomaticMotorSpeed = 0.5;

    public final static double armMoveDownEncoderTicks = 1;

    //public final static double maxArmPosEncoder = 700;
    public final static double minArmPosEncoder = 0;
    public final static int maxSwitchChannel = 8;
    public final static int minSwitchChannel = 9;
}
