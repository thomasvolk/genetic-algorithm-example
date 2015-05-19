package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.bericht.Report;
import de.thomasvolk.genexample.model.WagonBelegung;

public interface Algorithmus {
    WagonBelegung berechneWagon(Report generator);
}
