package it.unibo.radarSystem22.sprint2a.handlers;

import it.unibo.comm2022.ApplMsgHandler;
import it.unibo.comm2022.interfaces.IApplMsgHandler;
import it.unibo.comm2022.interfaces.Interaction2021;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.utils.ColorsOut;

public class LedApplHandler extends ApplMsgHandler {

    private ILed led;

    public LedApplHandler(String name, ILed led) {
        super(name);
        this.led = led;
    }

    public static IApplMsgHandler create(String name, ILed led) {
        return new LedApplHandler(name, led);
    }

    @Override
    public void elaborate(String s, Interaction2021 interaction2021) {
        ColorsOut.out(name + " | elaborate message = " + s + " conn = " + interaction2021, ColorsOut.GREEN);

        if(s.equals("getState")) {
            this.sendMsgToClient("" + led.getState(), interaction2021);
        } else if(s.equals("on")) {
            led.turnOn();
        } else if(s.equals("off")) {
            led.turnOff();
        }
    }
}
