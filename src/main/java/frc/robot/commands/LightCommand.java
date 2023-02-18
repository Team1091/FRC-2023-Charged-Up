package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LightsSubsystem;

public class LightCommand extends CommandBase {
    private final LightsSubsystem lightsSubsystem;

    public LightCommand(LightsSubsystem lightsSubsystem) {
        this.lightsSubsystem = lightsSubsystem;
        addRequirements(this.lightsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (DriverStation.getAlliance() == DriverStation.Alliance.Blue) {
            lightsSubsystem.setLights(LightsSubsystem.LightColors.BLUE);
            return;
        }

        if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
            lightsSubsystem.setLights(LightsSubsystem.LightColors.RED);
            return;
        }

        lightsSubsystem.setLights(LightsSubsystem.LightColors.ORANGE);
    }

    @Override
    public void end(boolean interrupted) {
        this.lightsSubsystem.setLights(LightsSubsystem.LightColors.OFF);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}