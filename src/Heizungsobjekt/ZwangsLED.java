/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import Main.*;
import java.io.IOException;

/**
 *
 * @author root
 */
public class ZwangsLED {

    public static void ausschalten() {
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getKnopfLampeAusPath());
        } catch (IOException ex) {
            Logger.ErrorLog("Pumpe", "KnopfLampeAus.py fehlt");
        }
    }

    public static void anschalten() {
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getKnopfLampeAnPath());
        } catch (IOException ex) {
            Logger.ErrorLog("Pumpe", "KnopfLampeAn.py fehlt");
        }

    }
}
