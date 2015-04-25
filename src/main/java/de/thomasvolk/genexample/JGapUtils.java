package de.thomasvolk.genexample;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

import java.util.List;

public final class JGapUtils {
    private JGapUtils() {
    }

    public static int[] asIntArray(IChromosome aSubject, int len) {
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            Integer allele = (Integer) aSubject.getGene(i).getAllele();
            result[i] = allele;
        }
        return result;
    }

    public static Genotype create(int popSize, int[] startGenes, FitnessFunction fitnessFunction) {
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
}
