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
package de.thomasvolk.genexample.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AbstractWagonTest {
    protected static final int ANZAHL_SITZPLAETZE = 16;
    private static final int REIHEN = 6;
    private static final int BREITE = 3;
    private final Wagon wagon = new WagonFactory().lese("GgGaA\nGgGaAg\nGgGaA");

    @Test
    public void wagonGeometrie() {
        Assert.assertEquals(REIHEN, wagon.getReihen());
        Assert.assertEquals(BREITE, wagon.getBreite());
    }

    public Wagon getWagon() {
        return wagon;
    }

    protected Passagier[] getPassagiere(String text) throws IOException {
        return getPassagiere(text, ANZAHL_SITZPLAETZE);
    }

    protected Passagier[] getPassagiere(String text, int len) throws IOException {
        return new CSVPassagierFactory().lese(new ByteArrayInputStream(("Fensterplatz,\"in Fahrtrichtung\",Abteil\n" +
                text).getBytes()), len).toArray(new Passagier[] {});
    }

    protected Passagier[] getPassagiere(int von, int bis, Wertung w) {
        return IntStream.range(von, bis).mapToObj(i -> new Passagier(i, w)).collect(Collectors.toCollection(ArrayList::new)).toArray(new Passagier[] {});
    }
}
