package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Passagier;
import de.thomasvolk.genexample.Sitzplatz;

public class GeneticAlgorithmus extends AbstractAlgorithmus {

    public GeneticAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(passagierListe, sitzplatzListe);
    }

    @Override
    public int[] getPassagierReihenfolge() {
        throw new UnsupportedOperationException("not implemented jet");
    }
}
