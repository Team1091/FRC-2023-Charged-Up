// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ManualArmMovementCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    @NotNull
    private final ArmSubsystem armSubsystem;
    @NotNull private final Supplier<Double> armMotorSpeed;

    public ManualArmMovementCommand(@NotNull ArmSubsystem subsystem, @NotNull Supplier<Double> speed) {
        armSubsystem = subsystem;
        armMotorSpeed = speed;
        addRequirements(armSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        armSubsystem.setMotor(armMotorSpeed.get());
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
