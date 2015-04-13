package de.thomasvolk.genexample;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SitzplatzFactory {

    private Sitzplatz erzeuge(int nummer, int reihe, int position, int breite, char eigenschaft) {
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
                return null;
        }
        int fensterRechts = breite - 1;
        int fensterLinks = 0;
        boolean fenster = position == fensterRechts || position == fensterLinks;
        return new Sitzplatz(nummer, reihe, position, fenster, inFahrtrichtung, abteil);
    }

    public List<Sitzplatz> lese(InputStream is) throws IOException {
        String text = IOUtils.toString(is, "UTF-8").trim();
        String[] lines = text.split("\\n");
        List<Sitzplatz> sitzplatzListe = new ArrayList<>();
        int breite = lines.length;
        int position = breite - 1;
        for(String line: lines) {
            int reihe = 0;
            for (char eigenschaft : line.toCharArray()) {
                int nummer = (reihe * breite) + position + 1;
                Sitzplatz sitzplatz = erzeuge(nummer, reihe, position, breite, eigenschaft);
                if(sitzplatz != null) {
                    sitzplatzListe.add(sitzplatz);
                }
                reihe++;
            }
            position--;
        }
        return sitzplatzListe.stream().sorted( (s1, s2) -> s1.getNummer() - s2.getNummer() ).collect(
                Collectors.toCollection(ArrayList::new));
    }
}
