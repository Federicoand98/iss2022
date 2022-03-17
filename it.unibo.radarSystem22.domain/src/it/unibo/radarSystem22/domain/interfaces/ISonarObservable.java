package it.unibo.radarSystem22.domain.interfaces;

public interface ISonarObservable extends ISonar {

    void register(IObserver o);
    void unregister(IObserver o);
}
