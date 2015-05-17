package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.report.templates.BelegungTemplate;
import de.thomasvolk.genexample.report.templates.GenerationTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class HtmlReport implements Report {
    private final int schritte;
    private final GenerationTemplate generationTemplate;
    private final BelegungTemplate belegungTemplate;
    private final GenerationTemplate indexTemplate;
    private final GenerationTemplate wagonJsTemplate;
    private final GenerationTemplate dataJsTemplate;
    private final GenerationTemplate cssTemplate;
    private Generation letzteGeneration;
    private Collection<Generation> generationen = new ArrayList<>();
    private WagonBelegung startWagonBelegung;
    private String titel = "";
    private String beschreibung = "";


    public HtmlReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlReport(String zielPfad, int schritte) {
        this.schritte = schritte;
        new File(zielPfad).mkdirs();
        generationTemplate = new GenerationTemplate(zielPfad, "generation.html");
        belegungTemplate = new BelegungTemplate(zielPfad, "belegung.html");
        indexTemplate = new GenerationTemplate(zielPfad, "index.html");
        wagonJsTemplate = new GenerationTemplate(zielPfad, "wagon.js");
        dataJsTemplate = new GenerationTemplate(zielPfad, "data.js");
        cssTemplate = new GenerationTemplate(zielPfad, "default.css");
    }

    private void erzeugebelegung(Generation generation) {
        int i = 0;
        for(WagonBelegung wagonBelegung: generation.getWagonBelegungen()) {
            String name = String.format("belegung_%s_%s", generation.getName(), i);
            BelegungTemplate.Context ctx = new BelegungTemplate.Context();
            ctx.setBelegung(wagonBelegung);
            belegungTemplate.generiere(ctx, name);
            i++;
        }

    }

    private void erzeugeGeneration(Generation generation) {
        generationen.add(generation);
        String name = "generation_" + generation.getName();
        GenerationTemplate.Context ctx = new GenerationTemplate.Context();
        ctx.setTitel(getTitel());
        ctx.setBeschreibung(getBeschreibung());
        ctx.setGeneration(generation);
        ctx.setStartWagonBelegung(startWagonBelegung);
        generationTemplate.generiere(ctx, name);
        dataJsTemplate.generiere(ctx, name);
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
    public void start(WagonBelegung wagonBelegung) {
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
        GenerationTemplate.Context ctx = new GenerationTemplate.Context();
        ctx.setTitel(getTitel());
        ctx.setBeschreibung(getBeschreibung());
        ctx.setGeneration(gen);
        ctx.setStartWagonBelegung(startWagonBelegung);
        ctx.setGenerationen(generationen);
        indexTemplate.generiere(ctx);
        dataJsTemplate.generiere(ctx, "index");
        wagonJsTemplate.generiere(ctx);
        cssTemplate.generiere(ctx);
    }
}
