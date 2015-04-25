package de.thomasvolk.genexample.algorithm;

import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.model.AbstractWagonTest;
import de.thomasvolk.genexample.model.Passagier;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public abstract class AbstractAlgorithmusTest extends AbstractWagonTest {
    protected abstract Algorithmus getAlgorithmus(Passagier[] passagiere);

}
