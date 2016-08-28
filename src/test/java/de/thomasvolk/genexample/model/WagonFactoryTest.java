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

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class WagonFactoryTest {

    @Test
    public void lesen() throws IOException {
        WagonFactory factory = new WagonFactory();
        Wagon wagon = factory.lese(getClass().getResourceAsStream("/wagon-test.txt"));
        assertEquals(28, wagon.getReihen());
        assertEquals(4, wagon.getBreite());

        assertEquals(104, wagon.getSitzplatzListe().length);
        Seat sitzplatz = wagon.getSitzplatzListe()[0];
        assertEquals(2, sitzplatz.getNummer());
        assertEquals(1, sitzplatz.getPosition());
        assertEquals(0, sitzplatz.getReihe());
        assertFalse(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertTrue(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[5];
        assertEquals(8, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(1, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertTrue(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[97];
        assertEquals(104, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(25, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[94];
        assertEquals(101, sitzplatz.getNummer());
        assertEquals(0, sitzplatz.getPosition());
        assertEquals(25, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

        sitzplatz = wagon.getSitzplatzListe()[93];
        assertEquals(100, sitzplatz.getNummer());
        assertEquals(3, sitzplatz.getPosition());
        assertEquals(24, sitzplatz.getReihe());
        assertTrue(sitzplatz.isFenster());
        assertFalse(sitzplatz.isAbteil());
        assertFalse(sitzplatz.isInFahrtrichtung());

    }
}
