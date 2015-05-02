package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.report.Report;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.stream.Stream;

public class FirstComeFirstServed extends AbstractAlgorithmus {

    public FirstComeFirstServed(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe) {
        super(sitzplatzListe, passagierListe);
    }

    @Override
    public Wagon berechneWagon(Report report) {
        report.start(getWagon());
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
        Wagon wagon = new Wagon(getSitzplatzListe(), getPassagierListe(),
                ArrayUtils.toPrimitive(passagierReihenfolge.toArray(new Integer[getPassagierListe().length])));
        report.ende(new Generation(0, Collections.singleton(wagon), wagon.getZufriedenheit(), wagon));
        return wagon;
    }
}
