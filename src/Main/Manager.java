package Main;

import Heizungsobjekt.*;
import java.io.IOException;
import java.util.logging.Level;

public class Manager {

    public static void Steuerung() {
        int i = 0;
        Logger.log(0);
        Heizungsobjekt.INIT();
        INI.initINI();

        while (true) {
            if (!Knopf.getKnopfAn()) {
                PumpeAnschaltTest();
                PumpeAusschaltTest();
                Logger.log(1);
                KnopfAnTest();
            } else if (Knopf.getKnopfAn()) {
                Logger.log(1);
                KnopfAusTest();
            }
            try {
                Thread.sleep(INI.getSleep());
            } catch (InterruptedException ex) {
                Logger.ErrorLog("Manager", "sleep failed");
            }
            System.out.println("new test");
        }
    }

    private static void PumpeAnschaltTest() {
        if (!Pumpe.getIstAn() && (Termometer.getTemperatur() >= INI.getAnTemp())) {
            Pumpe.anschalten();
            System.out.println("Manger Pumpe an");
        }
    }

    private static void PumpeAusschaltTest() {
        if (Pumpe.getIstAn() && (Termometer.getTemperatur() <= INI.getAusTemp())) {
            Pumpe.ausschalten();
            System.out.println("Manager Pumpe aus");
        }
    }

    private static void KnopfAnTest() {
        if (Knopf.Knopfan()) {
            Pumpe.anschalten();
            System.out.println("Knopf Pumpe an");
        }
    }

    private static void KnopfAusTest() {
        if (!Knopf.Knopfan()) {
            Pumpe.ausschalten();
            System.out.println("Knopf Pumpe aus");
        }
    }
}
