package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.model.WagonBesetzung;

public interface Report {
    void start(WagonBesetzung wagonBesetzung);
    void evolutionsSchritt(Generation generation);
    void ende(Generation generation);
}
