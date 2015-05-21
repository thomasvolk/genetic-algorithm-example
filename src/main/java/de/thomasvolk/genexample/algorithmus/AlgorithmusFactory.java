package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Wagon;
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
