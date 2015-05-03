package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBesetzung;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import org.apache.commons.io.FilenameUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
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
            binding.put("templateName", newName);
            binding.put("generation", gen);
            binding.put("generationen", generationen);
            binding.put("startWagon", startWagonBesetzung);
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
    private WagonBesetzung startWagonBesetzung;

    public HtmlReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlReport(String zielPfad, int schritte) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
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

    @Override
    public void start(WagonBesetzung wagonBesetzung) {
        startWagonBesetzung = wagonBesetzung;
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
