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
        if(passagierReihenfolge.length != passagierListe.size()) {
            throw new IllegalStateException(String.format(
                    "Reihenfolge Laenge=%s weicht von Passagier Anzahl %s ab.", passagierReihenfolge.length, passagierListe.size()));
        }
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
