package Main;

import Heizungsobjekt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Logger {

    static SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd;HH:mm");
    static String uhrzeit = date.format(new Date());

    public static void log(int i) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(INI.getLogPath(), true));

            if ((i == 0)) {
                writer.append("\nNew Log\nStatus;Datum;Uhrzeit;Einheit;Daten;\n");
            } else if ((i == 1) && (INI.getLogPrio() <= i)) {
                writer.append("LOG;" + uhrzeit + ";Pumpe;" + Pumpe.getIstAn() + ";\n");
                writer.append("LOG;" + uhrzeit + ";Termometer1;" + Termometer.getTemperatur() + ";\n");
            } else if ((i == 2) && (INI.getLogPrio() <= i)) {
                writer.append("LOG;" + uhrzeit + ";Pumpe;Angeschaltet;\n");
            } else if ((i == 3) && (INI.getLogPrio() <= i)) {
                writer.append("LOG;" + uhrzeit + ";Pumpe;Ausgeschaltet;\n");
            } else if ((i == 4) && (INI.getLogPrio() <= i)) {
                writer.append("LOG;" + uhrzeit + ";Pumpe;AngeschaltetKnopf;\n");
            } else if ((i == 5) && (INI.getLogPrio() <= i)) {
                writer.append("LOG;" + uhrzeit + ";Pumpe;AusgeschaltetKnopf;\n");
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
        try {
            BufferedWriter writer = null;
            
            writer = new BufferedWriter(new FileWriter(INI.getLogPath(), true));
            writer.append("ERROR;" + uhrzeit + ";" + Name + ";" + Fehler + ";\n");

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Log Error");
        }
    }
}
