package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Wagon;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
    private Stream<Wagon> letzteWagons;
    private Integer letzteGeneration;

    public HtmlReport(String zielPfad) {
        this(zielPfad, 1);
    }

    public HtmlReport(String zielPfad, int schritte) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
    }

    private String getGenerationDateiname(int num) {
        return String.format("generation_%010d.html", num);
    }

    private String getPath(String name) {
        return FilenameUtils.concat(zielPfad, name);
    }

    private void erzeugeGeneration(int num, Stream<Wagon> wagons) {
        Map<String, Object> binding = new HashMap<>();
        binding.put("wagons", wagons);
        binding.put("generation", num);
        generationTemplate.generiere(binding, getPath(getGenerationDateiname(num)));
    }

    @Override
    public void evolutionsSchritt(int num, Stream<Wagon> wagons) {
        if(num % schritte == 0) {
            erzeugeGeneration(num, wagons);
            letzteGeneration = null;
            letzteWagons = null;
        }
        else {
            letzteGeneration = num;
            letzteWagons = wagons;
        }
    }

    @Override
    public void bestesErgebnis(int num, Wagon wagon) {
        if(letzteGeneration != null) {
            erzeugeGeneration(letzteGeneration, letzteWagons);
        }
        Map<String, Object> binding = new HashMap<>();
        binding.put("wagon", wagon);
        binding.put("generation", num);
        indexTemplate.generiere(binding, getPath("index.html"));
        wagonJsTemplate.generiere(binding, getPath("wagon.js"));
        cssTemplate.generiere(binding, getPath("default.css"));
    }
}
