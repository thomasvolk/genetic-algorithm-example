package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.model.Wertung;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.ShuffleAlgorithmus;
import de.thomasvolk.genexample.report.NullReport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ShuffleAlgorithmusTest extends AbstractAlgorithmusTest {

    protected Algorithmus getAlgorithmus(Passagier[] passagiere) {
        return new ShuffleAlgorithmus(passagiere, getWagon());
    }


    @Test
    public void nullWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = algorithmus.berechneWagon(NullReport.INSTANCE);
        assertEquals(0, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = new WagonBelegung(getWagon(), passagiere);
        assertEquals(2500, wagonBelegung.getZufriedenheit(), 0);
        assertEquals(2500, algorithmus.berechneWagon(NullReport.INSTANCE).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere("x,x,x\nx,x,\nx,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        assertEquals(400, new WagonBelegung(getWagon(), passagiere).getZufriedenheit(), 0);
        assertEquals(600, algorithmus.berechneWagon(NullReport.INSTANCE).getZufriedenheit(), 0);
    }
}
