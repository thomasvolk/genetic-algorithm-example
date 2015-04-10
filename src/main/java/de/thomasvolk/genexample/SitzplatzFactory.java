package de.thomasvolk.genexample;

public class SitzplatzFactory {
    private final int fensterLinks;
    private final int fensterRechts;

    public SitzplatzFactory(int fensterLinks, int fensterRechts) {
        this.fensterLinks = fensterLinks;
        this.fensterRechts = fensterRechts;
    }

    public Sitzplatz erzeuge(int reihe, int position, char eigenschaft) {
        boolean inFahrtrichtung;
        boolean abteil;
        switch (eigenschaft) {
            case 'G':
                inFahrtrichtung = true;
                abteil = false;
                break;
            case 'A':
                inFahrtrichtung = true;
                abteil = true;
                break;
            case 'g':
                inFahrtrichtung = false;
                abteil = false;
                break;
            case 'a':
                inFahrtrichtung = false;
                abteil = true;
                break;
            default:
                throw new IllegalStateException("unbekannte Eigenschaft: " + eigenschaft);
        }
        boolean fenster = position == fensterRechts || position == fensterLinks;
        return new Sitzplatz(reihe, position, fenster, inFahrtrichtung, abteil);
    }
}
