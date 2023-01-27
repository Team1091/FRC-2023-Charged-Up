// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class ArmMovementCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ArmSubsystem armSubsystem;
    private int automatic;
    private double armMotorSpeed;
    private DriveTrainSubsystem driveTrainSubsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ArmMovementCommand(ArmSubsystem subsystem, double speed) {
        armSubsystem = subsystem;
        this.automatic = 0; //will respond to joystick commands
        armMotorSpeed = speed;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(armSubsystem);
    }

    public ArmMovementCommand(ArmSubsystem subsystem, int automatic, DriveTrainSubsystem driveTrainSubsystem) {
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
        if (automatic == 0) {
            armSubsystem.setMotor(armMotorSpeed);
        }

        switch (automatic){
            case 1:
                if(armSubsystem.getMotorPosition() > Constants.groundEncoderPosition){
                    armSubsystem.setMotor(-0.5);
                }else if(armSubsystem.getMotorPosition() < Constants.groundEncoderPosition){
                    armSubsystem.setMotor(0.5);
                }else{
                    //do nothing
                }
                break;
            case 2:
                if(armSubsystem.getMotorPosition() > Constants.middleEncoderPosition){
                    armSubsystem.setMotor(-0.5);
                }else if(armSubsystem.getMotorPosition() < Constants.middleEncoderPosition){
                    armSubsystem.setMotor(0.5);
                }else{
                    //do nothing
                }
                break;
            case 3:
                if(armSubsystem.getMotorPosition() > Constants.highestEncoderPosition){
                    armSubsystem.setMotor(-0.5);
                }else if(armSubsystem.getMotorPosition() < Constants.highestEncoderPosition){
                    armSubsystem.setMotor(0.5);
                }else{
                    //do nothing
                }
                break;
        }

    }

    public void moveArmDownSlightly() {
        double pastEncoderPos = armSubsystem.getMotorPosition();
        while (armSubsystem.getMotorPosition() > (pastEncoderPos + Constants.armMoveDownEncoderTicks)) {
            armSubsystem.setMotor(-0.1);
        }

        armSubsystem.setMotor(0.0);
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
