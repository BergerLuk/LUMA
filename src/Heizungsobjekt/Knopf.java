/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import Main.*;
import java.io.*;

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
            FileReader fr = new FileReader(INI.getKnopfDruckPath());
            BufferedReader br = new BufferedReader(fr);
            Knopf = br.readLine();
            br.close();
            fr.close();
        } catch (IOException exc) {
            Logger.ErrorLog("Knopf", "KnopfDruck.txt fehlt");
        }
        try {
            k = Integer.valueOf(Knopf);

        } catch (NumberFormatException test) {
            Logger.ErrorLog(Knopf, "Knopfdruck lese Fehler");

        }
        if (k % 2 == 1) {
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

    public static void KnopfDruecken() {
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getGUISchalterPath());
        } catch (IOException ex) {
            Logger.ErrorLog("Knopf", "GUISchalterPath.py fehlt");
        }

    }

}
