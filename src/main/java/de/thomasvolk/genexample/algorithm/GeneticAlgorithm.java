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
package de.thomasvolk.genexample.algorithm;

import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.model.*;
import org.jgap.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneticAlgorithm extends AbstractGenerationAlgorithm {
    private static class GeneticFitnesFunction extends FitnessFunction {
        private final WagonAllocation wagonBelegung;

        public GeneticFitnesFunction(WagonAllocation wagonBelegung) {
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

    public GeneticAlgorithm(WagonAllocation wagonBelegung) {
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
        Stream<WagonAllocation> wagonStream = reihenfolgenGeneration.map(r -> getWagonBelegung().copy(r));
        double zufriedenheit = solution.getFitnessValue();
        WagonAllocation besteWagonBelegung = getWagonBelegung().copy(JGapUtils.asIntArray(solution, getWagonBelegung().getAnzahlPlaetze()));
        return new Generation(generation, wagonStream.collect(Collectors.toList()), zufriedenheit, besteWagonBelegung);
    }
}
