package it.unibo.radarSystem22.sprint1;

import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.usecases.LedAlarmUsecase;
import it.unibo.radarSystem22.sprint1.usecases.RadarGuiUsecase;

public class Controller {

    private ILed led;
    private ISonar sonar;
    private IRadarDisplay radar;
    private ActionFunction endFunction;

    public static Controller create(ILed led, ISonar sonar, IRadarDisplay radar) {
        return new Controller(led, sonar, radar);
    }

    public static Controller create(ILed led, ISonar sonar) {
        return new Controller(led, sonar, DeviceFactory.createRadarGui());
    }

    private Controller(ILed led, ISonar sonar, IRadarDisplay radar) {
        this.led = led;
        this.sonar = sonar;
        this.radar = radar;
    }

    public void start(ActionFunction endFunction, int limit) {
        this.endFunction = endFunction;

        ColorsOut.outappl("Controller | start with endFun=" + endFunction, ColorsOut.GREEN);
        sonar.activate();
        activate(limit);
    }

    protected void activate(int limit) {
        new Thread() {
            public void run() {
                BasicUtils.aboutThreads("Controller activated |");

                try {
                    boolean sonarActive = sonar.isActive();

                    if(sonarActive) {
                        for(int i = 1; i <= limit; i++) {
                            IDistance d = sonar.getDistance();
                            ColorsOut.outappl("Controller | d=" + d + "i=" + i, ColorsOut.GREEN);

                            if(radar != null) {
                                RadarGuiUsecase.doUseCase(radar, d);
                            }

                            LedAlarmUsecase.doUseCase(led, d);
                            BasicUtils.delay(DomainSystemConfig.sonarDelay);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ColorsOut.outerr("Error: " + e.getMessage());
                }
            }
        }.start();
    }
}
