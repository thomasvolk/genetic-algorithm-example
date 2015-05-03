package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.report.Report;
import de.thomasvolk.genexample.model.WagonBesetzung;

public interface Algorithmus {
    WagonBesetzung berechneWagon(Report generator);
}
