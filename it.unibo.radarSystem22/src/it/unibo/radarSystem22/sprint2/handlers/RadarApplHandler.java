package it.unibo.radarSystem22.sprint2.handlers;

import it.unibo.comm2022.ApplMsgHandler;
import it.unibo.comm2022.interfaces.Interaction2021;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import org.json.JSONObject;

public class RadarApplHandler extends ApplMsgHandler {

    private IRadarDisplay radar;
    private int curDistance = 0;

    public RadarApplHandler(String name, IRadarDisplay radar) {
        super(name);
        this.radar = radar;
    }

    @Override
    public void elaborate(String message, Interaction2021 conn) {
        ColorsOut.out(name + " | elaborate " + message + " conn=" + conn);

        if(message.equals("getCurDistance")) {
            try {
                conn.reply("" + curDistance);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }

        JSONObject jsonObject = new JSONObject(message);
        curDistance = jsonObject.getInt("distance");
        String distance = "" + curDistance;
        String angle = "" + jsonObject.getInt("angle");

        radar.update(distance, angle);
    }
}
