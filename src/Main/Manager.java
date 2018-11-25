package Main;

import Heizungsobjekt.*;

public class Manager {

    public static void Steuerung() {
        int i = 0;
        Logger.log(0, null, null);
        Heizungsobjekt.INIT();
        INI.initINI();

        while (true) {
            if (!Knopf.getKnopfAn()) {
                PumpeAnschaltTest();
                PumpeAusschaltTest();
                Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
                Logger.log(1, "Termometer1", String.valueOf(Termometer.getTemperatur()));
                KnopfAnTest();
            } else if (Knopf.getKnopfAn()) {
                Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
                Logger.log(1, "Termometer1", String.valueOf(Termometer.getTemperatur()));
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
            Pumpe.anschalten(0);
            System.out.println("Manger Pumpe an");
        }
    }

    private static void PumpeAusschaltTest() {
        if (Pumpe.getIstAn() && (Termometer.getTemperatur() <= INI.getAusTemp())) {
            Pumpe.ausschalten(0);
            System.out.println("Manager Pumpe aus");
        }
    }

    private static void KnopfAnTest() {
        if (Knopf.Knopfan()) {
            Pumpe.anschalten(1);
            System.out.println("Knopf Pumpe an");
        }
    }

    private static void KnopfAusTest() {
        if (!Knopf.Knopfan()) {
            Pumpe.ausschalten(1);
            System.out.println("Knopf Pumpe aus");
        }
    }
}
