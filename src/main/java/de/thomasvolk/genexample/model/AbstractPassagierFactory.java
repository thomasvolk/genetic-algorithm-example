package de.thomasvolk.genexample.model;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class AbstractPassagierFactory implements PassagierFactory {
    public static final String FENSTERPLATZ = "Fensterplatz";
    public static final String IN_FAHRTRICHTUNG = "in Fahrtrichtung";
    public static final String ABTEIL = "Abteil";

    @Override
    public List<Passagier> lese(InputStream is, int anzahl) throws IOException {
        List<Passagier> passagiere = lesePassagiere(is, anzahl);
        int count = passagiere.size();
        while(count < anzahl) {
            count++;
            passagiere.add(new Passagier(count, Wertung.NULL));
        }
        return passagiere;
    }

    protected int getWertung(String inEingabe) {
        String eingabe = inEingabe.trim();
        if(StringUtils.isEmpty(eingabe)) {
            return Wertung.NULL_GEWICHTUNG;
        }
        try {
            return Integer.valueOf(eingabe);
        } catch (NumberFormatException e) {
            return Wertung.EINFACHE_GEWICHTUNG;
        }
    }

    protected abstract List<Passagier> lesePassagiere(InputStream is, int anzahl) throws IOException;
}
