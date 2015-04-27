package de.thomasvolk.genexample.report;

import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Wagon;

import java.util.stream.Stream;

public class HtmlReport implements Report {
    private final String zielPfad;

    public HtmlReport(String zielPfad) {
        this.zielPfad = zielPfad;
    }

    @Override
    public void evolutionsSchritt(int num, Stream<Wagon> wagons) {

    }

    @Override
    public void bestesErgebnis(Wagon wagon) {

    }
}
