package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Wagon;

public class AlgorithmusFactory {
    public Algorithmus erzeugeAlgorithmus(AlgorithmusTyp typ, Passagier[] passagierListe, Wagon wagon) {
        switch (typ) {
            case GENETISCH:
                return new GeneticAlgorithmus(passagierListe, wagon);
            case SHUFFLE:
                return new ShuffleAlgorithmus(passagierListe, wagon);
            case FCFS:
                return new FirstComeFirstServed(passagierListe, wagon);
            default:
                throw new IllegalStateException("AlgorithmusTyp unbekannt: " + typ);
        }
    }
}
