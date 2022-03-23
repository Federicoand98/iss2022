package it.unibo.radarSystem22.mytest;

import it.unibo.radarSystem22.domain.ILed;

import java.io.IOException;

public class LedEnabler implements ILed {

    private boolean state;

    public LedEnabler() {
        this.state = false;
    }

    @Override
    public void turnOn() {
        try {
            Runtime.getRuntime().exec("./turnOn.sh");
            state = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnOff() {
        try {
            Runtime.getRuntime().exec("./turnOff.sh");
            state = false;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean getState() {
        return state;
    }
}
