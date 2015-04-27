package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.Report;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.SitzplatzVergabe;
import de.thomasvolk.genexample.model.Wagon;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstComeFirstServed extends AbstractAlgorithmus {

    public FirstComeFirstServed(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe) {
        super(passagierListe, sitzplatzListe);
    }

    @Override
    public Wagon getWagon(Report report) {
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
        report.bestesErgebnis(wagon);
        return wagon;
    }
}
