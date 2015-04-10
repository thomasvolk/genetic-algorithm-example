package de.thomasvolk.genexample;

public class Sitzplatz {
    private final int reihe;
    private final int position;

    public Sitzplatz(int reihe, int position) {
        this.reihe = reihe;
        this.position = position;
    }

    public int getReihe() {
        return reihe;
    }

    public int getPosition() {
        return position;
    }
}
