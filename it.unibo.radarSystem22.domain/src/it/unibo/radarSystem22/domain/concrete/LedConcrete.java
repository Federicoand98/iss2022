package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.models.LedModel;

import java.io.IOException;

public class LedConcrete extends LedModel implements ILed {

    private Runtime runtime = Runtime.getRuntime();

    @Override
    protected void ledActivate(boolean val) {
        try {
            if(val) {
                runtime.exec("sudo bash led25GpioTurnOn.sh");
            } else {
                runtime.exec("sudo bash led25GpioTurnOff.sh");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
