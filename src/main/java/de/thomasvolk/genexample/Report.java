package de.thomasvolk.genexample;

import de.thomasvolk.genexample.model.Wagon;

import java.util.stream.Stream;

public interface Report {
    void evolutionsSchritt(int num, Stream<Wagon> wagons);
    void bestesErgebnis(Wagon wagon);
}
