package de.thomasvolk.genexample;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SitzplatzFactoryTest {

    @Test
    public void lesen() throws IOException {
        SitzplatzFactory factory = new SitzplatzFactory();
        List<Sitzplatz> sitzplatzListe = factory.lese(getClass().getResourceAsStream("/wagon.txt"));
        assertEquals(98, sitzplatzListe.size());
        Sitzplatz sitzplatz = sitzplatzListe.get(0);
        assertEquals(1, sitzplatz.getNummer());
        assertEquals(0, sitzplatz.getPosition());
        assertEquals(0, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = sitzplatzListe.get(5);
        assertEquals(6, sitzplatz.getNummer());
        assertEquals(1, sitzplatz.getPosition());
        assertEquals(1, sitzplatz.getReihe());
        assertFalse(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = sitzplatzListe.get(97);
        assertEquals(104, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(25, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = sitzplatzListe.get(94);
        assertEquals(100, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(24, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = sitzplatzListe.get(93);
        assertEquals(99, sitzplatz.getNummer());
        assertEquals(2, sitzplatz.getPosition());
        assertEquals(24, sitzplatz.getReihe());
        assertFalse(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

    }
}
