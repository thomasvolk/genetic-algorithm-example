/**
 * Copyright (C) 2015 Thomas Volk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.thomasvolk.genexample.algorithm;

import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.algorithmus.GeneticAlgorithmus;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.model.Wertung;
import de.thomasvolk.genexample.bericht.HtmlAlgorithmusBericht;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GeneticAlgorithmusTest extends AbstractAlgorithmusTest {
    private Path tempdir;
    private HtmlAlgorithmusBericht report;


    @Before
    public void prepare() throws IOException {
        tempdir = Files.createTempDirectory("GeneticAlgorithmusTest");
        System.out.println("Tempdir: " + tempdir);
        report = new HtmlAlgorithmusBericht(tempdir.toString(), 100);
    }

    private void report(String titel, String beschreibung) {
        report.setTitel(titel);
        report.setBeschreibung(beschreibung);
    }

    @Test
    public void nullWertungen() {
        report("GeneticAlgorithmusTest.nullWertungen", "Es wurden keine Wertungen abgegeben.");
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = algorithmus.berechneWagon(report);
        assertEquals(0, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void alleWuescheEinfacheWertungen() throws IOException {
        report("GeneticAlgorithmusTest.alleWuescheEinfacheWertungen", "Jeder Passagier hat alle Wünsche gewählt.");
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        WagonBelegung wagonBelegung = new WagonBelegung(getWagon(), passagiere);
        assertEquals(2500, wagonBelegung.getZufriedenheit(), 0);
        assertEquals(2500, algorithmus.berechneWagon(report).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        report("GeneticAlgorithmusTest.unterschiedlicheWertungen", "Die Passagier haben unterschiedliche wünsche.");
        Passagier[] passagiere = getPassagiere("x,x,x\nx,x,\nx,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        assertEquals(400, new WagonBelegung(getWagon(), passagiere).getZufriedenheit(), 0);
        assertEquals(600, algorithmus.berechneWagon(report).getZufriedenheit(), 0);
    }

    @Override
    protected AlgorithmusTyp getAlgorithmusTyp() {
        return AlgorithmusTyp.GENETISCH;
    }
}
