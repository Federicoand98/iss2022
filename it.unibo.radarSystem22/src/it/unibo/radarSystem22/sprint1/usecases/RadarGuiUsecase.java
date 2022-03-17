package it.unibo.radarSystem22.sprint1.usecases;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;

public class RadarGuiUsecase {

    public static void doUseCase(IRadarDisplay radar, IDistance d) {
        if(radar != null) {
            int v = d.getVal();
            radar.update("" + v, "30");
        }
    }
}
