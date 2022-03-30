package it.unibo.radarSystem22.sprint2a.proxy;

import it.unibo.comm2022.ApplMsgHandler;
import it.unibo.comm2022.ProtocolType;
import it.unibo.comm2022.interfaces.IApplMsgHandler;
import it.unibo.comm2022.interfaces.Interaction2021;
import it.unibo.comm2022.proxy.ProxyAsClient;
import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.sprint2a.handlers.LedApplHandler;

public class SonarProxyAsClient extends ProxyAsClient implements ISonar {

    public SonarProxyAsClient(String name, String host, String entry, ProtocolType protocol) {
        super(name, host, entry, protocol);
    }

    @Override
    public void activate() {
        sendCommandOnConnection("activate");
    }

    @Override
    public void deactivate() {
        sendCommandOnConnection("deactivate");
        super.close();
    }

    @Override
    public IDistance getDistance() {
        String answer = sendRequestOnConnection("getDistance");
        ColorsOut.out(name + " | getDistance answer = " + answer, ColorsOut.ANSI_PURPLE);
        return new Distance(Integer.parseInt(answer));
    }

    @Override
    public boolean isActive() {
        String answer = sendRequestOnConnection("isActive");
        ColorsOut.out(name + " | isActive answer = " + answer, ColorsOut.ANSI_PURPLE);
        return answer.equals("true");
    }
}
