package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.report.Report;
import de.thomasvolk.genexample.model.Wagon;

public interface Algorithmus {
    Wagon berechneWagon(Report generator);
}
