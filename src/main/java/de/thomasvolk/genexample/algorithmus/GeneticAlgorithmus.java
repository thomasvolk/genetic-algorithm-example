package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Passagier;

public class GeneticAlgorithmus implements Algorithmus {
    private final Passagier[] passagierListe;

    public GeneticAlgorithmus(Passagier[] passagierListe) {
        this.passagierListe = passagierListe;
    }

    @Override
    public int[] getPassagierReihenfolge() {
        throw new UnsupportedOperationException("not implemented jet");
    }
}
