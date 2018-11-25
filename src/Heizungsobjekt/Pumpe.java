/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heizungsobjekt;

import Main.*;
import java.io.*;
import java.util.logging.Level;

/**
 *
 * @author Lukas
 */
public class Pumpe extends Heizungsobjekt{
    
    private static boolean istAn = false;
    
    public static boolean getIstAn() {
        return istAn;
    }
    
    public static void anschalten(int i) {
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getPumpeAnPath());
        } catch (IOException ex) {
            Logger.ErrorLog("Pumpe", "An.py fehlt");
        }
        istAn = true;
        
        if (GUI.Uebersicht.istda)
            GUI.Uebersicht.editText("Pumpe ist An");
        
        if(i == 0)
            Logger.log(2, "Pumpe", "Angeschaltet");
        else
            Logger.log(2, "Pumpe", "AngeschaltetKnopf");
    }
    
    public static void ausschalten(int i) {
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getPumpeAusPath());
        } catch (IOException ex) {
            Logger.ErrorLog("Pumpe", "Aus.py fehlt");
        }
        istAn = false;
        
        if (GUI.Uebersicht.istda)
            GUI.Uebersicht.editText("Pumpe ist Aus");
        
        if(i == 0)
            Logger.log(2, "Pumpe", "Ausgeschaltet");
        else
            Logger.log(2, "Pumpe", "AusgeschaltetKnopf");
    }
}