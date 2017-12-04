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
package de.thomasvolk.genexample.model;

import java.util.Collection;

public class Generation {
    private final int nummer;
    private final Collection<WagonAllocation> wagonAllocations;
    private final double happiness;
    private final WagonAllocation bestWagonAllocation;

    public Generation(int nummer, Collection<WagonAllocation> wagonAllocations, double happiness, WagonAllocation bestWagon) {
        this.nummer = nummer;
        this.wagonAllocations = wagonAllocations;
        this.happiness = happiness;
        this.bestWagonAllocation = bestWagon;
    }

    public double getHappiness() {
        return happiness;
    }

    public int getNummer() {
        return nummer;
    }

    public Collection<WagonAllocation> getWagonAllocations() {
        return wagonAllocations;
    }

    public WagonAllocation getBestWagonAllocation() {
        return bestWagonAllocation;
    }

    public String getName() {
        return String.format("%010d", getNummer());
    }

}