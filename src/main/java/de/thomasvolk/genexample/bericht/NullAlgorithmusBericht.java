package de.thomasvolk.genexample.bericht;


import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;

public final class NullAlgorithmusBericht implements AlgorithmusBericht {
    public static final AlgorithmusBericht INSTANCE = new NullAlgorithmusBericht();
    private NullAlgorithmusBericht() {
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
