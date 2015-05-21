package de.thomasvolk.genexample.algorithm;

import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.AlgorithmusFactory;
import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.model.AbstractWagonTest;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.WagonBelegung;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public abstract class AbstractAlgorithmusTest extends AbstractWagonTest {

    protected abstract AlgorithmusTyp getAlgorithmusTyp();

    protected Algorithmus getAlgorithmus(Passagier[] passagiere) {
        WagonBelegung wagonBelegung = new WagonBelegung(getWagon(), passagiere);
        return new AlgorithmusFactory().erzeugeAlgorithmus(getAlgorithmusTyp(), wagonBelegung);
    }

}
