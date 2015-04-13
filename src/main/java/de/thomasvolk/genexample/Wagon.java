package de.thomasvolk.genexample;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Wagon {
    private final Sitzplatz[] sitzplatzListe;
    private final Passagier[] passagierListe;
    private int[] passagierReihenfolge;

    private static int[] getInitialPassagierReihenfolge(Collection<Passagier> passagierListe) {
        int[] passagierReihenfolge = new int[passagierListe.size()];
        for(int i=0; i < passagierListe.size(); i++) {
            passagierReihenfolge[i] = i;
        }
        return passagierReihenfolge;
    }

    public Wagon(Collection<Sitzplatz> sitzplatzListe, Collection<Passagier> passagierListe) {
        this(sitzplatzListe, passagierListe, getInitialPassagierReihenfolge(passagierListe));
    }

    public Wagon(Collection<Sitzplatz> sitzplatzListe, Collection<Passagier> passagierListe, int[] passagierReihenfolge) {
        this.sitzplatzListe = sitzplatzListe.toArray(new Sitzplatz[sitzplatzListe.size()]);
        this.passagierListe = passagierListe.toArray(new Passagier[passagierListe.size()]);
        if(sitzplatzListe.size() != passagierListe.size()) {
            throw new IllegalStateException(String.format(
                    "Sitzplatzanzah %s weicht von Passagieranzahl %s ab!", sitzplatzListe.size(), passagierListe.size()));
        }
        HashSet<Integer> sortierungSet = IntStream.of(passagierReihenfolge).mapToObj(i -> i).collect(Collectors.toCollection(HashSet::new));
        if(passagierReihenfolge.length != passagierListe.size()) {
            throw new IndexOutOfBoundsException(String.format(
                    "Laenge der Reihenfolge %s weicht von Passagieranzahl %s ab!", passagierReihenfolge.length, passagierListe.size()));
        }
        if(sortierungSet.size() != passagierListe.size()) {
            throw new ArrayStoreException("Reihenfolge enthält dubletten!");
        }
        this.passagierReihenfolge = passagierReihenfolge;
    }

    public double getZufriedenheit() {
        return getSitzplatzVergabeListe().stream().map(SitzplatzVergabe::getZufriedenheit).reduce(0.0, (a, v) -> a + v);
    }

    public Collection<SitzplatzVergabe> getSitzplatzVergabeListe() {
        Collection<SitzplatzVergabe> result = new ArrayList<>();
        for(int i = 0; i < passagierReihenfolge.length; i++) {
            int passagier = passagierReihenfolge[i];
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
