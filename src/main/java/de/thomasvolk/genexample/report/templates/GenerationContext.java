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
package de.thomasvolk.genexample.report.templates;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonAllocation;

import java.util.Collection;

public class GenerationContext {
    private String titel;
    private String beschreibung;
    private Generation generation;
    private WagonAllocation startWagonAllocation;
    private Collection<Generation> generationen;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public WagonAllocation getStartWagonAllocation() {
        return startWagonAllocation;
    }

    public void setStartWagonAllocation(WagonAllocation startWagonAllocation) {
        this.startWagonAllocation = startWagonAllocation;
    }

    public Collection<Generation> getGenerationen() {
        return generationen;
    }

    public void setGenerationen(Collection<Generation> generationen) {
        this.generationen = generationen;
    }
}
