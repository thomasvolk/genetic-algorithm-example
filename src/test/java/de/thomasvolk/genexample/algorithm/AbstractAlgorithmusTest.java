package de.thomasvolk.genexample.algorithm;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public abstract class AbstractAlgorithmusTest extends AbstractWagonTest {
    protected abstract Algorithmus getAlgorithmus(Passagier[] passagiere);

}
