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
package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonAllocation;
import de.thomasvolk.genexample.report.templates.AllocationContext;
import de.thomasvolk.genexample.report.templates.GenerationContext;
import de.thomasvolk.genexample.report.templates.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class HtmlAlgorithmReport implements AlgorithmReport {
    private final int schritte;
    private final Template generationTemplate;
    private final Template belegungTemplate;
    private final Template indexTemplate;
    private final Template dataJsTemplate;
    private Generation letzteGeneration;
    private Collection<Generation> generationen = new ArrayList<>();
    private WagonAllocation startWagonBelegung;
    private String titel = "";
    private String beschreibung = "";
    private final String zielPfad;

    public HtmlAlgorithmReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlAlgorithmReport(String zielPfad, int schritte) {
        this.schritte = schritte;
        new File(zielPfad).mkdirs();
        this.zielPfad = zielPfad;
        generationTemplate = new Template("algorithm/generation.html");
        belegungTemplate = new Template("algorithm/allocation.html");
        indexTemplate = new Template("algorithm/index.html");
        dataJsTemplate = new Template("data.js");

    }

    private void erzeugebelegung(Generation generation) {
        int i = 0;
        for(WagonAllocation wagonBelegung: generation.getWagonAllocations()) {
            String name = String.format("belegung_%s_%s", generation.getName(), i);
            AllocationContext ctx = new AllocationContext();
            ctx.setBelegung(wagonBelegung);
            belegungTemplate.generiere(getZielPfad(), ctx, name);
            i++;
        }

    }

    private void erzeugeGeneration(Generation generation) {
        generationen.add(generation);
        String name = "generation_" + generation.getName();
        GenerationContext ctx = new GenerationContext();
        ctx.setTitel(getTitel());
        ctx.setBeschreibung(getBeschreibung());
        ctx.setGeneration(generation);
        ctx.setStartWagonBelegung(startWagonBelegung);
        generationTemplate.generiere(getZielPfad(), ctx, name);
        dataJsTemplate.generiere(getZielPfad(), ctx, name);
    }

    public String getZielPfad() {
        return zielPfad;
    }

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

    @Override
    public void start(WagonAllocation wagonBelegung) {
        startWagonBelegung = wagonBelegung;
    }

    @Override
    public void evolutionsSchritt(Generation gen) {
        if(gen.getNummer() % schritte == 0) {
            erzeugeGeneration(gen);
            erzeugebelegung(gen);
            letzteGeneration = null;
        }
        else {
            letzteGeneration = gen;
        }
    }

    @Override
    public void ende(Generation gen) {
        if(letzteGeneration != null) {
            erzeugeGeneration(letzteGeneration);
            erzeugebelegung(letzteGeneration);
        }
        GenerationContext ctx = new GenerationContext();
        ctx.setTitel(getTitel());
        ctx.setBeschreibung(getBeschreibung());
        ctx.setGeneration(gen);
        ctx.setStartWagonBelegung(startWagonBelegung);
        ctx.setGenerationen(generationen);
        indexTemplate.generiere(getZielPfad(), ctx);
        dataJsTemplate.generiere(getZielPfad(), ctx, "index");
        AllocationContext belegungCtx = new AllocationContext();
        belegungCtx.setBelegung(gen.getBesteWagonBelegung());
        belegungTemplate.generiere(getZielPfad(), belegungCtx);
    }
}
