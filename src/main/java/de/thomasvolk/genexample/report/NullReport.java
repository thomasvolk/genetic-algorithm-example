package de.thomasvolk.genexample.report;


import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Wagon;

import java.util.stream.Stream;

public final class NullReport implements Report {
    public static final Report INSTANCE = new NullReport();
    private NullReport() {
    }

    @Override
    public void evolutionsSchritt(int num, Stream<Wagon> wagons) {

    }

    @Override
    public void bestesErgebnis(Wagon wagon) {

    }
}
