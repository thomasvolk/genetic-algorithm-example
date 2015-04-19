package de.thomasvolk.genexample.algorithm;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public abstract class AbstractAlgorithmusTest extends AbstractWagonTest {
    protected abstract Algorithmus getAlgorithmus(List<Passagier> passagiere);

    @Test
    public void nullWertungen() {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, algorithmus.getPassagierReihenfolge());
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(0, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(2500, wagon.getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 14, 0, 2, 6, 8, 9, 11, 13, 1, 3, 5, 7, 10, 4}, algorithmus.getPassagierReihenfolge());
        assertEquals(2500, wagon.copy(algorithmus.getPassagierReihenfolge()).getZufriedenheit(), 0);
    }

}
