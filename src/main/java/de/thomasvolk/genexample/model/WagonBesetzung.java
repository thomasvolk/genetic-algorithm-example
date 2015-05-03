package de.thomasvolk.genexample.model;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WagonBesetzung {
    private final Sitzplatz[] sitzplatzListe;
    private final Passagier[] passagierListe;
    private int[] passagierReihenfolge;

    private static int[] getInitialPassagierReihenfolge(int length) {
        int[] passagierReihenfolge = new int[length];
        for(int i=0; i < length; i++) {
            passagierReihenfolge[i] = i;
        }
        return passagierReihenfolge;
    }

    public WagonBesetzung(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe) {
        this(sitzplatzListe, passagierListe, getInitialPassagierReihenfolge(passagierListe.length));
    }

    public WagonBesetzung(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe, int[] passagierReihenfolge) {
        this.sitzplatzListe = sitzplatzListe;
        this.passagierListe = passagierListe;
        if(sitzplatzListe.length != passagierListe.length) {
            throw new IllegalStateException(String.format(
                    "Sitzplatzanzah %s weicht von Passagieranzahl %s ab!", sitzplatzListe.length, passagierListe.length));
        }
        HashSet<Integer> sortierungSet = IntStream.of(passagierReihenfolge).mapToObj(i -> i).collect(Collectors.toCollection(HashSet::new));
        if(passagierReihenfolge.length != passagierListe.length) {
            throw new IndexOutOfBoundsException(String.format(
                    "Laenge der Reihenfolge %s weicht von Passagieranzahl %s ab!", passagierReihenfolge.length, passagierListe.length));
        }
        if(sortierungSet.size() != passagierListe.length) {
            throw new ArrayStoreException("Reihenfolge enthält dubletten!");
        }
        this.passagierReihenfolge = passagierReihenfolge;
    }

    public double getZufriedenheit() {
        return getSitzplatzVergabeListe().stream().map(SitzplatzVergabe::getZufriedenheit).reduce(0.0, (a, v) -> a + v);
    }

    public double getMaximaleZufriedenheit() {
        return getSitzplatzVergabeListe().stream().map(SitzplatzVergabe::getPassagier).map(Passagier::getMaximaleZufriedenheit).reduce(0.0, (a, v) -> a + v);
    }

    public int[] getPassagierReihenfolge() {
        return passagierReihenfolge;
    }

    public List<SitzplatzVergabe> getSitzplatzVergabeListe() {
        List<SitzplatzVergabe> result = new ArrayList<>();
        for(int i = 0; i < passagierReihenfolge.length; i++) {
            int sitzplatz = passagierReihenfolge[i];
            result.add(new SitzplatzVergabe(sitzplatzListe[sitzplatz], passagierListe[i]));
        }
        result.sort((sv1, sv2) -> sv1.getSitzplatz().getNummer() - sv2.getSitzplatz().getNummer());
        return result;
    }

    public WagonBesetzung copy(int[] newPassagierReihenfolge) {
       return new WagonBesetzung(sitzplatzListe, passagierListe, newPassagierReihenfolge);
    }

    public Passagier[] getPassagierListe() {
        return passagierListe;
    }

    public Sitzplatz[] getSitzplatzListe() {
        return sitzplatzListe;
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "plaetze=" + getAnzahlPlaetze() +
                ", passagierReihenfolge=" + Arrays.asList(passagierReihenfolge) +
                '}';
    }

    public int getAnzahlPlaetze() {
        return getSitzplatzListe().length;
    }
}
