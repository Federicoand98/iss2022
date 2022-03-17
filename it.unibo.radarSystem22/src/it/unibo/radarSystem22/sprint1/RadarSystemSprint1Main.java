package it.unibo.radarSystem22.sprint1;

import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class RadarSystemSprint1Main implements IApplication {

    private IRadarDisplay radarDisplay;
    private ISonar sonar;
    private ILed led;
    private Controller controller;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public void setup(String domainConfig, String systemConfig) {
        BasicUtils.aboutThreads("Before setup");

        if(domainConfig != null) {
            DomainSystemConfig.setTheConfiguration(domainConfig);
        }

        if(systemConfig != null) {
            RadarSystemConfig.setTheConfiguration(systemConfig);
        }

        if(domainConfig == null && systemConfig == null) {
            DomainSystemConfig.testing = false;
            DomainSystemConfig.sonarDelay = 200;
            // pc
            DomainSystemConfig.simulation = true; // rasp false
            DomainSystemConfig.ledGui = true; // rasp false
            RadarSystemConfig.DLIMIT = 70; // rasp 12
            RadarSystemConfig.RadarGuiRemote = false; // rasp true
        }
    }

    @Override
    public void doJob(String domainConfig, String systemConfig) {
        BasicUtils.aboutThreads("Before doJob |");
        setup(domainConfig, systemConfig);
        configure();
        BasicUtils.waitTheUser();

        ActionFunction endFun = (n) -> {
            System.out.println(n);
            terminate();
        };

        controller.start(endFun, 30);
    }

    protected void configure() {
        led = DeviceFactory.createLed();
        radarDisplay = RadarSystemConfig.RadarGuiRemote ? null : DeviceFactory.createRadarGui();
        BasicUtils.aboutThreads("Before Controller creation |");
        sonar = DeviceFactory.createSonar();
        controller = Controller.create(led, sonar, radarDisplay);
    }

    public void terminate() {
        BasicUtils.aboutThreads("Before deactivation |");
        sonar.deactivate();
        System.exit(0);
    }

    public IRadarDisplay getRadarDisplay() {
        return radarDisplay;
    }

    public ISonar getSonar() {
        return sonar;
    }

    public ILed getLed() {
        return led;
    }

    public Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        //pc
        //BasicUtils.aboutThreads("At init no config files |");
        //new RadarSystemSprint1Main().doJob(null, null);

        // rasp
        BasicUtils.aboutThreads("At init with CONFIG files |");
        new RadarSystemSprint1Main().doJob("DomainSystemConfig.json", "RadarSystemConfig.json");
    }
}
