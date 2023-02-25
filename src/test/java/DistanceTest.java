import frc.robot.utils.Distance;
import org.junit.jupiter.api.Test;

public class DistanceTest {

    @Test
    public void testInches() {
        assert almostEqual(Distance.inInches(12).toFeet(), 1.0);
    }


    @Test
    public void testFeet() {
        assert almostEqual(Distance.inFeet(3.2808399).toMeters(), 1.0);
    }


    private static final double eps = 0.00001;

    public static boolean almostEqual(double a, double b) {
        return Math.abs(a - b) < eps;
    }

}
