package de.thomasvolk.genexample;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SetGene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

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
    private static final int MAX_EVOLUTION = 10000;
    public static int[] EXPECTED = {0,1,2,3,4,5,6,7,8,9,10};

    @Test
    public void run() throws Exception {
        Genotype population = create(1000);
        for (int i = 0; i < MAX_EVOLUTION; i++) {
            population.evolve();
            IChromosome solution = population.getFittestChromosome();
            System.out.println(getRepresetation(solution));
            if (((int)solution.getFitnessValue()) == EXPECTED.length) {
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
        conf.setPreservFittestIndividual(true);

        FitnessFunction myFunc = new NumberOrderingFitnessFunction(EXPECTED);
        conf.setFitnessFunction(myFunc);

        IChromosome sampleChromosome = new Chromosome(conf, 1);
        Collection<Gene> genes = new ArrayList<>();
        for (int e : EXPECTED) {
            genes.add(new IntegerGene(conf, 0, EXPECTED.length - 1));
        }
        sampleChromosome.setGenes(genes.toArray(new Gene[genes.size()]));
        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(popSize);

        return Genotype.randomInitialGenotype(conf);
    }


}
