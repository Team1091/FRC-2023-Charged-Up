import frc.robot.utils.Rotation;
import org.junit.jupiter.api.Test;

public class RotationTest {

    @Test
    public void testRadians() {
        assert DistanceTest.almostEqual(Rotation.inRadians(1).toDegrees(), 57.2957795);
    }

}
