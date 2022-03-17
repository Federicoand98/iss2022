package it.unibo.radarSystem22;

import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SonarConsumerTest extends Thread{

    private ISonar sonar;
    private int delta;

    public SonarConsumerTest(ISonar sonar, int delta) {
        this.sonar = sonar;
        this.delta = delta;
    }

    @Override
    public void run() {
        int v0 = sonar.getDistance().getVal();

        while(sonar.isActive()) {
            BasicUtils.delay(DomainSystemConfig.sonarDelay / 2);

            IDistance d = sonar.getDistance();
            int v = d.getVal();
            int vExpectedMin = v0 - delta;
            int vExpectedMax = v0 + delta;

            assertTrue(v <= vExpectedMax && v >= vExpectedMin);

            v0 = v;
        }
    }

    @Test
    public void testSonarMock() {
        DomainSystemConfig.simulation = true;
        DomainSystemConfig.sonarDelay = 10;
        int delta = 1;

        ISonar sonar = DeviceFactory.createSonar();
        new SonarConsumerTest(sonar, delta).start();

        sonar.activate();

        while(sonar.isActive()) {
            BasicUtils.delay(100);
        }
    }
}
