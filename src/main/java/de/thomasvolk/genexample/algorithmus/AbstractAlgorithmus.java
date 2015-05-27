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

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;
import de.thomasvolk.genexample.model.WagonBelegung;

public abstract class AbstractAlgorithmus implements Algorithmus {
    private final WagonBelegung wagonBelegung;

    public AbstractAlgorithmus(WagonBelegung wagonBelegung) {
        this.wagonBelegung = wagonBelegung;
    }

    public Sitzplatz[] getSitzplatzListe() {
        return wagonBelegung.getSitzplatzListe();
    }

    public Passagier[] getPassagierListe() {
        return wagonBelegung.getPassagierListe();
    }

    public WagonBelegung getWagonBelegung() {
        return wagonBelegung;
    }
}
