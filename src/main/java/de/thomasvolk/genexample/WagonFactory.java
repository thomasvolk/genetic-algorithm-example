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
        int reihe = 0;
        int position = lines.length - 1;
        for(String line: lines) {
            for(char sitz: line.toCharArray()) {
                Sitzplatz sitzplatz = new Sitzplatz(reihe, position);
                sitzplatzListe.add(sitzplatz);
                reihe++;
            }
            position--;
        }
        return new Wagon(sitzplatzListe);
    }
}
