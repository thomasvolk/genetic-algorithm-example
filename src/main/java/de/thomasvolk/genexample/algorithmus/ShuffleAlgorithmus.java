package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Passagier;
import de.thomasvolk.genexample.Sitzplatz;
import de.thomasvolk.genexample.Wagon;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffleAlgorithmus extends AbstractAlgorithmus {
    private int maxIterations = 1000;

    public ShuffleAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(passagierListe, sitzplatzListe);
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    @Override
    public int[] getPassagierReihenfolge() {
        Wagon wagon = new Wagon(getSitzplatzListe(), getPassagierListe());
        int[] reihenfolge =  wagon.getPassagierReihenfolge();
        int[] bestShuffle = reihenfolge;
        double hoechsteZufriedenheit = wagon.getZufriedenheit();
        for (int i = 0; i < getMaxIterations(); i++) {
            reihenfolge = shuffle(reihenfolge);
            double zufriedenheit = wagon.copy(reihenfolge).getZufriedenheit();
            if(zufriedenheit > hoechsteZufriedenheit) {
                hoechsteZufriedenheit = zufriedenheit;
                bestShuffle = reihenfolge;
            }
            if(zufriedenheit == wagon.getMaximaleZufriedenheit()) {
                break;
            }
        }
        return bestShuffle;
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
