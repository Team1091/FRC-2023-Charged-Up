package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LightsSubsystem;

public class LightCommand extends CommandBase {
    private final LightsSubsystem lightsSubsystem;

    public LightCommand(LightsSubsystem lightsSubsystem) {
        this.lightsSubsystem = lightsSubsystem;
    }


    @Override
    public void initialize() {

    }

    @Override
   public void execute() {
        if(DriverStation.getAlliance() == DriverStation.Alliance.Blue) {
            this.lightsSubsystem.setLights(LightsSubsystem.LightColors.BLUE);
        } else if(DriverStation.getAlliance() == DriverStation.Alliance.Red){
            this.lightsSubsystem.setLights(LightsSubsystem.LightColors.RED);
        } else {
            this.lightsSubsystem.setLights(LightsSubsystem.LightColors.ORANGE);
        }

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