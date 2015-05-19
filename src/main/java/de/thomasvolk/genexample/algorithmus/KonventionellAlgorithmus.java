package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.bericht.AlgorithmusBericht;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class KonventionellAlgorithmus extends AbstractAlgorithmus {

    public KonventionellAlgorithmus(Passagier[] passagierListe, Wagon wagon) {
        super(wagon, passagierListe);
    }

    @Override
    public WagonBelegung berechneWagon(AlgorithmusBericht algorithmusBericht) {
        algorithmusBericht.start(getWagonBelegung());
        List<Integer> passagierReihenfolge = new ArrayList<>();
        Set<SitzplatzVergabe> vergebenePlaetze = new HashSet<>();
        for (Passagier p : getPassagierListe()) {
            SitzplatzVergabe besterPlatz = null;
            int index = 0;
            int ausgewaehlterPassagierIndex = 0;
            for (Sitzplatz sp : getSitzplatzListe()) {
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
        WagonBelegung wagonBelegung = new WagonBelegung(getWagonBelegung().getWagon(), getPassagierListe(),
                ArrayUtils.toPrimitive(passagierReihenfolge.toArray(new Integer[getPassagierListe().length])));
        algorithmusBericht.ende(new Generation(0, Collections.singleton(wagonBelegung), wagonBelegung.getZufriedenheit(), wagonBelegung));
        return wagonBelegung;
    }
}
