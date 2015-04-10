package de.thomasvolk.genexample;

public class Sitzplatz {
    private final int reihe;
    private final int position;
    private final boolean inFahrtrichtung;
    private final boolean abteil;
    private final boolean fenster;

    public Sitzplatz(int reihe, int position, boolean fenster, boolean inFahrtrichtung, boolean abteil) {
        this.reihe = reihe;
        this.position = position;
        this.fenster = fenster;
        this.inFahrtrichtung = inFahrtrichtung;
        this.abteil = abteil;
    }

    public int getReihe() {
        return reihe;
    }

    public int getPosition() {
        return position;
    }

    public boolean isInFahrtrichtung() {
        return inFahrtrichtung;
    }

    public boolean isAbteil() {
        return abteil;
    }

    public boolean isFenster() {
        return fenster;
    }
}
