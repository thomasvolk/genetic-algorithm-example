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
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class WagonAllocationTest extends AbstractWagonTest {

    @Test
    public void nullPassagiere() throws IOException {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Request.NULL);
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere);
        assertEquals(0.0, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungPassagiere() throws IOException {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Request(Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG));
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere);
        assertEquals(Request.EINFACHE_GEWICHTUNG * 25, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungOhneFahrtrichtungPassagiere() throws IOException {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Request(Request.EINFACHE_GEWICHTUNG,Request.EINFACHE_GEWICHTUNG,Request.NULL_GEWICHTUNG));
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere);
        assertEquals(Request.EINFACHE_GEWICHTUNG * 16, wagonBelegung.getZufriedenheit(), 0);
    }

    @Test(expected = ArrayStoreException.class)
    public void dubeltten() {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Request.NULL);
        new WagonAllocation(getWagon(), passagiere, new int[] {0,1,2,3,4,5,12,7,8,9,10,11,12,13,14,15});
    }

    @Test
    public void sortierung() {
        Passenger[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Request.NULL);
        WagonAllocation wagonBelegung = new WagonAllocation(getWagon(), passagiere, new int[]{14, 0, 1, 2, 3, 4, 5, 12, 13, 8, 9, 10, 11, 6, 7, 15});
        List<SeatAllocation> sitzplatzVergabeListe = wagonBelegung.getSitzplatzVergabeListe();
        SeatAllocation sitzplatzVergabe = sitzplatzVergabeListe.get(0);
        assertEquals(1, sitzplatzVergabe.getPassagier().getId());
        assertEquals(1, sitzplatzVergabe.getSitzplatz().getNummer());
        sitzplatzVergabe = sitzplatzVergabeListe.get(14);
        assertEquals(0, sitzplatzVergabe.getPassagier().getId());
        assertEquals(15, sitzplatzVergabe.getSitzplatz().getNummer());
        sitzplatzVergabe = sitzplatzVergabeListe.get(5);
        assertEquals(6, sitzplatzVergabe.getPassagier().getId());
        assertEquals(6, sitzplatzVergabe.getSitzplatz().getNummer());
    }

}
