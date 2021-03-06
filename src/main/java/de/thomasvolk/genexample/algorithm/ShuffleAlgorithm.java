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
    private double hoechsteHappiness;

    public ShuffleAlgorithm(WagonAllocation wagonBelegung) {
        super(wagonBelegung);
        reihenfolge =  getWagonAllocation().getPassagierReihenfolge();
        bestShuffle = reihenfolge;
        hoechsteHappiness = getWagonAllocation().getHappiness();

    }

    protected Generation getGeneration(int nummer) {
        reihenfolge = shuffle(reihenfolge);
        WagonAllocation genWagonBelegung = getWagonAllocation().copy(reihenfolge);
        double happiness = genWagonBelegung.getHappiness();
        Generation generation = new Generation(nummer, Collections.singleton(genWagonBelegung), happiness, genWagonBelegung);
        if(happiness > hoechsteHappiness) {
            hoechsteHappiness = happiness;
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
