package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.model.*;
import org.jgap.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneticAlgorithmus extends AbstractGenerationAlgorithmus {
    private static class GeneticFitnesFunction extends FitnessFunction {
        private final WagonBelegung wagonBelegung;

        public GeneticFitnesFunction(WagonBelegung wagonBelegung) {
            this.wagonBelegung = wagonBelegung;
        }

        @Override
        protected double evaluate(IChromosome aSubject) {
            int[] passagierReihenfolge = JGapUtils.asIntArray(aSubject, wagonBelegung.getAnzahlPlaetze());
            return wagonBelegung.copy(passagierReihenfolge).getZufriedenheit();
        }
    }

    private int populationSize = 6;
    private Genotype genotype;

    public GeneticAlgorithmus(WagonBelegung wagonBelegung) {
        super(wagonBelegung);
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public Genotype getGenotype() {
        if(genotype == null) {
            genotype = JGapUtils.create(getPopulationSize(), getWagonBelegung().getPassagierReihenfolge(), new GeneticFitnesFunction(getWagonBelegung()));
        }
        return genotype;
    }

    protected Generation getGeneration(int generation) {
        getGenotype().evolve();
        IChromosome solution = getGenotype().getFittestChromosome();
        List<IChromosome> chromosomes = getGenotype().getPopulation().getChromosomes();
        Stream<int[]> reihenfolgenGeneration = chromosomes.stream().map(c -> JGapUtils.asIntArray(c, getWagonBelegung().getAnzahlPlaetze()));
        Stream<WagonBelegung> wagonStream = reihenfolgenGeneration.map(r -> getWagonBelegung().copy(r));
        double zufriedenheit = solution.getFitnessValue();
        WagonBelegung besteWagonBelegung = getWagonBelegung().copy(JGapUtils.asIntArray(solution, getWagonBelegung().getAnzahlPlaetze()));
        return new Generation(generation, wagonStream.collect(Collectors.toList()), zufriedenheit, besteWagonBelegung);
    }
}
