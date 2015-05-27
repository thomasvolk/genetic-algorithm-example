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
package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.bericht.AlgorithmusBericht;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class KonventionellAlgorithmus extends AbstractAlgorithmus {

    public KonventionellAlgorithmus(WagonBelegung wagonBelegung) {
        super(wagonBelegung);
    }

    @Override
    public WagonBelegung berechneWagon(AlgorithmusBericht algorithmusBericht) {
        algorithmusBericht.start(getWagonBelegung());
        List<Integer> passagierReihenfolge = new ArrayList<>();
        Set<SitzplatzVergabe> vergebenePlaetze = new HashSet<>();
        for (Passagier p : getPassagierListe()) {
            SitzplatzVergabe besterPlatz = null;
            int index = 0;
            int ausgewaehlterPassagierIndex = 0;
            for (Sitzplatz sp : getSitzplatzListe()) {
                SitzplatzVergabe sitzplatzVergabe = new SitzplatzVergabe(sp, p);
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
        WagonBelegung wagonBelegung = new WagonBelegung(getWagonBelegung().getWagon(), getPassagierListe(),
                ArrayUtils.toPrimitive(passagierReihenfolge.toArray(new Integer[getPassagierListe().length])));
        algorithmusBericht.ende(new Generation(0, Collections.singleton(wagonBelegung), wagonBelegung.getZufriedenheit(), wagonBelegung));
        return wagonBelegung;
    }
}
