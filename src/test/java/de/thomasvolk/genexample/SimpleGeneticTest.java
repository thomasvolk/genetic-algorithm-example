package de.thomasvolk.genexample;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class NumberOrderingFitnessFunction extends FitnessFunction {

    private final int[] expected;

    NumberOrderingFitnessFunction(int[] expected) {
        this.expected = expected;
    }

    @Override
    protected double evaluate(IChromosome aSubject) {
        int fitnessValue = 0;
        for (int i = 0; i < expected.length; i++) {
            Integer allele = (Integer) aSubject.getGene(i).getAllele();
            if(allele == expected[i]) { fitnessValue++; }
        }
        return fitnessValue;
    }

}


public class SimpleGeneticTest {
    private static final int MAX_EVOLUTION = 1000;
    public static int[] EXPECTED = {0,1,2,3,4,5,6,7,8,9};
    public static int[] START = {7,5,2,4,0,6,3,1,9,8};


    @Test
    public void run() throws Exception {
        Genotype population = create(10000);
        for (int i = 0; i < MAX_EVOLUTION; i++) {
            population.evolve();
            IChromosome solution = population.getFittestChromosome();
            int fitnessValue = (int) solution.getFitnessValue();
            if(i % 100 == 0) {
                System.out.println(getRepresetation(solution) + " - " + fitnessValue);
            }
            if (fitnessValue == EXPECTED.length) {
                break;
            }
        }

        IChromosome solution = population.getFittestChromosome();
        int fitnessValue = (int) solution.getFitnessValue();
        System.out.println("-----------------------------------------------");
        System.out.println("The solution has a fitness value of " + fitnessValue);
        System.out.println(getRepresetation(solution));
    }

    private String getRepresetation(IChromosome solution) {
        return Arrays.stream(solution.getGenes()).map(g -> String.valueOf(g.getAllele())).reduce("", (s, g) -> s + " " + g);
    }

    private static Genotype create(int popSize) throws InvalidConfigurationException {
        Configuration conf = new DefaultConfiguration();
        conf.getGeneticOperators().clear();
        conf.addGeneticOperator(new SwappingMutationOperator(conf));
        conf.setPreservFittestIndividual(true);
        conf.setKeepPopulationSizeConstant(true);

        FitnessFunction myFunc = new NumberOrderingFitnessFunction(EXPECTED);
        conf.setFitnessFunction(myFunc);

        IChromosome sampleChromosome = new Chromosome(conf, EXPECTED.length);
        Collection<Gene> genes = new ArrayList<>();
        for (int e : START) {
            genes.add(new IntegerGene(conf, e, e));
        }
        sampleChromosome.setGenes(genes.toArray(new Gene[genes.size()]));
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(popSize);

        return Genotype.randomInitialGenotype(conf);
    }


}
