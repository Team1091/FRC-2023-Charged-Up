// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static double deadzone = 0.1;

    public static final class DriveTrain {
    public final static int backLeftMotorChannel = 1;
    public final static int frontLeftMotorChannel = 2;
    public final static int frontRightMotorChannel = 3;
    public final static int backRightMotorChannel = 4;
    public final static double originTolerance = 0.3;
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }


    public final static String cameraName = "Microsoft_LifeCam_HD-3000";
    public final static int cameraPixelHeight = 180;
    public final static int cameraPixelWidth = 640;
    //public final static double cameraHeightMeters = 0.2;
    public static double cameraHeightMeters = 0.74422;
    public static double cameraPitchRadians = 0.0;
    //public final static double targetHeightInMeters = 0.384175;
    public static double targetHeightInMeters = 0.8128;
    public final static double stationTargetHeightInMeters = 0.619252;

    public final static SPI.Port gyroPort = SPI.Port.kMXP;
    public final static double gyroDeadZoneThreshhold = 10;
    public final static double gyroCommandMoveSpeed = 0.25;
}
