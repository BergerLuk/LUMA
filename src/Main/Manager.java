package Main;

import Heizungsobjekt.*;

public class Manager {

    Termometer HauptTermo = new Termometer("28-05170013b3ff", "HauptTermo");
    Termometer ZweitTermo = new Termometer("28-04170077ffff", "ZweitTermo");
    Termometer DrittTermo = new Termometer("28-04170077ffff", "DrittTermo");
    Termometer ViertTermo = new Termometer("28-04170077ffff", "ViertTermo");
    Termometer FuenftTermo = new Termometer("28-04170077ffff", "FuenftTermo");

    Termometer[] AlleTermo = {HauptTermo, ZweitTermo, DrittTermo, ViertTermo, FuenftTermo};

    public void Steuerung() {
        int i = 0;
        Logger.log(0, null, null);
        Heizungsobjekt.INIT();
        INI.initINI();

        while (true) {
            if (!Knopf.getKnopfAn()) {
                if (i % INI.getSleep() == 0) {
                    PumpeAnschaltTest();
                    PumpeAusschaltTest();
                    Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
                    LogTermo();
                }
                KnopfAnTest();
            } else if (Knopf.getKnopfAn()) {
                if (i % INI.getSleep() == 0) {
                    Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
                    LogTermo();
                }
                KnopfAusTest();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.ErrorLog("Manager", "sleep failed");
            }
            i++;
        }
    }

    private void LogTermo() {
        for (Termometer i : AlleTermo) {
            Logger.log(1, i.toString(), String.valueOf(i.getTemperatur()));
        }
    }

    private void PumpeAnschaltTest() {
        if (!Pumpe.getIstAn() && (HauptTermo.getTemperatur() >= INI.getAnTemp())) {
            Pumpe.anschalten(0);
            System.out.println("Manger Pumpe an");
        }
    }

    private void PumpeAusschaltTest() {
        if (Pumpe.getIstAn() && (HauptTermo.getTemperatur() <= INI.getAusTemp())) {
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
