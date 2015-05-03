package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.WagonBesetzung;
import de.thomasvolk.genexample.model.Wertung;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.ShuffleAlgorithmus;
import de.thomasvolk.genexample.report.NullReport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ShuffleAlgorithmusTest extends AbstractAlgorithmusTest {

    protected Algorithmus getAlgorithmus(Passagier[] passagiere) {
        return new ShuffleAlgorithmus(passagiere, getSitzPlaetze());
    }


    @Test
    public void nullWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBesetzung wagonBesetzung = algorithmus.berechneWagon(NullReport.INSTANCE);
        assertEquals(0, wagonBesetzung.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBesetzung wagonBesetzung = new WagonBesetzung(getSitzPlaetze(), passagiere);
        assertEquals(2500, wagonBesetzung.getZufriedenheit(), 0);
        assertEquals(2500, algorithmus.berechneWagon(NullReport.INSTANCE).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        assertEquals(400, new WagonBesetzung(getSitzPlaetze(), passagiere).getZufriedenheit(), 0);
        assertEquals(600, algorithmus.berechneWagon(NullReport.INSTANCE).getZufriedenheit(), 0);
    }
}
