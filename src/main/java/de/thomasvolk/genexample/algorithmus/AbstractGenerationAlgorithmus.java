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
package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.bericht.AlgorithmusBericht;

public abstract class AbstractGenerationAlgorithmus extends AbstractAlgorithmus {
    private int maxEvolutions = 1000;

    public AbstractGenerationAlgorithmus(WagonBelegung wagonBelegung) {
        super(wagonBelegung);
    }


    public int getMaxEvolutions() {
        return maxEvolutions;
    }

    public void setMaxEvolutions(int maxEvolutions) {
        this.maxEvolutions = maxEvolutions;
    }

    abstract protected Generation getGeneration(int generation);

    @Override
    public WagonBelegung berechneWagon(AlgorithmusBericht algorithmusBericht) {
        algorithmusBericht.start(getWagonBelegung());
        int nummer = 0;
        Generation gen = null;
        for (; nummer < getMaxEvolutions(); nummer++) {
            gen = getGeneration(nummer);

            algorithmusBericht.evolutionsSchritt(gen);
            if(gen.getZufriedenheit() == getWagonBelegung().getMaximaleZufriedenheit()) {
                break;
            }
        }
        algorithmusBericht.ende(gen);
        return gen.getBesteWagonBelegung();
    }
}
