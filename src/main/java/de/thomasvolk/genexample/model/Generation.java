package de.thomasvolk.genexample.model;

import java.util.Collection;

public class Generation {
    private final int nummer;
    private final Collection<WagonBesetzung> wagonBesetzungs;
    private final double zufriedenheit;
    private final WagonBesetzung besterWagonBesetzung;

    public Generation(int nummer, Collection<WagonBesetzung> wagonBesetzungs, double zufriedenheit, WagonBesetzung besterWagon) {
        this.nummer = nummer;
        this.wagonBesetzungs = wagonBesetzungs;
        this.zufriedenheit = zufriedenheit;
        this.besterWagonBesetzung = besterWagon;
    }

    public double getZufriedenheit() {
        return zufriedenheit;
    }

    public int getNummer() {
        return nummer;
    }

    public Collection<WagonBesetzung> getWagonBesetzungs() {
        return wagonBesetzungs;
    }

    public WagonBesetzung getBesterWagonBesetzung() {
        return besterWagonBesetzung;
    }

    public String getName() {
        return String.format("%010d", getNummer());
    }

}