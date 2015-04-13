package de.thomasvolk.genexample;


import java.util.ArrayList;
import java.util.Collection;

public class Wagon {
    private final Sitzplatz[] sitzplatzListe;
    private final Passagier[] passagierListe;
    private int[] passagierReihenfolge;

    public Wagon(Collection<Sitzplatz> sitzplatzListe, Collection<Passagier> passagierListe) {
        this.sitzplatzListe = sitzplatzListe.toArray(new Sitzplatz[sitzplatzListe.size()]);
        this.passagierListe = passagierListe.toArray(new Passagier[passagierListe.size()]);
        if(sitzplatzListe.size() != passagierListe.size()) {
            throw new IllegalStateException(String.format(
                    "Sitzplatzanzah %s weicht von Passagieranzahl %s ab!", sitzplatzListe.size(), passagierListe.size()));
        }
        passagierReihenfolge = new int[passagierListe.size()];
        for(int i=0; i < passagierListe.size(); i++) {
            passagierReihenfolge[i] = i;
        }
    }

    public int[] getPassagierReihenfolge() {
        return passagierReihenfolge;
    }

    public void setPassagierReihenfolge(int[] passagierReihenfolge) {
        if(passagierReihenfolge.length != passagierListe.length) {
            throw new IllegalStateException(String.format(
                    "Laenge der Reihenfolge %s weicht von Passagieranzahl %s ab!", passagierReihenfolge.length, passagierListe.length));
        }
        this.passagierReihenfolge = passagierReihenfolge;
    }

    public double getZufriedenheit() {
        return 0.0;
    }

    public Collection<SitzplatzVergabe> getSitzplatzVergabeListe() {
        Collection<SitzplatzVergabe> result = new ArrayList<>();
        for(int i = 0; i < getPassagierReihenfolge().length; i++) {
            int passagier = getPassagierReihenfolge()[i];
            result.add(new SitzplatzVergabe(sitzplatzListe[i], passagierListe[passagier]));
        }
        return result;
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "sitzplatzListe=" + sitzplatzListe +
                ", passagierListe=" + passagierListe +
                '}';
    }
}
