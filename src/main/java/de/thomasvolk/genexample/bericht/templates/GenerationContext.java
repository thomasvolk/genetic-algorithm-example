package de.thomasvolk.genexample.bericht.templates;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;

import java.util.Collection;

public class GenerationContext {
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
