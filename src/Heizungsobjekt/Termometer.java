/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import java.io.*;
import Main.*;
import java.util.logging.Level;

/**
 *
 * @author Lukas
 */
public class Termometer extends Heizungsobjekt {
    
    static String Kennung = "28-05170013b3ff";
    
    private static int Temperatur;

    public static int getTemperatur() {

        try {
            FileReader fr;
            fr = new FileReader("/sys/bus/w1/devices/" + Kennung + "/w1_slave");
            BufferedReader br = new BufferedReader(fr);
            
            String zeile1 = br.readLine();
            String Temp = br.readLine();
            Temp = Temp.substring(29);
            Temperatur = Integer.valueOf(Temp) / 1000;
            //System.out.println(Temperatur);
            br.close();
            
            return Temperatur;
        } catch (IOException ex) {
            Logger.ErrorLog("Termometer" + Kennung, "Temperaturdatei fehlt");
            return 30;
        }
    }
}
