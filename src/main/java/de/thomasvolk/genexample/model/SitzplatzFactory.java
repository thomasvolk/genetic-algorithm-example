package de.thomasvolk.genexample.model;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SitzplatzFactory {

    private Sitzplatz erzeuge(int reihe, int position, int breite, char eigenschaft) {
        int nummer = (reihe * breite) + position + 1;
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

    public Wagon lese(InputStream is) throws IOException {
        return lese(IOUtils.toString(is, "UTF-8").trim());
    }

    public Wagon lese(String text) {
        String[] lines = text.split("\\n");
        List<Sitzplatz> sitzplatzListe = new ArrayList<>();
        int breite = lines.length;
        int position = breite - 1;
        int reihen = 0;
        for(String line: lines) {
            int reihe = 0;
            for (char eigenschaft : line.toCharArray()) {
                Sitzplatz sitzplatz = erzeuge(reihe, position, breite, eigenschaft);
                if(sitzplatz != null) {
                    sitzplatzListe.add(sitzplatz);
                }
                reihe++;
            }
            if(reihe > reihen) {
                reihen = reihe;
            }
            position--;
        }
        Sitzplatz[] sitzPLaetze = sitzplatzListe.stream().sorted((s1, s2) -> s1.getNummer() - s2.getNummer()).collect(
                Collectors.toCollection(ArrayList::new)).toArray(new Sitzplatz[sitzplatzListe.size()]);
        return new Wagon(sitzPLaetze, reihen, breite);
    }
}
