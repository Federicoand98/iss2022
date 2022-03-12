package it.unibo.radarSystem22.domain;

import it.unibo.radarSystem22.domain.interfaces.IDistance;

public class Distance implements IDistance {

    private int v;

    public Distance(int v) {
        this.v = v;
    }

    public Distance(String d) {
        this.v = Integer.parseInt(d);
    }

    @Override
    public int getVal() {
        return v;
    }

    @Override
    public String toString() {
        return "" + v;
    }
}
