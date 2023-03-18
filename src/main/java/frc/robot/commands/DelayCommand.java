package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DelayCommand extends CommandBase {
    private long currentTime = 0;
    private long delayMilli;

    public DelayCommand(long delayMilli) {
        this.delayMilli = delayMilli;
    }

    @Override
    public void initialize() {
        currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - currentTime >= delayMilli;
    }
}
