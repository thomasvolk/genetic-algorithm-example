package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Wagon;

import java.util.stream.Stream;

public interface GenerationenProvider {
    interface GenerationHandler {
        void evolutionsSchritt(int num, Stream<Wagon> wagons);
    }
    void setGenerationHandler(GenerationHandler handler);
}
