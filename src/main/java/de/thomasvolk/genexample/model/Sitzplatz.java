package de.thomasvolk.genexample.model;

public class Sitzplatz {
    private final int nummer;
    private final int reihe;
    private final int position;
    private final boolean inFahrtrichtung;
    private final boolean abteil;
    private final boolean fenster;

    public Sitzplatz(int nummer, int reihe, int position, boolean fenster, boolean inFahrtrichtung, boolean abteil) {
        this.nummer = nummer;
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

    public int getNummer() {
        return nummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sitzplatz sitzplatz = (Sitzplatz) o;

        return nummer == sitzplatz.nummer;

    }

    @Override
    public int hashCode() {
        return nummer;
    }

    @Override
    public String toString() {
        return "Sitzplatz{" +
                "nummer=" + nummer +
                ", reihe=" + reihe +
                ", position=" + position +
                ", inFahrtrichtung=" + inFahrtrichtung +
                ", abteil=" + abteil +
                ", fenster=" + fenster +
                '}';
    }
}
