/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import Main.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Lukas
 */
public class Knopf extends Heizungsobjekt {

    private static boolean KnopfAn = false;

    public static boolean Knopfan() {
        int k = 0;
        String Knopf = "0";
        try {
        FileReader fr = new FileReader("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt");
        BufferedReader br = new BufferedReader(fr);
        Knopf = br.readLine();
        br.close();
        fr.close();
        }
        catch (IOException exc) {
            Logger.ErrorLog("Knopf", "KnopfDruck.txt fehlt");
        }
        try {
            k = Integer.valueOf(Knopf);

        } catch (NumberFormatException test) {
            Logger.ErrorLog(Knopf, "Knopfdruck lese Fehler");
            
        }
        if (k == 1) {
            KnopfAn = true;
            return true;
        } else {
            KnopfAn = false;
            return false;
        }
    }

    public static boolean getKnopfAn() {
        return KnopfAn;
    }

}