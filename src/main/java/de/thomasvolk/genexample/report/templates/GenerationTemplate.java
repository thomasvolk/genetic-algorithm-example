package de.thomasvolk.genexample.report.templates;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;
import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GenerationTemplate extends AbstractTemplate<GenerationTemplate.Context> {
    public static class Context {
        private String titel;
        private String beschreibung;
        private Generation generation;
        private WagonBelegung startWagonBelegung;
        private Collection<Generation> generationen;

        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        public Generation getGeneration() {
            return generation;
        }

        public void setGeneration(Generation generation) {
            this.generation = generation;
        }

        public WagonBelegung getStartWagonBelegung() {
            return startWagonBelegung;
        }

        public void setStartWagonBelegung(WagonBelegung startWagonBelegung) {
            this.startWagonBelegung = startWagonBelegung;
        }

        public Collection<Generation> getGenerationen() {
            return generationen;
        }

        public void setGenerationen(Collection<Generation> generationen) {
            this.generationen = generationen;
        }
    }

    public GenerationTemplate(String zielPfad, String name) {
        super(zielPfad, name);
    }

}
