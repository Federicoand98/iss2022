package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.IDistance;
import it.unibo.radarSystem22.domain.ISonar;

public class SonarMock implements ISonar {

    private boolean isRunning = false;

    public SonarMock() {

    }

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
