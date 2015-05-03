package de.thomasvolk.genexample.report;


import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBesetzung;

public final class NullReport implements Report {
    public static final Report INSTANCE = new NullReport();
    private NullReport() {
    }

    @Override
    public void start(WagonBesetzung wagonBesetzung) {

    }

    @Override
    public void evolutionsSchritt(Generation gen) {

    }

    @Override
    public void ende(Generation generation) {

    }
}
