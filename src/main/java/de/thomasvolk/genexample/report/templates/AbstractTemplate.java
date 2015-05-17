package de.thomasvolk.genexample.report.templates;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import org.apache.commons.io.FilenameUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTemplate<T> {
    private final String name;
    private final String extension;
    private final String zielPfad;


    public AbstractTemplate(String zielPfad, String name) {
        this.zielPfad = zielPfad;
        this.name = FilenameUtils.getBaseName(name);
        this.extension = FilenameUtils.getExtension(name);
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getZielPfad() {
        return zielPfad;
    }

    protected String getTargetPath(String name) {
        return FilenameUtils.concat(getZielPfad(), name);
    }


    public void generiere(T ctx, String newName) {
        try (FileWriter fileWriter = new FileWriter(getTargetPath(newName + "." + getExtension()))) {
            generiere(ctx, newName, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generiere(T ctx) {
        generiere(ctx, getName());
    }

    public void generiere(T ctx, String newName, Writer out) {
        Map<String, Object> binding = new HashMap<>();
        binding.put("ctx", ctx);
        binding.put("templateName", newName);
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/report/" + getName() +
                "." + getExtension()))) {
            SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
            groovy.text.Template template = templateEngine.createTemplate(reader);
            Writable writable = template.make(binding);
            writable.writeTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
