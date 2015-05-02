package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.Wagon;

public interface Report {
    void start(Wagon wagon);
    void evolutionsSchritt(Generation generation);
    void ende(Generation generation);
}
