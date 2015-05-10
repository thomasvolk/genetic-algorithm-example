package de.thomasvolk.genexample.model;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVPassagierFactory extends AbstractPassagierFactory {

    @Override
    protected List<Passagier> lesePassagiere(InputStream is, int anzahl) throws IOException {
        List<Passagier> passagiere = new ArrayList<>();
        Reader in = new InputStreamReader(is);
        Iterator<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in).iterator();
        int i = 0;
        while(records.hasNext() && i < anzahl) {
            CSVRecord record = records.next();
            i++;
            int fahrtrichtung = getWertung(record.get(IN_FAHRTRICHTUNG));
            int fensterplatz = getWertung(record.get(FENSTERPLATZ));
            int abteil = getWertung(record.get(ABTEIL));
            Wertung wertung = new Wertung(fensterplatz, abteil, fahrtrichtung);
            passagiere.add(new Passagier(i, wertung));
        }
        return passagiere;
    }

}
