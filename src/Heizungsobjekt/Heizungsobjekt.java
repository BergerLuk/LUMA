/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import Main.*;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Lukas
 */
public class Heizungsobjekt {
    public static void INIT() {
        Pumpe.ausschalten(0);
        System.out.println("Reset Pumpe");
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getPumpeInit());
            System.out.println("Init Pumpe");
        } catch (IOException ex) {
            Logger.ErrorLog("Heizungsobjekte", "SchalterReset.py fehlt");
        }
        System.out.println("Reset Knopf");
        try {
            Process p = Runtime.getRuntime().exec("python /home/pi/NetBeansProjects/LUMA/PythonScripts/SchalterTest.py");
        } catch (IOException ex) {
            Logger.ErrorLog("Knopf", "SchalterTest.py fehlt");
        }
    }
}