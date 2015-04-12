package de.thomasvolk.genexample;


import java.util.Collection;

public class Wagon {
    private final Collection<Sitzplatz> sitzplatzListe;
    private final Collection<Passagier> passagierListe;

    public Wagon(Collection<Sitzplatz> sitzplatzListe, Collection<Passagier> passagierListe) {
        this.sitzplatzListe = sitzplatzListe;
        this.passagierListe = passagierListe;
    }

    public double getZufriedenheit(int[] passagierReihenfolge) {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "sitzplatzListe=" + sitzplatzListe +
                ", passagierListe=" + passagierListe +
                '}';
    }
}
