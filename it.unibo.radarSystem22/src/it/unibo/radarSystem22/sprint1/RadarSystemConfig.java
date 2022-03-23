package it.unibo.radarSystem22.sprint1;

import it.unibo.radarSystem22.domain.utils.ColorsOut;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class RadarSystemConfig {
    public static boolean tracing = false;
    public static boolean testing = false;
    public static int DLIMIT = 15;
    public static boolean RadarGuiRemote = false;
    public static String hostAddr         = "localhost";
    public static String raspAddr         = "localhost";
    public static int serverPort          = 8023;
    public static int ledPort             = 8010;
    public static int sonarPort           = 8015;

    public static void setTheConfiguration() {
        setTheConfiguration("../RadarSystemConfig.json");
    }

    public static void setTheConfiguration(String resourceName) {
        FileInputStream fis = null;

        try {
            ColorsOut.out("%%% setTheConfiguration from file: " + resourceName);

            if(fis == null) {
                fis = new FileInputStream(new File(resourceName));
            }

            Reader reader = new InputStreamReader(fis);
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject object = new JSONObject(tokener);

            tracing = object.getBoolean("tracing");
            testing = object.getBoolean("testing");
            RadarGuiRemote = object.getBoolean("RadarGuiRemote");
            DLIMIT = object.getInt("DLIMIT");
            serverPort = object.getInt("serverPort");
            hostAddr = object.getString("hostAddr");
            raspAddr = object.getString("raspAddr");
            ledPort = object.getInt("ledPort");
            sonarPort = object.getInt("sonarPort");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ColorsOut.outerr("setTheConfiguration ERROR " + e.getMessage());
        }
    }
}
