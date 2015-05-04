package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.model.Wertung;
import de.thomasvolk.genexample.report.NullReport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FirstComeFirstServedTest extends AbstractAlgorithmusTest {


    protected Algorithmus getAlgorithmus(Passagier[]passagiere) {
        return new FirstComeFirstServed(getWagon(), passagiere);
    }


    @Test
    public void nullWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.berechneWagon(NullReport.INSTANCE).getPassagierReihenfolge();
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, passagierReihenfolge);
        WagonBelegung wagonBelegung = new WagonBelegung(getWagon(), passagiere, passagierReihenfolge);
        assertEquals(0, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = new WagonBelegung(getWagon(), passagiere);
        assertEquals(2500, wagonBelegung.getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 14, 0, 2, 6, 8, 9, 11, 13, 1, 3, 5, 7, 10, 4}, algorithmus.berechneWagon(NullReport.INSTANCE
        ).getPassagierReihenfolge());
        assertEquals(2500, algorithmus.berechneWagon(NullReport.INSTANCE).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.berechneWagon(NullReport.INSTANCE).getPassagierReihenfolge();
        assertEquals(400, new WagonBelegung(getWagon(), passagiere).getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14}, passagierReihenfolge);
        assertEquals(600, new WagonBelegung(getWagon(), passagiere, algorithmus.berechneWagon(NullReport.INSTANCE).getPassagierReihenfolge()).getZufriedenheit(), 0);
    }



}
