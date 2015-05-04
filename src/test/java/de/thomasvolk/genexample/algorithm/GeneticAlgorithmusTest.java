package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.report.Report;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.GeneticAlgorithmus;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.model.Wertung;
import de.thomasvolk.genexample.report.HtmlReport;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GeneticAlgorithmusTest extends AbstractAlgorithmusTest {
    private Path tempdir;
    private Report report;

    protected Algorithmus getAlgorithmus(Passagier[] passagiere) {
        return new GeneticAlgorithmus(passagiere, getSitzPlaetze());
    }

    @Before
    public void prepare() throws IOException {
        tempdir = Files.createTempDirectory("GeneticAlgorithmusTest");
        System.out.println("Tempdir: " + tempdir);
        report = new HtmlReport(tempdir.toString(), 100);
    }

    @Test
    public void nullWertungen() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = algorithmus.berechneWagon(report);
        assertEquals(0, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = new WagonBelegung(getSitzPlaetze(), passagiere);
        assertEquals(2500, wagonBelegung.getZufriedenheit(), 0);
        assertEquals(2500, algorithmus.berechneWagon(report).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passagier[] passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        assertEquals(400, new WagonBelegung(getSitzPlaetze(), passagiere).getZufriedenheit(), 0);
        assertEquals(600, algorithmus.berechneWagon(report).getZufriedenheit(), 0);
    }
}
