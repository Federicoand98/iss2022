package it.unibo.radarSystem22.main;

import it.unibo.radarSystem22.domain.ILed;
import it.unibo.radarSystem22.interfaces.Interaction;
import it.unibo.radarSystem22.mytest.LedClientProxy;
import it.unibo.radarSystem22.mytest.TCPInteraction;

import java.io.IOException;

public class LedTestMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        Interaction interaction = new TCPInteraction("172.20.10.5", 6666);
        ILed led = new LedClientProxy(interaction);
        Controller controller = new Controller(led);

        controller.run();
    }
}
