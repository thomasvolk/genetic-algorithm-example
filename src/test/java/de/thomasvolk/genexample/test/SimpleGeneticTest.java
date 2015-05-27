/**
 * Copyright (C) 2015 Thomas Volk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.thomasvolk.genexample.test;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    private static final int POPULATION_SIZE = 6;
    public static int[] EXPECTED = {0,1,2,3,4,5,6,7,8,9};
    public static int[] START = {7,5,4,2,0,6,3,1,9,8};

    @Test
    public void run() throws Exception {
        Genotype genotype = create(POPULATION_SIZE, START, new NumberOrderingFitnessFunction(EXPECTED));
        System.out.println("init");
        System.out.println(toString(genotype.getFittestChromosome()));
        for (int i = 0; i < MAX_EVOLUTION; i++) {
            System.out.println("evolution step: " + i);
            genotype.evolve();
            List<IChromosome> chromosomes = genotype.getPopulation().getChromosomes();
            chromosomes.stream().forEach(c -> System.out.println("        " + toString(c)));
            IChromosome solution = genotype.getFittestChromosome();
            int fitnessValue = (int) solution.getFitnessValue();
            System.out.println("fittest:" + toString(solution));
            if (fitnessValue == EXPECTED.length) {
                break;
            }
        }
        IChromosome solution = genotype.getFittestChromosome();
        System.out.println("result");
        System.out.println(toString(solution));
    }

    private static Genotype create(int popSize, int[] startGenes, FitnessFunction fitnessFunction) throws InvalidConfigurationException {
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
        for(IChromosome chromosome: chrmosomes) {
            for (int i = 0; i < startGenes.length; i++) {
                Gene gene = chromosome.getGene(i);
                gene.setAllele(startGenes[i]);
            }
        }
        return genotype;
    }

    private String toString(IChromosome chromosome) {
        int fitnessValue = (int) chromosome.getFitnessValue();
        return Arrays.stream(chromosome.getGenes()).map(g -> String.valueOf(g.getAllele())).reduce("", (s, g) -> s + " " + g) +
                " -> fitness: " + fitnessValue;
    }


}
