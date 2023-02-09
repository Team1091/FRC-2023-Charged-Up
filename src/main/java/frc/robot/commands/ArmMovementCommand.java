// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ArmPosition;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;


public class ArmMovementCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ArmSubsystem armSubsystem;
    private ArmPosition automatic;
    private double armMotorSpeed;


    public ArmMovementCommand(ArmSubsystem subsystem, double speed) {
        armSubsystem = subsystem;
        automatic = null; //will respond to joystick commands
        armMotorSpeed = speed;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(armSubsystem);
    }

    public ArmMovementCommand(ArmSubsystem subsystem, ArmPosition automatic) {
        armSubsystem = subsystem;
        this.automatic = automatic; //1 is lowest position, 2 is middle position, 3 is highest position
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(armSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (automatic == null) {
            armSubsystem.setMotor(armMotorSpeed);
            return;
        }


        switch (automatic){
            case HIGH:
                if (Math.abs(armSubsystem.getMotorPosition() - ArmPosition.HIGH.encoderPosition) < Constants.armEncoderThreshold) {
                    if (armSubsystem.getMotorPosition() > ArmPosition.HIGH.encoderPosition) {
                        armSubsystem.setMotor(0.5);
                    } else {
                        armSubsystem.setMotor(-0.5);
                    }
                }
                break;
            case MIDDLE:
                if (Math.abs(armSubsystem.getMotorPosition() - ArmPosition.MIDDLE.encoderPosition) < Constants.armEncoderThreshold) {
                    if (armSubsystem.getMotorPosition() > ArmPosition.MIDDLE.encoderPosition) {
                        armSubsystem.setMotor(0.5);
                    } else {
                        armSubsystem.setMotor(-0.5);
                    }
                }
                break;
            case GROUND:
                if (Math.abs(armSubsystem.getMotorPosition() - ArmPosition.GROUND.encoderPosition) < Constants.armEncoderThreshold) {
                    if (armSubsystem.getMotorPosition() > ArmPosition.GROUND.encoderPosition) {
                        armSubsystem.setMotor(0.5);
                    } else {
                        armSubsystem.setMotor(-0.5);
                    }
                }
                break;
        }

    }

    public void moveArmDownSlightly() {
//        double pastEncoderPos = armSubsystem.getMotorPosition();
//        while (armSubsystem.getMotorPosition() > (pastEncoderPos + Constants.armMoveDownEncoderTicks)) {
//            armSubsystem.setMotor(-0.1);
//        }
//
//        armSubsystem.setMotor(0.0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
