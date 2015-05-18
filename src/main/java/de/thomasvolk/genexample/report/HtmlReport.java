package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.report.templates.BelegungContext;
import de.thomasvolk.genexample.report.templates.GenerationContext;
import de.thomasvolk.genexample.report.templates.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class HtmlReport implements Report {
    private final int schritte;
    private final Template generationTemplate;
    private final Template belegungTemplate;
    private final Template indexTemplate;
    private final Template wagonJsTemplate;
    private final Template dataJsTemplate;
    private final Template cssTemplate;
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
        generationTemplate = new Template(zielPfad, "generation.html");
        belegungTemplate = new Template(zielPfad, "belegung.html");
        indexTemplate = new Template(zielPfad, "index.html");
        wagonJsTemplate = new Template(zielPfad, "wagon.js");
        dataJsTemplate = new Template(zielPfad, "data.js");
        cssTemplate = new Template(zielPfad, "default.css");
    }

    private void erzeugebelegung(Generation generation) {
        int i = 0;
        for(WagonBelegung wagonBelegung: generation.getWagonBelegungen()) {
            String name = String.format("belegung_%s_%s", generation.getName(), i);
            BelegungContext ctx = new BelegungContext();
            ctx.setBelegung(wagonBelegung);
            belegungTemplate.generiere(ctx, name);
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
        GenerationContext ctx = new GenerationContext();
        ctx.setTitel(getTitel());
        ctx.setBeschreibung(getBeschreibung());
        ctx.setGeneration(gen);
        ctx.setStartWagonBelegung(startWagonBelegung);
        ctx.setGenerationen(generationen);
        indexTemplate.generiere(ctx);
        dataJsTemplate.generiere(ctx, "index");
        wagonJsTemplate.generiere(ctx);
        cssTemplate.generiere(ctx);
        BelegungContext belegungCtx = new BelegungContext();
        belegungCtx.setBelegung(gen.getBesteWagonBelegung());
        belegungTemplate.generiere(belegungCtx);
    }
}
