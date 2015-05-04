package de.thomasvolk.genexample.model;

import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.SitzplatzFactory;
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
        Wagon wagon = factory.lese(getClass().getResourceAsStream("/wagon.txt"));
        assertEquals(26, wagon.getReihen());
        assertEquals(4, wagon.getBreite());

        assertEquals(98, wagon.getSitzplatzListe().length);
        Sitzplatz sitzplatz = wagon.getSitzplatzListe()[0];
        assertEquals(1, sitzplatz.getNummer());
        assertEquals(0, sitzplatz.getPosition());
        assertEquals(0, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[5];
        assertEquals(6, sitzplatz.getNummer());
        assertEquals(1, sitzplatz.getPosition());
        assertEquals(1, sitzplatz.getReihe());
        assertFalse(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[97];
        assertEquals(104, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(25, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[94];
        assertEquals(100, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(24, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[93];
        assertEquals(99, sitzplatz.getNummer());
        assertEquals(2, sitzplatz.getPosition());
        assertEquals(24, sitzplatz.getReihe());
        assertFalse(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

    }
}
