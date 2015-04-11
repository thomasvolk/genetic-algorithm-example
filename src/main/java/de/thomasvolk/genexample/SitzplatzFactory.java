package de.thomasvolk.genexample;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class SitzplatzFactory {

    public Sitzplatz erzeuge(int reihe, int position, int breite, char eigenschaft) {
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
        int fensterRechts = breite - 1;
        int fensterLinks = 0;
        boolean fenster = position == fensterRechts || position == fensterLinks;
        return new Sitzplatz(reihe, position, fenster, inFahrtrichtung, abteil);
    }

    public Collection<Sitzplatz> lese(InputStream is) throws IOException {
        String text = IOUtils.toString(is, "UTF-8").trim();
        String[] lines = text.split("\\n");
        Collection<Sitzplatz> sitzplatzListe = new ArrayList<>();
        int breite = lines.length;
        int position = breite - 1;
        int reihe = 0;
        for(String line: lines) {
            for(char eigenschaft: line.toCharArray()) {
                Sitzplatz sitzplatz = erzeuge(reihe, position, breite, eigenschaft);
                sitzplatzListe.add(sitzplatz);
                reihe++;
            }
            position--;
        }
        return sitzplatzListe;
    }
}
