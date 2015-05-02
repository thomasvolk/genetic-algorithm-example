package de.thomasvolk.genexample.report;


import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.Wagon;

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
