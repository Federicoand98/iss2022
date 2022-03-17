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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ColorsOut.outerr("setTheConfiguration ERROR " + e.getMessage());
        }
    }
}
