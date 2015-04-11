package de.thomasvolk.genexample;

public class Passagier {
    private final int id;
    private final int fensterPlatz;
    private final int abteil;
    private final int fahrtRichtung;

    public Passagier(int id) {
        this(id, 0, 0, 0);
    }

    public Passagier(int id, int fensterPlatz, int abteil, int fahrtRichtung) {
        this.id = id;
        this.fensterPlatz = fensterPlatz;
        this.abteil = abteil;
        this.fahrtRichtung = fahrtRichtung;
    }

    public int getId() {
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
