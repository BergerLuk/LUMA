package Main;

import Heizungsobjekt.*;

public class Manager {

    Termometer HauptTermo = new Termometer("28-04170077ffff", "HauptTermo");
    Termometer ZweitTermo = new Termometer("28-0517003d18ff", "ZweitTermo");
    Termometer DrittTermo = new Termometer("28-051700323fff", "DrittTermo");
    Termometer ViertTermo = new Termometer("28-0316a7b69fff", "ViertTermo");
    Termometer FuenftTermo = new Termometer("28-05170013b3ff", "Defekt");

    Termometer[] AlleTermo = {HauptTermo, ZweitTermo, DrittTermo, ViertTermo, FuenftTermo};

    public void Steuerung() {
        int i = 0;
        Logger.log(0, null, null);
        Heizungsobjekt.INIT();
        INI.initINI();

        while (true) {
            //Regelbetrieb
            if (!Knopf.getKnopfAn()) {
                if (i % INI.getSleep() == 0) {
                    ManagerTermo();
                }
                KnopfAnTest();
                //Zwangsbetrieb
            } else {
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

    private void ManagerTermo() {
        PumpeAnschaltTest();
        PumpeAusschaltTest();
        Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
        LogTermo();
    }

    public void TempCheck() {
        Logger.log(1, "Pumpe", String.valueOf(Pumpe.getIstAn()));
        LogTermo();
        if (!Knopf.getKnopfAn()) {
            PumpeAnschaltTest();
            PumpeAusschaltTest();
        }
    }

    private void LogTermo() {
        String GUILog = "";
        int a = 1;
        for (Termometer i : AlleTermo) {
            Logger.log(1, i.toString(), String.valueOf(i.getTemperatur()));
            GUILog = GUILog + a + " = " + String.valueOf(i.getTemperatur()) + "°C // ";
            a++;
        }
        GUI.Uebersicht.newLog(GUILog);
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

    private void KnopfAnTest() {
        if (Knopf.Knopfan()) {
            Pumpe.anschalten(1);
            System.out.println("Knopf Pumpe an");
        }
    }

    private void KnopfAusTest() {
        if (!Knopf.Knopfan()) {
            if (HauptTermo.getTemperatur() >= INI.getAusTemp()) {
                ZwangsLED.ausschalten();
                GUI.Uebersicht.PumpeAnUI(0);
                Logger.log(2, "Zwangsmodus", "Ausgeschaltet");
            }
            else {
                Pumpe.ausschalten(1);
                System.out.println("Knopf Pumpe aus");
            }
        }
    }
}
