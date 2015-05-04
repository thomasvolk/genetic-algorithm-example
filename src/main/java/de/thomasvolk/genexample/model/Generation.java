package de.thomasvolk.genexample.model;

import java.util.Collection;

public class Generation {
    private final int nummer;
    private final Collection<WagonBelegung> wagonBelegungen;
    private final double zufriedenheit;
    private final WagonBelegung besteWagonBelegung;

    public Generation(int nummer, Collection<WagonBelegung> wagonBelegungen, double zufriedenheit, WagonBelegung besterWagon) {
        this.nummer = nummer;
        this.wagonBelegungen = wagonBelegungen;
        this.zufriedenheit = zufriedenheit;
        this.besteWagonBelegung = besterWagon;
    }

    public double getZufriedenheit() {
        return zufriedenheit;
    }

    public int getNummer() {
        return nummer;
    }

    public Collection<WagonBelegung> getWagonBelegungen() {
        return wagonBelegungen;
    }

    public WagonBelegung getBesteWagonBelegung() {
        return besteWagonBelegung;
    }

    public String getName() {
        return String.format("%010d", getNummer());
    }

}