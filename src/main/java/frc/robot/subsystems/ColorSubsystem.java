package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.GamePieceType;

public class ColorSubsystem extends SubsystemBase {

    private PhotonColorVisionSubsystem photonColorVisionSubsystem;
    private GamePieceType gamePieceType;

    public ColorSubsystem(PhotonColorVisionSubsystem photonColorVisionSubsystem) {
        this.photonColorVisionSubsystem = photonColorVisionSubsystem;
        gamePieceType = GamePieceType.NONE;
    }

    @Override
    public void periodic() {
        int id = photonColorVisionSubsystem.getColoredObjectID();
        switch(id) {
            case 0:
                gamePieceType = GamePieceType.NONE;
                break;
            case 1:
                gamePieceType = GamePieceType.CUBE;
                break;
            case 2:
                gamePieceType = GamePieceType.CONE;
                break;
        }
    }

    public GamePieceType getGamePiece() {
        int id = photonColorVisionSubsystem.getColoredObjectID();
        switch(id) {
            case 0:
                gamePieceType = GamePieceType.NONE;
                break;
            case 1:
                gamePieceType = GamePieceType.CUBE;
                break;
            case 2:
                gamePieceType = GamePieceType.CONE;
                break;
        }

       return  gamePieceType;
   }

}
