package de.thomasvolk.genexample.report;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Map;

public class Template {
    private final String name;

    public Template(String name) {
        this.name = name;
    }

    public void generiere(Map<String, Object> binding, Writer out) {
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/report/" + name))) {
            SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
            groovy.text.Template template = templateEngine.createTemplate(reader);
            Writable writable = template.make(binding);
            writable.writeTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generiere(Map<String, Object> binding, String pfad) {
        try (FileWriter fileWriter = new FileWriter(pfad)) {
            generiere(binding, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
