package de.thomasvolk.genexample;

public class Passagier {
    private final String id;
    private final int fensterPlatz;
    private final int abteil;
    private final int fahrtRichtung;

    public Passagier(String id, int fensterPlatz, int abteil, int fahrtRichtung) {
        this.id = id;
        this.fensterPlatz = fensterPlatz;
        this.abteil = abteil;
        this.fahrtRichtung = fahrtRichtung;
    }

    public String getId() {
        return id;
    }

    public int getFensterPlatz() {
        return fensterPlatz;
    }

    public int getAbteil() {
        return abteil;
    }

    public int getFahrtRichtung() {
        return fahrtRichtung;
    }
}
