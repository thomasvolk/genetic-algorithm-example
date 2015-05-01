package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Wagon;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class HtmlReport implements Report {
    public static class Template {
        private final String name;

        public Template(String name) {
            this.name = name;
        }

        public void generiere(Map<String, Object> binding, Writer out) throws IOException {
            InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("report/" + name));
            SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
            groovy.text.Template template = templateEngine.createTemplate(reader);
            Writable writable = template.make(binding);
            writable.writeTo(out);
        }
    }
    private final String zielPfad;
    private Template generationTemplate = new Template("generation.html");
    private Template indexTemplate = new Template("index.html");

    public HtmlReport(String zielPfad) {
        this.zielPfad = zielPfad;
    }

    @Override
    public void evolutionsSchritt(int num, Stream<Wagon> wagons) {

    }

    @Override
    public void bestesErgebnis(Wagon wagon) {

    }
}
