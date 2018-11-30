package Main;

import Heizungsobjekt.*;

public class Manager {

    Termometer HauptTermo = new Termometer("28-05170013b3ff", "HauptTermo");
    Termometer ZweitTermo = new Termometer("28-04170077ffff", "ZweitTermo");
    Termometer DrittTermo = new Termometer("28-051700323fff", "DrittTermo");
    Termometer ViertTermo = new Termometer("28-0316a7b69fff", "ViertTermo");
    Termometer FuenftTermo = new Termometer("28-0517003d18ff", "FuenftTermo");

    Termometer[] AlleTermo = {HauptTermo, ZweitTermo, DrittTermo, ViertTermo, FuenftTermo};

    public void Steuerung() {
        int i = 0;
        Logger.log(0, null, null);
        Heizungsobjekt.INIT();
        INI.initINI();

        while (true) {
            if (!Knopf.getKnopfAn()) {
                if (i % INI.getSleep() == 0) {
                    ManagerTermo();
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
    public void ManagerTermo() {
                    PumpeAnschaltTest();
                    PumpeAusschaltTest();
                    Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
                    LogTermo();
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
