package de.thomasvolk.genexample;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PassagierFactory {

    public static final String ABTEIL = "Abteil";
    public static final String FENSTERPLATZ = "Fensterplatz";
    public static final String IN_FAHRTRICHTUNG = "in Fahrtrichtung";
    public static final String ID = "ID";

    public Collection<Passagier> lese(InputStream is, int anzahl) throws IOException {
        Collection<Passagier> passagiere = new ArrayList<Passagier>();
        Reader in = new InputStreamReader(is);
        Iterator<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in).iterator();
        int i = 0;
        while(records.hasNext() && i < anzahl) {
            CSVRecord record = records.next();
            i++;
            String id = record.get(ID);
            int fahrtrichtung = StringUtils.isEmpty(record.get(IN_FAHRTRICHTUNG).trim()) ? 0 : 1;
            int fensterplatz = StringUtils.isEmpty(record.get(FENSTERPLATZ).trim()) ? 0 : 1;
            int abteil = StringUtils.isEmpty(record.get(ABTEIL).trim()) ? 0 : 1;
            passagiere.add(new Passagier(i, fensterplatz, abteil, fahrtrichtung));
        }
        while(i < anzahl) {
            i++;
            passagiere.add(new Passagier(i));
        }
        return passagiere;
    }
}
