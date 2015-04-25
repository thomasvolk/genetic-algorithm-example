package de.thomasvolk.genexample.model;

public class Passagier {
    private final int id;
    private final Wertung wertung;

    public Passagier(int id, Wertung wertung) {
        this.id = id;
        this.wertung = wertung;
    }

    public int getId() {
        return id;
    }

    public Wertung getWertung() {
        return wertung;
    }

    public double getZufriedenheit(Sitzplatz sitzplatz) {
        double value = 0.0;
        if(sitzplatz.isAbteil()) {
            value += getWertung().getAbteil();
        }
        if(sitzplatz.isFenster()) {
            value += getWertung().getFensterPlatz();
        }
        if(sitzplatz.isInFahrtrichtung()) {
            value += getWertung().getFahrtRichtung();
        }
        return value;
    }

    public double getMaximaleZufriedenheit() {
        return getWertung().getFahrtRichtung() + getWertung().getFensterPlatz() + getWertung().getAbteil();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passagier passagier = (Passagier) o;

        return id == passagier.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Passagier{" +
                "id=" + id +
                ", wuensche=" + wertung +
                '}';
    }
}
