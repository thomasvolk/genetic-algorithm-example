package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.Wagon;

public abstract class AbstractAlgorithmus implements Algorithmus {
    protected final Sitzplatz[] sitzplatzListe;
    protected final Passagier[] passagierListe;
    protected Wagon wagon;

    public AbstractAlgorithmus(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe) {
        this.sitzplatzListe = sitzplatzListe;
        this.passagierListe = passagierListe;
        this.wagon = new Wagon(sitzplatzListe, passagierListe);
    }

    public Sitzplatz[] getSitzplatzListe() {
        return sitzplatzListe;
    }

    public Passagier[] getPassagierListe() {
        return passagierListe;
    }

    public Wagon getWagon() {
        return wagon;
    }
}
