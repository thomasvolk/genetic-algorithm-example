package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.WagonBesetzung;
import org.jgap.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneticAlgorithmus extends AbstractGenerationAlgorithmus {
    private static class GeneticFitnesFunction extends FitnessFunction {
        private final WagonBesetzung wagonBesetzung;

        public GeneticFitnesFunction(WagonBesetzung wagonBesetzung) {
            this.wagonBesetzung = wagonBesetzung;
        }

        @Override
        protected double evaluate(IChromosome aSubject) {
            int[] passagierReihenfolge = JGapUtils.asIntArray(aSubject, wagonBesetzung.getAnzahlPlaetze());
            return wagonBesetzung.copy(passagierReihenfolge).getZufriedenheit();
        }
    }

    private int populationSize = 6;
    private Genotype genotype;


    public GeneticAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(passagierListe, sitzplatzListe);
        this.genotype = JGapUtils.create(getPopulationSize(), getWagonBesetzung().getPassagierReihenfolge(), new GeneticFitnesFunction(getWagonBesetzung()));
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
        Stream<int[]> reihenfolgenGeneration = chromosomes.stream().map(c -> JGapUtils.asIntArray(c, getWagonBesetzung().getAnzahlPlaetze()));
        Stream<WagonBesetzung> wagonStream = reihenfolgenGeneration.map(r -> getWagonBesetzung().copy(r));
        double zufriedenheit = solution.getFitnessValue();
        WagonBesetzung besterWagonBesetzung = getWagonBesetzung().copy(JGapUtils.asIntArray(solution, getWagonBesetzung().getAnzahlPlaetze()));
        return new Generation(generation, wagonStream.collect(Collectors.toList()), zufriedenheit, besterWagonBesetzung);
    }
}
