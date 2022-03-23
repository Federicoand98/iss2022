package it.unibo.radarSystem22.sprint2.proxy;

import it.unibo.comm2022.ProtocolType;
import it.unibo.comm2022.proxy.ProxyAsClient;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.utils.ColorsOut;

public class RadarGuiProxyAsClient extends ProxyAsClient implements IRadarDisplay {

    public RadarGuiProxyAsClient(String name, String host, String entry, ProtocolType protocol) {
        super(name, host, entry, protocol);
    }

    @Override
    public void update(String s, String s1) {
        String msg = "{ \"distance\" : D , \"angle\" : A}".replace("D", s).replace("A", s1);

        try {
            sendCommandOnConnection(msg);
        } catch (Exception e) {
            ColorsOut.outerr(name + " | update ERROR: " + e.getMessage());
        }
    }

    @Override
    public int getCurDistance() {
        String answer = sendRequestOnConnection("getCurrDistance");
        return Integer.parseInt(answer);
    }
}
