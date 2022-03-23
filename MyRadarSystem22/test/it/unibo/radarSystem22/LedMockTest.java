package it.unibo.radarSystem22;

import it.unibo.radarSystem22.domain.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class LedMockTest {

    ILed led;

    @Before
    public void setUp() {
        this.led = new LedMock();
    }

    @Test
    public void testLedOn() {
        led.turnOn();
        assertTrue(led.getState());
    }

    @Test
    public void testLedOff() {
        led.turnOff();
        assertTrue(!led.getState());
    }
}
