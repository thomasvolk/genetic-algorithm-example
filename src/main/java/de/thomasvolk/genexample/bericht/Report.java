package de.thomasvolk.genexample.bericht;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBelegung;

public interface Report {
    void start(WagonBelegung wagonBelegung);
    void evolutionsSchritt(Generation generation);
    void ende(Generation generation);
}