
package Main;

import java.io.*;
import java.util.Properties;

public class INI {
    private static final String SkriptPath = "/home/pi/NetBeansProjects/LUMA/PythonScripts/";
    private static int AnschaltTemperatur = 65;
    private static int AusschaltTemperatur = 60;
    private static int log = 2;
    private static String LogPath = "/home/pi/Documents/LUMA/Log/";
    private static long Sleep = 600000;
    private static String PumpeInit = SkriptPath + "SchalterReset.py";
    private static String TermoPath = "/sys/bus/w1/devices/";
    private static String PumpeAusPath = SkriptPath + "Aus.py";
    private static String PumpeAnPath = SkriptPath + "An.py";
    private static String KnopfLampeAusPath = SkriptPath + "KnopfLampeAus.py";
    private static String KnopfLampeAnPath = SkriptPath + "KnopfLampeAn.py";
    private static String KnopfDruckPath = SkriptPath + "KnopfDruck.txt";
    private static String GUISchalterPath = SkriptPath + "GUISchalter.py";
    
    public static void initINI() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("/home/pi/Documents/LUMA/LUMA.ini"));
            AnschaltTemperatur = Integer.parseInt(p.getProperty("AnTemp","65"));
            AusschaltTemperatur = Integer.parseInt(p.getProperty("AusTemp","60"));
            log = Integer.parseInt(p.getProperty("LogLevel","2"));
            LogPath = p.getProperty("LogPath","/home/pi/Documents/LUMA/Log/");
            Sleep = Long.parseLong(p.getProperty("Sleep","30")) * 1000;
            PumpeInit = SkriptPath + p.getProperty("PumpeInit","SchalterReset.py");
            TermoPath = SkriptPath + p.getProperty("ThermoPath","/sys/bus/w1/devices/");
            PumpeAusPath = SkriptPath +p.getProperty("PumpeAusPath","Aus.py");
            PumpeAnPath =  SkriptPath + p.getProperty("PumpeAnPath","An.py");
            KnopfDruckPath = SkriptPath + p.getProperty("KnopfDruckPath","KnopfDruck.txt");
            KnopfLampeAusPath = SkriptPath + p.getProperty("KnopfLampeAusPath","KnopfLampeAus.py");
            KnopfLampeAnPath = SkriptPath + p.getProperty("KnopfLampeAnPath","KnopfLampeAn.py);
            GUISchalterPath = SkriptPath + p.getProperty("GUISchalterPath","GUISchalter.py");
        } catch (IOException ex) {
            Logger.ErrorLog("INI", "INI-Datei fehlt");
        }
        
        
    }
    
    public static String getGUISchalterPath() {
        return GUISchalterPath;
    }
    public static String getLogPath() {
        return LogPath;
    }
    public static String getKnopfLampeAnPath() {
        return KnopfLampeAnPath;
    }
    public static String getKnopfLampeAusPath() {
        return KnopfLampeAusPath;
    }
    public static String getPumpeAusPath() {
        return PumpeAusPath;
    }
    public static String getKnopfDruckPath() {
        return KnopfDruckPath;
    }
    public static String getPumpeAnPath() {
        return PumpeAnPath;
    }
    public static String getTermoPath() {
        return TermoPath;
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
    public static String getPumpeInit() {
        return PumpeInit;
    }
}
