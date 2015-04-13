package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Passagier;
import de.thomasvolk.genexample.Sitzplatz;
import de.thomasvolk.genexample.SitzplatzVergabe;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstComeFirstServed {
    private final Sitzplatz[] sitzplatzListe;
    private final Passagier[] passagierListe;

    public FirstComeFirstServed(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe) {
        this.sitzplatzListe = sitzplatzListe;
        this.passagierListe = passagierListe;
    }

    public int[] getPassagierReihenfolge() {
        List<Integer> passagierReihenfolge = new ArrayList<>();
        Set<SitzplatzVergabe> vergebenePlaetze = new HashSet<>();
        for (Sitzplatz sp : sitzplatzListe) {
            SitzplatzVergabe besterPlatz = null;
            int index = 0;
            int ausgewaehlterPassagierIndex = 0;
            for (Passagier p : passagierListe) {
                SitzplatzVergabe sitzplatzVergabe = new SitzplatzVergabe(sp, p);
                if (!vergebenePlaetze.contains(sitzplatzVergabe) && !passagierReihenfolge.contains(index)) {
                    if (besterPlatz == null || sitzplatzVergabe.getZufriedenheit() > besterPlatz.getZufriedenheit()) {
                        besterPlatz = sitzplatzVergabe;
                        ausgewaehlterPassagierIndex = index;
                    }
                }
                index++;
            }
            vergebenePlaetze.add(besterPlatz);
            passagierReihenfolge.add(ausgewaehlterPassagierIndex);
        }
        return ArrayUtils.toPrimitive(passagierReihenfolge.toArray(new Integer[passagierListe.length]));
    }
}
