package it.unibo.radarSystem22.domain.models;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class LedModel implements ILed {

    private boolean state = false;

    public static ILed create() {
        ILed led;

        if(DomainSystemConfig.simulation) {
            return led = createLedMock();
        } else {
            return led = createLedConcrete();
        }
    }

    public static ILed createLedMock() {
        return new LedMock();
    }

    public static ILed createLedConcrete() {
        //to do
        return null;
    }

    protected abstract void ledActivate(boolean val);

    protected void setState(boolean val) {
        this.state = val;
        ledActivate(val);
    }

    @Override
    public void turnOn() {
        setState(true);
    }

    @Override
    public void turnOff() {
        setState(false);
    }

    @Override
    public boolean getState() {
        return this.state;
    }
}
