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
                
        if(i == 0)
            Logger.log(2);
        else
            Logger.log(4);
    }
    
    public static void ausschalten(int i) {
        try {
            Process p = Runtime.getRuntime().exec("python " + INI.getPumpeAusPath());
        } catch (IOException ex) {
            Logger.ErrorLog("Pumpe", "Aus.py fehlt");
        }
        istAn = false;
        
        if(i == 0)
            Logger.log(3);
        else
            Logger.log(5);
    }
}
