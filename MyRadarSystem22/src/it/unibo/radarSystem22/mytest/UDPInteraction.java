package it.unibo.radarSystem22.mytest;

import it.unibo.radarSystem22.interfaces.Interaction;

public class UDPInteraction implements Interaction {


    @Override
    public void forward(String msg) throws Exception {

    }

    @Override
    public String request(String msg) throws Exception {
        return null;
    }

    @Override
    public String receiveMsg() throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
