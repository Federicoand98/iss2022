package it.unibo.radarSystem22.domain.models;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.concrete.SonarConcrete;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.mock.SonarMock;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class SonarModel implements ISonar {

    protected boolean stopped = false;
    private IDistance curVal = new Distance(90);

    public static ISonar create() {
        if(DomainSystemConfig.simulation) {
            return createSonarMock();
        } else {
            return createConcreteMock();
        }
    }

    public static ISonar createSonarMock() {
        ColorsOut.out("createSonarMock", ColorsOut.BLUE);
        return new SonarMock();
    }

    public static ISonar createConcreteMock() {
        ColorsOut.out("createSonarConcrete", ColorsOut.BLUE);
        return new SonarConcrete();
    }

    protected SonarModel() {
        ColorsOut.out("SonarModel | calling (specialized) sonarSetUp", ColorsOut.BLUE);
        sonarSetUp();
    }

    protected void updateDistance(int d) {
        curVal = new Distance(d);
        ColorsOut.out("SonarModel | updateDistance " + d, ColorsOut.BLUE);
    }

    protected abstract void sonarSetUp();
    protected abstract void sonarProduce();

    @Override
    public boolean isActive() {
        return !stopped;
    }

    @Override
    public IDistance getDistance() {
        return curVal;
    }

    @Override
    public void activate() {
        curVal = new Distance(90);
        ColorsOut.out("SonarModel | activate");

        stopped = false;

        new Thread() {
            public void run() {
                while(!stopped) {
                    sonarProduce();
                }
            }
        }.start();
    }

    @Override
    public void deactivate() {
        ColorsOut.out("SonarModel | deactivate", ColorsOut.BgYellow );
        stopped = true;
    }
}
