package de.thomasvolk.genexample;

public class Wertung {
    public static final Wertung NULL = new Wertung(0, 0, 0);
    public static final int EINFACHE_GEWICHTUNG = 100;
    public static final int NULL_GEWICHTUNG = 0;
    private final int fensterPlatz;
    private final int abteil;
    private final int fahrtRichtung;

    public Wertung(int fensterPlatz, int abteil, int fahrtRichtung) {
        this.fensterPlatz = fensterPlatz;
        this.abteil = abteil;
        this.fahrtRichtung = fahrtRichtung;
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

    @Override
    public String toString() {
        return "Wertug{" +
                "fensterPlatz=" + fensterPlatz +
                ", abteil=" + abteil +
                ", fahrtRichtung=" + fahrtRichtung +
                '}';
    }
}
