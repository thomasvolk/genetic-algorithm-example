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
