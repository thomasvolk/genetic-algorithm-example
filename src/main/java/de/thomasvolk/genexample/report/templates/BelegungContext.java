package de.thomasvolk.genexample.report.templates;

import de.thomasvolk.genexample.model.WagonBelegung;

public class BelegungContext {
    private WagonBelegung belegung;

    public WagonBelegung getBelegung() {
        return belegung;
    }

    public void setBelegung(WagonBelegung belegung) {
        this.belegung = belegung;
    }
}
