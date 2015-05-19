package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.bericht.AlgorithmusBericht;
import de.thomasvolk.genexample.model.WagonBelegung;

public interface Algorithmus {
    WagonBelegung berechneWagon(AlgorithmusBericht generator);
}
