package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;

public abstract class AbstractAlgorithmus implements Algorithmus {
    private final Sitzplatz[] sitzplatzListe;
    private final Passagier[] passagierListe;

    public AbstractAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        this.passagierListe = passagierListe;
        this.sitzplatzListe = sitzplatzListe;
    }

    public Sitzplatz[] getSitzplatzListe() {
        return sitzplatzListe;
    }

    public Passagier[] getPassagierListe() {
        return passagierListe;
    }

}
