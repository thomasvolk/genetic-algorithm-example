package de.thomasvolk.genexample.model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class WagonFactoryTest {

    @Test
    public void lesen() throws IOException {
        WagonFactory factory = new WagonFactory();
        Wagon wagon = factory.lese(getClass().getResourceAsStream("/wagon-test.txt"));
        assertEquals(28, wagon.getReihen());
        assertEquals(4, wagon.getBreite());

        assertEquals(104, wagon.getSitzplatzListe().length);
        Sitzplatz sitzplatz = wagon.getSitzplatzListe()[0];
        assertEquals(2, sitzplatz.getNummer());
        assertEquals(1, sitzplatz.getPosition());
        assertEquals(0, sitzplatz.getReihe());
        assertFalse(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[5];
        assertEquals(8, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(1, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[97];
        assertEquals(104, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(25, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[94];
        assertEquals(101, sitzplatz.getNummer());
        assertEquals(0, sitzplatz.getPosition());
        assertEquals(25, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[93];
        assertEquals(100, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(24, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

    }
}
