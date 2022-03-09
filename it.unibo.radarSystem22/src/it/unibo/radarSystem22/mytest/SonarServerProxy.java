package it.unibo.radarSystem22.mytest;

import it.unibo.radarSystem22.domain.IDistance;
import it.unibo.radarSystem22.domain.ISonar;

public class SonarServerProxy implements ISonar {

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public IDistance getDistance() {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
