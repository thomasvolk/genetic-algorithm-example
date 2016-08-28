/**
 * Copyright (C) 2015 Thomas Volk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.thomasvolk.genexample.model;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WagonFactory {

    private Seat erzeuge(int reihe, int position, int breite, char eigenschaft) {
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
        return new Seat(nummer, reihe, position, fenster, inFahrtrichtung, abteil);
    }

    public Wagon lese(InputStream is) throws IOException {
        return lese(IOUtils.toString(is, "UTF-8").trim());
    }

    public Wagon lese(String text) {
        String[] lines = text.split("\\r?\\n");
        List<Seat> sitzplatzListe = new ArrayList<>();
        int breite = lines.length;
        int position = breite - 1;
        int anzahlReihen = 0;
        for(String line: lines) {
            int reiheInZeile = 0;
            for (char eigenschaft : line.toCharArray()) {
                Seat sitzplatz = erzeuge(reiheInZeile, position, breite, eigenschaft);
                if(sitzplatz != null) {
                    sitzplatzListe.add(sitzplatz);
                }
                reiheInZeile++;
            }
            if(reiheInZeile > anzahlReihen) {
                anzahlReihen = reiheInZeile;
            }
            position--;
        }
        Seat[] sitzPLaetze = sitzplatzListe.stream().sorted((s1, s2) -> s1.getNummer() - s2.getNummer()).collect(
                Collectors.toCollection(ArrayList::new)).toArray(new Seat[sitzplatzListe.size()]);
        return new Wagon(sitzPLaetze, anzahlReihen, breite);
    }
}
