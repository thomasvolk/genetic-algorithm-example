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

import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.report.AlgorithmReport;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class ConventionalAlgorithm extends AbstractAlgorithm {

    public ConventionalAlgorithm(WagonAllocation wagonBelegung) {
        super(wagonBelegung);
    }

    @Override
    public WagonAllocation calculateWagon(AlgorithmReport algorithmusBericht) {
        algorithmusBericht.start(getWagonAllocation());
        List<Integer> passagierReihenfolge = new ArrayList<>();
        Set<SeatAllocation> vergebenePlaetze = new HashSet<>();
        for (Passenger p : getPassagierListe()) {
            SeatAllocation besterPlatz = null;
            int index = 0;
            int ausgewaehlterPassagierIndex = 0;
            for (Seat sp : getSitzplatzListe()) {
                SeatAllocation sitzplatzVergabe = new SeatAllocation(sp, p);
                if (!vergebenePlaetze.contains(sitzplatzVergabe) && !passagierReihenfolge.contains(index)) {
                    if (besterPlatz == null || sitzplatzVergabe.getZufriedenheit() > besterPlatz.getZufriedenheit()) {
                        besterPlatz = sitzplatzVergabe;
                        ausgewaehlterPassagierIndex = index;
                    }
                }
                index++;
            }
            vergebenePlaetze.add(besterPlatz);
            passagierReihenfolge.add(ausgewaehlterPassagierIndex);
        }
        WagonAllocation wagonBelegung = new WagonAllocation(getWagonAllocation().getWagon(), getPassagierListe(),
                ArrayUtils.toPrimitive(passagierReihenfolge.toArray(new Integer[getPassagierListe().length])));
        algorithmusBericht.ende(new Generation(0, Collections.singleton(wagonBelegung), wagonBelegung.getZufriedenheit(), wagonBelegung));
        return wagonBelegung;
    }
}
