package it.unibo.radarSystem22.sprint2.main.sysOnRasp;

import it.unibo.comm2022.ProtocolType;
import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.ActionFunction;
import it.unibo.radarSystem22.sprint1.Controller;
import it.unibo.radarSystem22.sprint1.RadarSystemConfig;
import it.unibo.radarSystem22.sprint2.proxy.RadarGuiProxyAsClient;

public class RadarSysSprint2ControllerOnRaspMain implements IApplication {

    private ISonar sonar;
    private ILed led;
    private IRadarDisplay radar;
    private Controller controller;

    @Override
    public void doJob(String domainConfig, String systemConfig) {
        setup(domainConfig, systemConfig);
        configure();
        execute();
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
            DomainSystemConfig.simulation = true;
            DomainSystemConfig.testing = false;
            DomainSystemConfig.tracing = false;
            DomainSystemConfig.sonarDelay = 200;
            DomainSystemConfig.ledGui = true;

            RadarSystemConfig.RadarGuiRemote = true;
            RadarSystemConfig.serverPort = 8080;
            RadarSystemConfig.hostAddr = "localhost";
            RadarSystemConfig.DLIMIT = 75;
        }
    }

    protected void configure() {
        sonar = DeviceFactory.createSonar();
        led = DeviceFactory.createLed();
        radar = new RadarGuiProxyAsClient("radarPxy", RadarSystemConfig.hostAddr, "" + RadarSystemConfig.serverPort, ProtocolType.tcp);
        controller = Controller.create(led, sonar, radar);
    }

    protected void execute() {
        ActionFunction endFun = (n) -> {
            System.out.println(n);
            terminate();
        };

        int d = radar.getCurDistance();
        ColorsOut.outappl("Current Distance answer = " + d, ColorsOut.MAGENTA);
        controller.start(endFun, 30);
    }

    public void terminate() {
        int d = radar.getCurDistance();
        ColorsOut.outappl("Current Distance answer = " + d, ColorsOut.MAGENTA);
        sonar.deactivate();
        System.exit(0);
    }

    @Override
    public String getName() {
        return null;
    }

    public static void main(String[] args) {
        BasicUtils.aboutThreads("At INIT with NO CONFIG files| ");
        new RadarSysSprint2ControllerOnRaspMain().doJob(null,null);
    }
}
