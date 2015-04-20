package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Passagier;
import de.thomasvolk.genexample.Sitzplatz;
import de.thomasvolk.genexample.Wagon;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class GeneticFitnesFunction extends FitnessFunction {
    private final Wagon wagon;

    public GeneticFitnesFunction(Wagon wagon) {
        this.wagon = wagon;
    }

    @Override
    protected double evaluate(IChromosome aSubject) {
        int[] passagierReihenfolge = GeneticAlgorithmus.getReihenfolge(aSubject, wagon.getAnzahlPlaetze());
        return wagon.copy(passagierReihenfolge).getZufriedenheit();
    }


}
