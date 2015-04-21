package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Passagier;
import de.thomasvolk.genexample.Sitzplatz;
import de.thomasvolk.genexample.Wagon;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

import java.util.List;

public class GeneticAlgorithmus extends AbstractAlgorithmus {
    private int maxEvolutions = 1000;
    private int populationSize = 6;

    public static int[] getReihenfolge(IChromosome aSubject, int len) {
        int[] passagierReihenfolge = new int[len];
        for (int i = 0; i < len; i++) {
            Integer allele = (Integer) aSubject.getGene(i).getAllele();
            passagierReihenfolge[i] = allele;
        }
        return passagierReihenfolge;
    }

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

    private static Genotype create(int popSize, int[] startGenes, FitnessFunction fitnessFunction) {
        try {
            Configuration.reset();
            Configuration conf = new DefaultConfiguration();
            conf.getGeneticOperators().clear();
            SwappingMutationOperator swap = new SwappingMutationOperator(conf);
            swap.setStartOffset(0);
            conf.addGeneticOperator(swap);
            conf.setPreservFittestIndividual(true);
            conf.setKeepPopulationSizeConstant(false);
            conf.setPopulationSize(popSize);

            conf.setFitnessFunction(fitnessFunction);
            IChromosome sampleChromosome = new Chromosome(conf, new IntegerGene(conf), startGenes.length);
            conf.setSampleChromosome(sampleChromosome);
            Genotype genotype = Genotype.randomInitialGenotype(conf);
            List<IChromosome> chrmosomes = genotype.getPopulation().getChromosomes();
            for (IChromosome chromosome : chrmosomes) {
                for (int i = 0; i < startGenes.length; i++) {
                    Gene gene = chromosome.getGene(i);
                    gene.setAllele(startGenes[i]);
                }
            }
            return genotype;
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[] getPassagierReihenfolge() {
        Wagon wagon = new Wagon(getSitzplatzListe(), getPassagierListe());
        Genotype genotype = create(getPopulationSize(), wagon.getPassagierReihenfolge(), new GeneticFitnesFunction(wagon));
        for (int i = 0; i < getMaxEvolutions(); i++) {
            genotype.evolve();
            IChromosome solution = genotype.getFittestChromosome();
            double fitnessValue = solution.getFitnessValue();
            if(fitnessValue == wagon.getMaximaleZufriedenheit()) {
                break;
            }
        }
        IChromosome solution = genotype.getFittestChromosome();
        return getReihenfolge(solution, wagon.getAnzahlPlaetze());
    }
}
