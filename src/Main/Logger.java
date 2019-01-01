package Main;

import Heizungsobjekt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Logger {

    static SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd;HH:mm");
    static String uhrzeit = date.format(new Date());
    static String LogPath = INI.getLogPath();

    public static void log(int i, String Gerät, String Status) {
        date = new SimpleDateFormat("yyyy.MM.dd;HH:mm");
        uhrzeit = date.format(new Date());
        BufferedWriter writer = null;
        String timeLog = LogPath + "/" + DateTime.getyear() + "/"
        try {
            writer = new BufferedWriter(new FileWriter(INI.getLogPath(), true));
            if ((i == 0)) {
                writer.append("\nNew Log\nStatus;Datum;Uhrzeit;Einheit;Daten;\n");
            } else if (INI.getLogPrio() <= i) {
                writer.append("LOG; " + uhrzeit + ";" + Gerät + ";" + Status + ";\n");
                GUI.Uebersicht.newLog("LOG; " + uhrzeit + ";" + Gerät + ";" + Status + ";\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.ErrorLog("Logger", "Log funktioniert nicht");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void ErrorLog(String Name, String Fehler) {
        BufferedWriter writer = null;
        try {
            System.out.println("Error");
            
            writer = new BufferedWriter(new FileWriter(INI.getLogPath(), true));
            writer.append("ERROR;" + uhrzeit + ";" + Name + ";" + Fehler + ";\n");
            writer.close();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Log Error");
        }
    }
}
