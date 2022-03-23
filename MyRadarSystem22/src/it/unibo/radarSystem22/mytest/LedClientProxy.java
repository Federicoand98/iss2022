package it.unibo.radarSystem22.mytest;

import it.unibo.radarSystem22.domain.ILed;
import it.unibo.radarSystem22.interfaces.Interaction;

public class LedClientProxy implements ILed {

    private Interaction interaction;

    public LedClientProxy(Interaction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void turnOn() {
        try {
            interaction.forward("turnOn");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnOff() {
        try {
            interaction.forward("turnOff");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getState() {
        try {
            String res = interaction.request("state");

            if(res == "on") {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
