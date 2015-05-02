package de.thomasvolk.genexample.model;

import java.util.stream.Stream;

public class Generation {
    private final int nummer;
    private final Stream<Wagon> wagons;
    private final double zufriedenheit;
    private final Wagon besterWagon;

    public Generation(int nummer, Stream<Wagon> wagons, double zufriedenheit, Wagon besterWagon) {
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

    public Stream<Wagon> getWagons() {
        return wagons;
    }

    public Wagon getBesterWagon() {
        return besterWagon;
    }

    public String getName() {
        return String.format("%010d", getNummer());
    }

}