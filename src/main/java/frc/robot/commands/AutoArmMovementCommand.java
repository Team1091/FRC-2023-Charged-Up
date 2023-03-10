// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ArmPosition;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import org.jetbrains.annotations.NotNull;

public class AutoArmMovementCommand extends CommandBase {

    @NotNull
    private final ArmSubsystem armSubsystem;
    @NotNull
    private final ArmPosition automatic;

    // Auto
    public AutoArmMovementCommand(@NotNull ArmSubsystem subsystem, @NotNull ArmPosition automatic) {
        armSubsystem = subsystem;
        this.automatic = automatic;
        addRequirements(armSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        switch (automatic) {
            case HIGH:
                if (Math.abs(armSubsystem.getMotorPosition() - ArmPosition.HIGH.encoderPosition) <
                        Constants.armEncoderThreshold) {
                    if (armSubsystem.getMotorPosition() > ArmPosition.HIGH.encoderPosition) {
                        armSubsystem.setMotor(0.5);
                    } else {
                        armSubsystem.setMotor(-0.5);
                    }
                }
                break;
            case MIDDLE:
                if (Math.abs(armSubsystem.getMotorPosition() - ArmPosition.MIDDLE.encoderPosition) <
                        Constants.armEncoderThreshold) {
                    if (armSubsystem.getMotorPosition() > ArmPosition.MIDDLE.encoderPosition) {
                        armSubsystem.setMotor(0.5);
                    } else {
                        armSubsystem.setMotor(-0.5);
                    }
                }
                break;
            case GROUND:
                if (Math.abs(armSubsystem.getMotorPosition() - ArmPosition.GROUND.encoderPosition) <
                        Constants.armEncoderThreshold) {
                    if (armSubsystem.getMotorPosition() > ArmPosition.GROUND.encoderPosition) {
                        armSubsystem.setMotor(0.5);
                    } else {
                        armSubsystem.setMotor(-0.5);
                    }
                }
                break;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        armSubsystem.setMotor(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
