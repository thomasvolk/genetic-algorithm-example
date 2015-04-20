package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.GeneticAlgorithmus;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GeneticAlgorithmusTest extends AbstractAlgorithmusTest {

    protected Algorithmus getAlgorithmus(Passagier[] passagiere) {
        return new GeneticAlgorithmus(passagiere, getSitzPlaetze());
    }


    @Test
    public void nullWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere, algorithmus.getPassagierReihenfolge());
        assertEquals(0, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(2500, wagon.getZufriedenheit(), 0);
        assertEquals(2500, wagon.copy(algorithmus.getPassagierReihenfolge()).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        assertEquals(400, new Wagon(getSitzPlaetze(), passagiere).getZufriedenheit(), 0);
        assertEquals(600, new Wagon(getSitzPlaetze(), passagiere, algorithmus.getPassagierReihenfolge()).getZufriedenheit(), 0);
    }
}
