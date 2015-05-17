package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;
import de.thomasvolk.genexample.model.WagonBelegung;

public abstract class AbstractAlgorithmus implements Algorithmus {
    private final WagonBelegung wagonBelegung;

    public AbstractAlgorithmus(Wagon wagon, Passagier[] passagierListe) {
        this.wagonBelegung = new WagonBelegung(wagon, passagierListe);
    }

    public Sitzplatz[] getSitzplatzListe() {
        return wagonBelegung.getSitzplatzListe();
    }

    public Passagier[] getPassagierListe() {
        return wagonBelegung.getPassagierListe();
    }

    public WagonBelegung getWagonBelegung() {
        return wagonBelegung;
    }
}
