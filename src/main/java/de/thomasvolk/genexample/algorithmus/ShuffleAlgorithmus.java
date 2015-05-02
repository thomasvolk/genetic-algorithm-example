package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ShuffleAlgorithmus extends AbstractGenerationAlgorithmus {
    private int[] reihenfolge;
    private int[] bestShuffle;
    private double hoechsteZufriedenheit;

    public ShuffleAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(passagierListe, sitzplatzListe);
        reihenfolge =  getWagon().getPassagierReihenfolge();
        bestShuffle = reihenfolge;
        hoechsteZufriedenheit = getWagon().getZufriedenheit();

    }

    protected Generation getGeneration(int nummer) {
        reihenfolge = shuffle(reihenfolge);
        Wagon genWagon = getWagon().copy(reihenfolge);
        double zufriedenheit = genWagon.getZufriedenheit();
        Generation generation = new Generation(nummer, Stream.of(genWagon), zufriedenheit, genWagon);
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
