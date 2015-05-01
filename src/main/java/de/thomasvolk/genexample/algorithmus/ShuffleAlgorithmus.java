package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
    public Wagon getWagon(Report report) {
        Wagon wagon = new Wagon(getSitzplatzListe(), getPassagierListe());
        int[] reihenfolge =  wagon.getPassagierReihenfolge();
        int[] bestShuffle = reihenfolge;
        double hoechsteZufriedenheit = wagon.getZufriedenheit();
        int i = 0;
        for (; i < getMaxIterations(); i++) {
            reihenfolge = shuffle(reihenfolge);
            Wagon genWagon = wagon.copy(reihenfolge);
            report.evolutionsSchritt(i, Stream.of(genWagon));
            double zufriedenheit = genWagon.getZufriedenheit();
            if(zufriedenheit > hoechsteZufriedenheit) {
                hoechsteZufriedenheit = zufriedenheit;
                bestShuffle = reihenfolge;
            }
            if(zufriedenheit == wagon.getMaximaleZufriedenheit()) {
                break;
            }
        }
        Wagon besterWagon = wagon.copy(bestShuffle);
        report.bestesErgebnis(i, wagon);
        return besterWagon;
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
