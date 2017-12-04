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

import de.thomasvolk.genexample.model.Passenger;
import de.thomasvolk.genexample.model.WagonAllocation;
import de.thomasvolk.genexample.model.Request;
import de.thomasvolk.genexample.report.HtmlAlgorithmReport;
import de.thomasvolk.genexample.algorithm.Algorithm;
import de.thomasvolk.genexample.algorithm.AlgorithmType;
import de.thomasvolk.genexample.algorithm.GeneticAlgorithm;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GeneticAlgorithmTest extends AbstractAlgorithmTest {
    private Path tempdir;
    private HtmlAlgorithmReport report;


    @Before
    public void prepare() throws IOException {
        tempdir = Files.createTempDirectory("GeneticAlgorithmusTest");
        System.out.println("Tempdir: " + tempdir);
        report = new HtmlAlgorithmReport(tempdir.toString(), 100);
    }

    private void report(String titel, String beschreibung) {
        report.setTitel(titel);
        report.setBeschreibung(beschreibung);
    }

    @Test
    public void nullWertungen() {
        report("GeneticAlgorithmusTest.nullWertungen", "Es wurden keine Wertungen abgegeben.");
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Request.NULL);
        Algorithm algorithmus = getAlgorithmus(passagiere);
        WagonAllocation wagonBelegung = algorithmus.calculateWagon(report);
        assertEquals(0, wagonBelegung.getHappiness(), 0);
    }

    @Test
    public void alleWuescheEinfacheWertungen() throws IOException {
        report("GeneticAlgorithmusTest.alleWuescheEinfacheWertungen", "Jeder Passagier hat alle Wünsche gewählt.");
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Request(Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG));
        Algorithm algorithmus = getAlgorithmus(passagiere);
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere);
        assertEquals(2500, wagonBelegung.getHappiness(), 0);
        assertEquals(2500, algorithmus.calculateWagon(report).getHappiness(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        report("GeneticAlgorithmusTest.unterschiedlicheWertungen", "Die Passagier haben unterschiedliche wünsche.");
        Passenger[] passagiere = getPassagiere("x,x,x\nx,x,\nx,,");
        Algorithm algorithmus = getAlgorithmus(passagiere);
        assertEquals(400, new WagonAllocation(getWagon(), passagiere).getHappiness(), 0);
        assertEquals(600, algorithmus.calculateWagon(report).getHappiness(), 0);
    }

    @Override
    protected AlgorithmType getAlgorithmusTyp() {
        return AlgorithmType.GENETIC;
    }
}
