package de.thomasvolk.genexample.bericht.templates;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import org.apache.commons.io.FilenameUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Template {
    private final String name;
    private final String dir;
    private final String extension;


    public Template(String name) {
        this.name = FilenameUtils.getBaseName(name);
        this.dir = FilenameUtils.getPath(name);
        this.extension = FilenameUtils.getExtension(name);
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    protected String getTargetPath(String zielPfad, String name) {
        return FilenameUtils.concat(zielPfad, name);
    }

    public void generiere(String zielPfad) {
        generiere(zielPfad, (Object)null);
    }

    public void generiere(String zielPfad, Object ctx) {
        generiere(zielPfad, ctx, getName());
    }

    public void generiere(String zielPfad, Object ctx, String newName) {
        try (FileWriter fileWriter = new FileWriter(getTargetPath(zielPfad, newName + "." + getExtension()))) {
            Map<String, Object> binding = new HashMap<>();
            binding.put("ctx", ctx);
            binding.put("templateName", newName);
            generiere(fileWriter, binding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void generiere(Writer out, Map<String, Object> binding) {
        try (InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/report/" + getDir() + "/" + getName() + "." + getExtension()))) {
            SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
            groovy.text.Template template = templateEngine.createTemplate(reader);
            Writable writable = template.make(binding);
            writable.writeTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDir() {
        return dir;
    }
}
