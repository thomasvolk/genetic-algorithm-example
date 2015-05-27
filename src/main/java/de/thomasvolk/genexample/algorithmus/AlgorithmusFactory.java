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

import de.thomasvolk.genexample.model.WagonBelegung;

public class AlgorithmusFactory {
    public Algorithmus erzeugeAlgorithmus(AlgorithmusTyp typ, WagonBelegung wagonBelegung) {
        switch (typ) {
            case GENETISCH:
                return new GeneticAlgorithmus(wagonBelegung);
            case SHUFFLE:
                return new ShuffleAlgorithmus(wagonBelegung);
            case KONVENTIONELL:
                return new KonventionellAlgorithmus(wagonBelegung);
            default:
                throw new IllegalStateException("AlgorithmusTyp unbekannt: " + typ);
        }
    }
}
