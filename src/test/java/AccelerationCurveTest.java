import frc.robot.AccelerationCurve;
import org.junit.jupiter.api.Test;

public class AccelerationCurveTest {

    @Test
    public void testAccel() throws InterruptedException {

        var acc = new AccelerationCurve(1, 0.3, 0.6);

        assert acc.getCurrentSpeed() == 0.0;



        while(true){
            acc.set(1.0);
            System.out.println(acc.getCurrentSpeed());
            Thread.sleep(100);
        }

    }

}
