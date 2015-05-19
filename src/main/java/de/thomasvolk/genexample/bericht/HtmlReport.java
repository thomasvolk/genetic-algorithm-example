package de.thomasvolk.genexample.bericht;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;
import de.thomasvolk.genexample.bericht.templates.BelegungContext;
import de.thomasvolk.genexample.bericht.templates.GenerationContext;
import de.thomasvolk.genexample.bericht.templates.Template;

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
    private final String zielPfad;

    public HtmlReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlReport(String zielPfad, int schritte) {
        this.schritte = schritte;
        new File(zielPfad).mkdirs();
        this.zielPfad = zielPfad;
        generationTemplate = new Template("algorithmus/generation.html");
        belegungTemplate = new Template("algorithmus/belegung.html");
        indexTemplate = new Template("algorithmus/index.html");
        dataJsTemplate = new Template("algorithmus/data.js");
        wagonJsTemplate = new Template("wagon.js");
        cssTemplate = new Template("default.css");
    }

    private void erzeugebelegung(Generation generation) {
        int i = 0;
        for(WagonBelegung wagonBelegung: generation.getWagonBelegungen()) {
            String name = String.format("belegung_%s_%s", generation.getName(), i);
            BelegungContext ctx = new BelegungContext();
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
        indexTemplate.generiere(getZielPfad(), ctx);
        dataJsTemplate.generiere(getZielPfad(), ctx, "index");
        wagonJsTemplate.generiere(getZielPfad());
        cssTemplate.generiere(getZielPfad());
        BelegungContext belegungCtx = new BelegungContext();
        belegungCtx.setBelegung(gen.getBesteWagonBelegung());
        belegungTemplate.generiere(getZielPfad(), belegungCtx);
    }
}
