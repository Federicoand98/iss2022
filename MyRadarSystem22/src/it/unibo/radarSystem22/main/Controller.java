package it.unibo.radarSystem22.main;

import eu.hansolo.steelseries.extras.Led;
import it.unibo.radarSystem22.domain.IDistance;
import it.unibo.radarSystem22.domain.ILed;
import it.unibo.radarSystem22.domain.IRadarDisplay;
import it.unibo.radarSystem22.domain.ISonar;

public class Controller {
    private final int LIMIT = 10;

    private ISonar sonar;
    private ILed led;
    private IRadarDisplay radarDisplay;
    private boolean state = false;

    public Controller(ISonar sonar, ILed led, IRadarDisplay radarDisplay) {
        this.sonar = sonar;
        this.led = led;
        this.radarDisplay = radarDisplay;
    }

    public Controller(ILed led) {
        this.led = led;
    }

    public void run() throws InterruptedException {

        while(true) {
            if(state) {
                led.turnOff();
                state = false;
            } else {
                led.turnOn();
                state = true;
            }

            Thread.sleep(500);
        }

        /*
        while(sonar.isActive()) {
            IDistance distance = sonar.getDistance();

            if(distance.getVal() < LIMIT) {
                led.turnOn();
            }  else {
                led.turnOff();
            }

            radarDisplay.udpdate("" + distance.getVal(), "90");
        }
        */
    }
}
