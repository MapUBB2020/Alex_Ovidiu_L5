package sample;

import java.util.List;

public class Fragebogen {
    private int ID_Fragebogen;
    private List<Frage> fragen;
    private int anzahlRichtige;
    private int anzahlFalsche;
    public int currentFrage;

    public Fragebogen(int ID_Fragebogen, List<Frage> fragen, int anzahlRichtige, int anzahlFalsche) {
        this.ID_Fragebogen = ID_Fragebogen;
        this.fragen = fragen;
        this.anzahlRichtige = anzahlRichtige;
        this.anzahlFalsche = anzahlFalsche;
        this.currentFrage = 0;
    }

    public Frage getCurrentQuestion() {
        if (currentFrage >= fragen.size())
            return null;
        Frage question = this.fragen.get(this.currentFrage);
        currentFrage++;
        return question;

    }

    public int getID_Fragebogen() {
        return ID_Fragebogen;
    }

    public void setID_Fragebogen(int ID_Fragebogen) {
        this.ID_Fragebogen = ID_Fragebogen;
    }

    public List<Frage> getFragen() {
        return fragen;
    }

    public void setFragen(List<Frage> fragen) {
        this.fragen = fragen;
    }

    public int getAnzahlRichtige() {
        return anzahlRichtige;
    }

    public void setAnzahlRichtige(int anzahlRichtige) {
        this.anzahlRichtige = anzahlRichtige;
    }

    public int getAnzahlFalsche() {
        return anzahlFalsche;
    }

    public void setAnzahlFalsche(int anzahlFalsche) {
        this.anzahlFalsche = anzahlFalsche;
    }
}
