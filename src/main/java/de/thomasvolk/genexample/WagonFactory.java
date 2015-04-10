package de.thomasvolk.genexample;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class WagonFactory {
    public Wagon lese(InputStream is) throws IOException {
        String text = IOUtils.toString(is, "UTF-8").trim();
        String[] lines = text.split("\\n");
        Collection<Sitzplatz> sitzplatzListe = new ArrayList<>();
        int fensterLinks = 0;
        int fensterRechts = lines.length - 1;
        SitzplatzFactory sitzplatzFactory = new SitzplatzFactory(fensterLinks, fensterRechts);
        int reihe = 0;
        int position = fensterRechts;
        for(String line: lines) {
            for(char eigenschaft: line.toCharArray()) {
                Sitzplatz sitzplatz = sitzplatzFactory.erzeuge(reihe, position, eigenschaft);
                sitzplatzListe.add(sitzplatz);
                reihe++;
            }
            position--;
        }
        return new Wagon(sitzplatzListe);
    }
}
