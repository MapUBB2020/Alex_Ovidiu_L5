package sample;

import java.util.List;

public class Frage {

    private String fragetext;
    private String antwort1;
    private String antwort2;
    private String antwort3;
    private String antwort4;
    private int ID;
    private String linktophoto;
    private List<String> korrekteAntworten;

    public Frage(String fragetext, String antwort1, String antwort2, String antowrt3, String antwort4, int ID, String linktophoto, List<String> korrekteAntworten) {
        this.fragetext = fragetext;
        this.korrekteAntworten = korrekteAntworten;
        this.antwort1 = antwort1;
        this.antwort2 = antwort2;
        this.antwort3 = antowrt3;
        this.antwort4 = antwort4;
        this.ID = ID;
        this.linktophoto = linktophoto;
    }

    public String getFragetext() {
        return fragetext;
    }

    public void setFragetext(String fragetext) {
        this.fragetext = fragetext;
    }

    public List<String> getKorrekteAntworten() {
        return korrekteAntworten;
    }

    public void setKorrekteAntworten(List<String> korrekteAntworten) {
        this.korrekteAntworten = korrekteAntworten;
    }

    public String getAntwort1() {
        return antwort1;
    }

    public void setAntwort1(String antwort1) {
        this.antwort1 = antwort1;
    }

    public String getAntwort2() {
        return antwort2;
    }

    public void setAntwort2(String antwort2) {
        this.antwort2 = antwort2;
    }

    public String getAntwort3() {
        return antwort3;
    }

    public String getAntwort4() {
        return antwort4;
    }

    public void setAntwort4(String antwort4) {
        this.antwort4 = antwort4;
    }

    public void setAntwort3(String antwort3) {
        this.antwort3 = antwort3;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLinktophoto() {
        return linktophoto;
    }

    public void setLinktophoto(String linktophoto) {
        this.linktophoto = linktophoto;
    }
}
