package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Wagon;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FirstComeFirstServedTest extends AbstractAlgorithmusTest {


    protected Algorithmus getAlgorithmus(Passagier[]passagiere) {
        return new FirstComeFirstServed(getSitzPlaetze(), passagiere);
    }


    @Test
    public void nullWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.getPassagierReihenfolge();
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, passagierReihenfolge);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere, passagierReihenfolge);
        assertEquals(0, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(2500, wagon.getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 14, 0, 2, 6, 8, 9, 11, 13, 1, 3, 5, 7, 10, 4}, algorithmus.getPassagierReihenfolge());
        assertEquals(2500, wagon.copy(algorithmus.getPassagierReihenfolge()).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.getPassagierReihenfolge();
        assertEquals(400, new Wagon(getSitzPlaetze(), passagiere).getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14}, passagierReihenfolge);
        assertEquals(600, new Wagon(getSitzPlaetze(), passagiere, algorithmus.getPassagierReihenfolge()).getZufriedenheit(), 0);
    }



}
