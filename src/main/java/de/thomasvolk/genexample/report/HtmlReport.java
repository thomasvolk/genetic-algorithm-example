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
    private Template generationTemplate = new Template("generation.html");
    private Template indexTemplate = new Template("index.html");

    public HtmlReport(String zielPfad) {
        this.zielPfad = zielPfad;
    }

    protected String getGenerationDateiname(int num) {
        return String.format("generation_%010d.html", num);
    }

    @Override
    public void evolutionsSchritt(int num, Stream<Wagon> wagons) {
        Map<String, Object> binding = new HashMap<>();
        binding.put("wagons", wagons);
        binding.put("generation", num);
        generationTemplate.generiere(binding, FilenameUtils.concat(zielPfad, getGenerationDateiname(num)));
    }

    @Override
    public void bestesErgebnis(int num, Wagon wagon) {
        Map<String, Object> binding = new HashMap<>();
        binding.put("wagon", wagon);
        binding.put("generation", num);
        generationTemplate.generiere(binding, FilenameUtils.concat(zielPfad, "index.html"));
    }
}
