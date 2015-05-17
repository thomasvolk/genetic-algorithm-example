package de.thomasvolk.genexample.report.templates;

import de.thomasvolk.genexample.model.WagonBelegung;

import java.io.Writer;

public class BelegungTemplate extends AbstractTemplate<BelegungTemplate.Context> {
    public static class Context {
        private WagonBelegung belegung;
    }

    public BelegungTemplate(String zielPfad, String name) {
        super(zielPfad, name);
    }

    @Override
    public void generiere(Context ctx, String newName, Writer out) {

    }

}
