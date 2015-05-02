package de.thomasvolk.genexample;

import de.thomasvolk.genexample.algorithmus.Generation;
import de.thomasvolk.genexample.model.Wagon;

import java.util.stream.Stream;

public interface Report {
    void start(Wagon wagon);
    void evolutionsSchritt(Generation generation);
    void ende(Generation generation);
}
