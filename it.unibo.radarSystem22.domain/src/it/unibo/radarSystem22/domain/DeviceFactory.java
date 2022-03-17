package it.unibo.radarSystem22.domain;

import it.unibo.radarSystem22.domain.concrete.RadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.models.LedModel;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class DeviceFactory {

    public static ILed createLed() {
        if(DomainSystemConfig.simulation) {
            return LedModel.createLedMock();
        } else {
            return LedModel.createLedConcrete();
        }
    }

    public static ISonar createSonar(boolean observable) {
        ColorsOut.out("DeviceFactory | createSonar simulated="+DomainSystemConfig.simulation, ColorsOut.GREEN);
        return createSonar();
    }

    public static ISonar createSonar() {
        if(DomainSystemConfig.simulation) {
            return SonarModel.createSonarMock();
        } else {
            return SonarModel.createConcreteMock();
        }
    }

    public static IRadarDisplay createRadarGui() {
        return RadarDisplay.getRadarDisplay();
    }
}
