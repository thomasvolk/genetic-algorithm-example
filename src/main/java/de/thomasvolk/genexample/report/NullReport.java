package de.thomasvolk.genexample.report;


import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.algorithmus.Generation;
import de.thomasvolk.genexample.model.Wagon;

import java.util.stream.Stream;

public final class NullReport implements Report {
    public static final Report INSTANCE = new NullReport();
    private NullReport() {
    }

    @Override
    public void start(Wagon wagon) {

    }

    @Override
    public void evolutionsSchritt(Generation gen) {

    }

    @Override
    public void ende(Generation generation) {

    }
}
