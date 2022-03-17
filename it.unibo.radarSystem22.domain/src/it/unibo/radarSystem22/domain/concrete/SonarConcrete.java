package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SonarConcrete extends SonarModel implements ISonar {

    private Process p;
    private BufferedReader reader;
    private IDistance curVal;

    @Override
    protected void sonarSetUp() {
        curVal = new Distance(90);
    }

    @Override
    public void activate() {
        ColorsOut.out("SonarConcrete | activate");

        if(p == null) {
            try {
                p = Runtime.getRuntime().exec("sudo ./SonarAlone");
                reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                ColorsOut.out("SonarConcrete | sonarSetUp done");
            } catch (Exception e) {
                e.printStackTrace();
                ColorsOut.outerr("SonarConcrete | sonarSetUp ERROR " + e.getMessage());
            }
        }

        super.activate();
    }

    @Override
    protected void sonarProduce() {
        try {
            String data = reader.readLine();

            if(data == null) {
                return;
            }

            int v = Integer.parseInt(data);
            ColorsOut.out("SonarConcrete | v=" + v);
            int lastSonarVal = curVal.getVal();

            if(lastSonarVal != v && v < DomainSystemConfig.sonarDistanceMax) {
                updateDistance(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ColorsOut.outerr("SonarConcrete | " + e.getMessage());
        }
    }

    @Override
    public void deactivate() {
        ColorsOut.out("SonarConcrete | deactivate", ColorsOut.GREEN);

        curVal = new Distance(90);

        if(p != null) {
            p.destroy();
            p = null;
        }

        super.deactivate();
    }
}
