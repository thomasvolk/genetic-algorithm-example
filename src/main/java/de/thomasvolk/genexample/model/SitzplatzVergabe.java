package de.thomasvolk.genexample.model;


public class SitzplatzVergabe {
    private final Sitzplatz sitzplatz;
    private final Passagier passagier;

    public SitzplatzVergabe(Sitzplatz sitzplatz, Passagier passagier) {
        this.sitzplatz = sitzplatz;
        this.passagier = passagier;
    }

    public double getZufriedenheitFaktor() {
        if(getPassagier().getMaximaleZufriedenheit() == 0) {
            return -1;
        }
        else {
            return ((double) getZufriedenheit()) / ((double) getPassagier().getMaximaleZufriedenheit());
        }
    }

    public int getZufriedenheit() {
        int value = 0;
        if(sitzplatz.isAbteil()) {
            value += passagier.getWertung().getAbteil();
        }
        if(sitzplatz.isFenster()) {
            value += passagier.getWertung().getFensterPlatz();
        }
        if(sitzplatz.isInFahrtrichtung()) {
            value += passagier.getWertung().getFahrtRichtung();
        }
        return value;
    }

    public Sitzplatz getSitzplatz() {
        return sitzplatz;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SitzplatzVergabe that = (SitzplatzVergabe) o;

        return sitzplatz.equals(that.sitzplatz);

    }

    @Override
    public int hashCode() {
        return sitzplatz.hashCode();
    }

    @Override
    public String toString() {
        return "SitzplatzVergabe{" +
                "sitzplatz=" + sitzplatz.getNummer() +
                ", passagier=" + passagier.getId() +
                ", zufriedenheit=" + getZufriedenheit() +
                '}';
    }
}
