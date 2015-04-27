package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Wagon;

public interface Algorithmus {
    Wagon getWagon(Report generator);
}
