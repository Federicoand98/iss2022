package it.unibo.radarSystem22;

import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import jdk.jfr.internal.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.rmi.CORBA.Util;

import static org.junit.Assert.assertTrue;

public class TestLed {

    @Before
    public void up() {
        System.out.println("up");
    }

    @After
    public void down() {
        System.out.println("down");
    }

    @Test
    public void testLedMock() {
        DomainSystemConfig.simulation = true;

        ILed led = DeviceFactory.createLed();

        assertTrue(!led.getState());

        led.turnOn();
        assertTrue(led.getState());
        BasicUtils.delay(1000);

        led.turnOff();
        assertTrue(!led.getState());
        BasicUtils.delay(1000);
    }
}
