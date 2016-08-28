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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleAlgorithm extends AbstractGenerationAlgorithm {
    private int[] reihenfolge;
    private int[] bestShuffle;
    private double hoechsteZufriedenheit;

    public ShuffleAlgorithm(WagonAllocation wagonBelegung) {
        super(wagonBelegung);
        reihenfolge =  getWagonBelegung().getPassagierReihenfolge();
        bestShuffle = reihenfolge;
        hoechsteZufriedenheit = getWagonBelegung().getZufriedenheit();

    }

    protected Generation getGeneration(int nummer) {
        reihenfolge = shuffle(reihenfolge);
        WagonAllocation genWagonBelegung = getWagonBelegung().copy(reihenfolge);
        double zufriedenheit = genWagonBelegung.getZufriedenheit();
        Generation generation = new Generation(nummer, Collections.singleton(genWagonBelegung), zufriedenheit, genWagonBelegung);
        if(zufriedenheit > hoechsteZufriedenheit) {
            hoechsteZufriedenheit = zufriedenheit;
            bestShuffle = reihenfolge;
        }
        return generation;
    }

    private int[] shuffle(int[] reihenfolge) {
        List<Integer> list = new ArrayList<>();
        for (int platz : reihenfolge) {
            list.add(platz);
        }
        Collections.shuffle(list);
        int[] result = new int[reihenfolge.length];
        for(int i = 0; i < reihenfolge.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
