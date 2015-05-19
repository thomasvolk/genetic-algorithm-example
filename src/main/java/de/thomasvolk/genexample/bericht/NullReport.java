package de.thomasvolk.genexample.bericht;


import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;

public final class NullReport implements Report {
    public static final Report INSTANCE = new NullReport();
    private NullReport() {
    }

    @Override
    public void start(WagonBelegung wagonBelegung) {

    }

    @Override
    public void evolutionsSchritt(Generation gen) {

    }

    @Override
    public void ende(Generation generation) {

    }
}
