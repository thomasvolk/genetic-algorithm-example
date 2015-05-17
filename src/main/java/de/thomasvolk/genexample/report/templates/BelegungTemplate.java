package de.thomasvolk.genexample.report.templates;

import de.thomasvolk.genexample.model.WagonBelegung;

import java.io.Writer;

public class BelegungTemplate extends AbstractTemplate<BelegungTemplate.Context> {
    public static class Context {
        private WagonBelegung belegung;

        public WagonBelegung getBelegung() {
            return belegung;
        }

        public void setBelegung(WagonBelegung belegung) {
            this.belegung = belegung;
        }
    }

    public BelegungTemplate(String zielPfad, String name) {
        super(zielPfad, name);
    }
}
