package it.unibo.radarSystem22.domain;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.models.LedModel;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class DeviceFactory {

    public static ILed createLed() {
        if(DomainSystemConfig.simulation) {
            return LedModel.createLedMock();
        } else {
            return LedModel.createLedConcrete();
        }
    }

    public static ISonar createSonar() {
        // to do
        return null;
    }

    public IRadarDisplay createRadarGui() {
        // to do
        return null;
    }
}
