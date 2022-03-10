package it.unibo.radarSystem22.domain.models;

import it.unibo.radarSystem22.domain.ILed;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class LedModel implements ILed {

    private boolean state = false;

    public static ILed create() {
        ILed led;

        if(DomainSystemConfig.simulation) {

        } else {
            
        }
    }

}
