package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HtmlReport implements Report {
    private class Template {
        private final String name;
        private final String extension;

        public Template(String name) {
            this.name = FilenameUtils.getBaseName(name);
            this.extension = FilenameUtils.getExtension(name);
        }

        public void generiere(Generation gen, String newName, Writer out) {
            Map<String, Object> binding = new HashMap<>();
            binding.put("titel", getTitel());
            binding.put("beschreibung", getBeschreibung());
            binding.put("templateName", newName);
            binding.put("generation", gen);
            binding.put("generationen", generationen);
            binding.put("startWagon", startWagonBelegung);
            try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/report/" + name + "." + extension))) {
                SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
                groovy.text.Template template = templateEngine.createTemplate(reader);
                Writable writable = template.make(binding);
                writable.writeTo(out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void generiere(Generation gen, String newName) {
            try (FileWriter fileWriter = new FileWriter(getTargetPath(newName + "." + extension))) {
                generiere(gen, newName, fileWriter);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void generiere(Generation gen) {
            generiere(gen, name);
        }
    }
    private final String zielPfad;
    private final int schritte;
    private final Template generationTemplate = new Template("generation.html");
    private final Template indexTemplate = new Template("index.html");
    private final Template wagonJsTemplate = new Template("wagon.js");
    private final Template dataJsTemplate = new Template("data.js");
    private final Template cssTemplate = new Template("default.css");
    private Generation letzteGeneration;
    private Collection<Generation> generationen = new ArrayList<>();
    private WagonBelegung startWagonBelegung;
    private String titel = "";
    private String beschreibung = "";


    public HtmlReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlReport(String zielPfad, int schritte) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
        new File(zielPfad).mkdirs();
    }

    private String getTargetPath(String name) {
        return FilenameUtils.concat(zielPfad, name);
    }

    private void erzeugeGeneration(Generation generation) {
        generationen.add(generation);
        String name = "generation_" + generation.getName();
        generationTemplate.generiere(generation, name);
        dataJsTemplate.generiere(generation, name);
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
        }
        indexTemplate.generiere(gen);
        dataJsTemplate.generiere(gen, "index");
        wagonJsTemplate.generiere(gen);
        cssTemplate.generiere(gen);
    }
}
