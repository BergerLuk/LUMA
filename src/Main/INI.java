
package Main;

import java.io.*;
import java.util.Properties;
import Main.*;

public class INI {
    private static int AnschaltTemperatur = 65;
    private static int AusschaltTemperatur = 60;
    private static int log = 2;
    private static String LogPath = "/home/pi/Documents/LUMA/log.xls";
    private static long Sleep = 600000;
    
    public static void initINI() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("/home/pi/Documents/LUMA/LUMA.ini"));
            AnschaltTemperatur = Integer.parseInt(p.getProperty("AnTemp"));
            AusschaltTemperatur = Integer.parseInt(p.getProperty("AusTemp"));
            log = Integer.parseInt(p.getProperty("LogLevel"));
            LogPath = p.getProperty("LogPath");
            Sleep = Long.parseLong(p.getProperty("Sleep")) * 1000;
        } catch (IOException ex) {
            Logger.ErrorLog("INI", "INI-Datei fehlt");
        }
        
        
    }
    
    public static String getLogPath() {
        return LogPath;
    }
    public static int getAnTemp() {
        return AnschaltTemperatur;
    }
    public static int getAusTemp() {
        return AusschaltTemperatur;
    }
    public static int getLogPrio() {
        //0 = Log
        //2 = Action
        //4 = Error
        return log;
    }
    public static long getSleep() {
        return Sleep;
    }
}
