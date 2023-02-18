package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CompressorSubsystem extends SubsystemBase {

    private Compressor pcmCompressor;

    public CompressorSubsystem() {
        //TODO figure out what module it is, I put 0 just cause
        pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    }

    public void disableDaCompressor() {
        pcmCompressor.disable();
    }

    public void enableDaCompressor(){
        pcmCompressor.enableDigital();
    }
}
