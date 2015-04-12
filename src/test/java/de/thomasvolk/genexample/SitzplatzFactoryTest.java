package de.thomasvolk.genexample;

import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class SitzplatzFactoryTest {

    @Test
    public void lesen() throws IOException {
        SitzplatzFactory factory = new SitzplatzFactory();
        Collection<Sitzplatz> sitzplatzListe = factory.lese(getClass().getResourceAsStream("/wagon.txt"));
        assertEquals(98, sitzplatzListe.size());
        //sitzplatzListe.stream().forEach( s -> System.out.println(s));
    }
}
