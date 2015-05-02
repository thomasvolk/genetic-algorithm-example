package de.thomasvolk.genexample.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generation {
    private final int nummer;
    private final Collection<Wagon> wagons;
    private final double zufriedenheit;
    private final Wagon besterWagon;

    public Generation(int nummer, Collection<Wagon> wagons, double zufriedenheit, Wagon besterWagon) {
        this.nummer = nummer;
        this.wagons = wagons;
        this.zufriedenheit = zufriedenheit;
        this.besterWagon = besterWagon;
    }

    public double getZufriedenheit() {
        return zufriedenheit;
    }

    public int getNummer() {
        return nummer;
    }

    public Collection<Wagon> getWagons() {
        return wagons;
    }

    public Wagon getBesterWagon() {
        return besterWagon;
    }

    public String getName() {
        return String.format("%010d", getNummer());
    }

}