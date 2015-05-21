package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleAlgorithmus extends AbstractGenerationAlgorithmus {
    private int[] reihenfolge;
    private int[] bestShuffle;
    private double hoechsteZufriedenheit;

    public ShuffleAlgorithmus(WagonBelegung wagonBelegung) {
        super(wagonBelegung);
        reihenfolge =  getWagonBelegung().getPassagierReihenfolge();
        bestShuffle = reihenfolge;
        hoechsteZufriedenheit = getWagonBelegung().getZufriedenheit();

    }

    protected Generation getGeneration(int nummer) {
        reihenfolge = shuffle(reihenfolge);
        WagonBelegung genWagonBelegung = getWagonBelegung().copy(reihenfolge);
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
