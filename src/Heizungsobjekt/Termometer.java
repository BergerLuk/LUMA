/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import java.io.*;
import Main.*;

/**
 *
 * @author Lukas
 */
public class Termometer extends Heizungsobjekt {
    
    private static int Temperatur;
    

    public static int getTemperatur() {

        try {
            FileReader fr;
            fr = new FileReader(INI.getThermoPath());
            BufferedReader br = new BufferedReader(fr);
            
            String zeile1 = br.readLine();
            String Temp = br.readLine();
            Temp = Temp.substring(29);
            Temperatur = Integer.valueOf(Temp) / 1000;
            //System.out.println(Temperatur);
            br.close();
            
            return Temperatur;
        } catch (IOException ex) {
            Logger.ErrorLog("Termometer", "Temperaturdatei fehlt");
            return 30;
        }
    }
}
