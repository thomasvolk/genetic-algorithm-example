package de.thomasvolk.genexample;

import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class WagonFactoryTest {

    @Test
    public void lesen() throws IOException {
        WagonFactory factory = new WagonFactory();
        Wagon wagon = factory.lese(getClass().getResourceAsStream("/wagon.txt"));
        assertEquals(98, wagon.getSitzplatzListe().size());
    }
}
