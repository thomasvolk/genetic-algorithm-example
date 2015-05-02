package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;
import org.jgap.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneticAlgorithmus extends AbstractGenerationAlgorithmus {
    private static class GeneticFitnesFunction extends FitnessFunction {
        private final Wagon wagon;

        public GeneticFitnesFunction(Wagon wagon) {
            this.wagon = wagon;
        }

        @Override
        protected double evaluate(IChromosome aSubject) {
            int[] passagierReihenfolge = JGapUtils.asIntArray(aSubject, wagon.getAnzahlPlaetze());
            return wagon.copy(passagierReihenfolge).getZufriedenheit();
        }
    }

    private int populationSize = 6;
    private Genotype genotype;


    public GeneticAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(passagierListe, sitzplatzListe);
        this.genotype = JGapUtils.create(getPopulationSize(), getWagon().getPassagierReihenfolge(), new GeneticFitnesFunction(getWagon()));
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    protected Generation getGeneration(int generation) {
        genotype.evolve();
        IChromosome solution = genotype.getFittestChromosome();
        List<IChromosome> chromosomes = genotype.getPopulation().getChromosomes();
        Stream<int[]> reihenfolgenGeneration = chromosomes.stream().map(c -> JGapUtils.asIntArray(c, getWagon().getAnzahlPlaetze()));
        Stream<Wagon> wagonStream = reihenfolgenGeneration.map(r -> getWagon().copy(r));
        double zufriedenheit = solution.getFitnessValue();
        Wagon besterWagon = getWagon().copy(JGapUtils.asIntArray(solution, getWagon().getAnzahlPlaetze()));
        return new Generation(generation, wagonStream.collect(Collectors.toList()), zufriedenheit, besterWagon);
    }
}
