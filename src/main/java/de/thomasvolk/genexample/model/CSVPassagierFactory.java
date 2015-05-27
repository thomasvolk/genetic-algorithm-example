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
