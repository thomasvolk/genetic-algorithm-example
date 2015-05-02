package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.Wagon;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HtmlReport implements Report {
    public static class Template {
        private final String name;

        public Template(String name) {
            this.name = name;
        }

        public void generiere(Map<String, Object> binding, Writer out) {
            try(InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/report/" + name))) {
                SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
                groovy.text.Template template = templateEngine.createTemplate(reader);
                Writable writable = template.make(binding);
                writable.writeTo(out);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void generiere(Map<String, Object> binding, String pfad) {
            try(FileWriter fileWriter = new FileWriter(pfad)) {
                generiere(binding, fileWriter);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private final String zielPfad;
    private final int schritte;
    private final Template generationTemplate = new Template("generation.html");
    private final Template indexTemplate = new Template("index.html");
    private final Template wagonJsTemplate = new Template("wagon.js");
    private final Template cssTemplate = new Template("default.css");
    private Generation letzteGeneration;
    private Collection<Generation> generationen = new ArrayList<>();
    private Wagon startWagon;

    public HtmlReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlReport(String zielPfad, int schritte) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
    }

    private String getPath(String name) {
        return FilenameUtils.concat(zielPfad, name);
    }

    private void erzeugeGeneration(Generation generation) {
        Map<String, Object> binding = new HashMap<>();
        binding.put("generation", generation);
        generationen.add(generation);
        generationTemplate.generiere(binding, getPath("generation_" + generation.getName() + ".html"));
    }

    @Override
    public void start(Wagon wagon) {
        startWagon = wagon;
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
        Map<String, Object> binding = new HashMap<>();
        binding.put("generation", gen);
        binding.put("generationen", generationen);
        binding.put("startWagon", startWagon);
        indexTemplate.generiere(binding, getPath("index.html"));
        wagonJsTemplate.generiere(binding, getPath("wagon.js"));
        cssTemplate.generiere(binding, getPath("default.css"));
    }
}
