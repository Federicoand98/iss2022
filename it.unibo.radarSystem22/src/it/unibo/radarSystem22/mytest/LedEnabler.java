package it.unibo.radarSystem22.mytest;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import it.unibo.radarSystem22.domain.ILed;
import com.pi4j.*;

public class LedEnabler implements ILed {

    private static final int PIN_LED = 17;
    private Context pi4j;
    private DigitalOutput led = null;

    public LedEnabler() {
        this.pi4j = Pi4J.newAutoContext();
        DigitalOutputConfigBuilder config = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("LED Flasher")
                .address(PIN_LED)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");
        this.led = pi4j.create(config);
    }

    @Override
    public void turnOn() {
        if(led.equals(DigitalState.LOW)) {
            led.high();
            System.out.println("LED high");
        }
    }

    @Override
    public void turnOff() {
        if(led.equals(DigitalState.HIGH)) {
            led.low();
            System.out.println("LED low");
        }
    }

    @Override
    public boolean getState() {
        return led.isHigh() ? true : false;
    }
}
