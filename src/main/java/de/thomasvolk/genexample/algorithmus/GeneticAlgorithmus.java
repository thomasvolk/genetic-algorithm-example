package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;
import org.jgap.*;

import java.util.List;
import java.util.stream.Stream;

public class GeneticAlgorithmus extends AbstractAlgorithmus implements GenerationenProvider {
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
    private int maxEvolutions = 1000;
    private int populationSize = 6;
    private GenerationHandler generationHandler;

    public GeneticAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(passagierListe, sitzplatzListe);
    }

    public int getMaxEvolutions() {
        return maxEvolutions;
    }

    public void setMaxEvolutions(int maxEvolutions) {
        this.maxEvolutions = maxEvolutions;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    @Override
    public int[] getPassagierReihenfolge() {
        Wagon wagon = new Wagon(getSitzplatzListe(), getPassagierListe());
        Genotype genotype = JGapUtils.create(getPopulationSize(), wagon.getPassagierReihenfolge(), new GeneticFitnesFunction(wagon));
        for (int i = 0; i < getMaxEvolutions(); i++) {
            genotype.evolve();
            IChromosome solution = genotype.getFittestChromosome();
            List<IChromosome> chromosomes = genotype.getPopulation().getChromosomes();
            Stream<int[]> reihenfolgenGeneration = chromosomes.stream().map(c -> JGapUtils.asIntArray(c, wagon.getAnzahlPlaetze()));
            Stream<Wagon> wagonStream = reihenfolgenGeneration.map(r -> wagon.copy(r));
            if(generationHandler != null) {
                generationHandler.evolutionsSchritt(i, wagonStream);
            }
            double fitnessValue = solution.getFitnessValue();
            if(fitnessValue == wagon.getMaximaleZufriedenheit()) {
                break;
            }
        }
        IChromosome solution = genotype.getFittestChromosome();
        return JGapUtils.asIntArray(solution, wagon.getAnzahlPlaetze());
    }

    @Override
    public void setGenerationHandler(GenerationHandler handler) {
        this.generationHandler = handler;
    }
}
