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
import de.thomasvolk.genexample.report.NullAlgorithmReport;
import de.thomasvolk.genexample.algorithm.Algorithm;
import de.thomasvolk.genexample.algorithm.AlgorithmTyp;
import de.thomasvolk.genexample.algorithm.ConventionalAlgorithm;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ConventionalAlgorithmusTest extends AbstractAlgorithmTest {



    @Test
    public void nullWertungen() {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Request.NULL);
        Algorithm algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.berechneWagon(NullAlgorithmReport.INSTANCE).getPassagierReihenfolge();
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, passagierReihenfolge);
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere, passagierReihenfolge);
        assertEquals(0, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void einfacheWertungen() {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Request(Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG));
        Algorithm algorithmus = getAlgorithmus(passagiere);
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere);
        assertEquals(2500, wagonBelegung.getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 14, 0, 2, 6, 8, 9, 11, 13, 1, 3, 5, 7, 10, 4, 15}, algorithmus.berechneWagon(NullAlgorithmReport.INSTANCE
        ).getPassagierReihenfolge());
        assertEquals(2500, algorithmus.berechneWagon(NullAlgorithmReport.INSTANCE).getZufriedenheit(), 0);
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        Passenger[] passagiere = getPassagiere("x,x,x\nx,x,\nx,,");
        Algorithm algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.berechneWagon(NullAlgorithmReport.INSTANCE).getPassagierReihenfolge();
        assertEquals(400, new WagonAllocation(getWagon(), passagiere).getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15}, passagierReihenfolge);
        assertEquals(600, new WagonAllocation(getWagon(), passagiere, algorithmus.berechneWagon(NullAlgorithmReport.INSTANCE).getPassagierReihenfolge()).getZufriedenheit(), 0);
    }


    @Override
    protected AlgorithmTyp getAlgorithmusTyp() {
        return AlgorithmTyp.KONVENTIONELL;
    }
}
