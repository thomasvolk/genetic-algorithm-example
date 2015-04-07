package de.thomasvolk.genexample;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

public class PassengersReader {
    public Collection<Passenger> read(InputStream is) throws IOException {
        Collection<Passenger> passengers = new ArrayList<Passenger>();
        Reader in = new InputStreamReader(is);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("Ticket-ID", "Fensterplatz",
                "in Fahrtrichtung", "Sitzgruppe").parse(in);
        for (CSVRecord record : records) {
            String lastName = record.get("Last Name");
            String firstName = record.get("First Name");
        }
        return passengers;
    }
}
