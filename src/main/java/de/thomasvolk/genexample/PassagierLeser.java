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

public class PassagierLeser {

    public static final String ABTEIL = "Abteil";
    public static final String FENSTERPLATZ = "Fensterplatz";
    public static final String IN_FAHRTRICHTUNG = "in Fahrtrichtung";
    public static final String ID = "ID";

    public Collection<Passagier> lese(InputStream is) throws IOException {
        Collection<Passagier> passagiere = new ArrayList<Passagier>();
        Reader in = new InputStreamReader(is);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        records.forEach( record -> {
            String id = record.get(ID);
            int fahrtrichtung = StringUtils.isEmpty(record.get(IN_FAHRTRICHTUNG)) ? 0 : 1;
            int fensterplatz = StringUtils.isEmpty(record.get(FENSTERPLATZ)) ? 0 : 1;
            int abteil = StringUtils.isEmpty(record.get(ABTEIL)) ? 0 : 1;
            passagiere.add(new Passagier(id, fensterplatz, abteil, fahrtrichtung));
        });
        return passagiere;
    }
}
