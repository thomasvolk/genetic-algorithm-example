package de.thomasvolk.genexample.model;

public class Wagon {
    private final Sitzplatz[] sitzplatzListe;
    private final int reihen;
    private final int breite;


    public Wagon(Sitzplatz[] sitzplatzListe, int reihen, int breite) {
        this.sitzplatzListe = sitzplatzListe;
        this.reihen = reihen;
        this.breite = breite;
    }

    public Sitzplatz[] getSitzplatzListe() {
        return sitzplatzListe;
    }

    public int getReihen() {
        return reihen;
    }

    public int getBreite() {
        return breite;
    }
}
