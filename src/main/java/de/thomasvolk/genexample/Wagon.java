package de.thomasvolk.genexample;


import java.util.Collection;

public class Wagon {
    private final Collection<Sitzplatz> sitzplatzListe;

    public Wagon(Collection<Sitzplatz> sitzplatzListe) {
        this.sitzplatzListe = sitzplatzListe;
    }

    public Collection<Sitzplatz> getSitzplatzListe() {
        return sitzplatzListe;
    }
}
