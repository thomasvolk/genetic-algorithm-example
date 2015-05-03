package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.WagonBesetzung;

public abstract class AbstractAlgorithmus implements Algorithmus {
    protected final Sitzplatz[] sitzplatzListe;
    protected final Passagier[] passagierListe;
    protected WagonBesetzung wagonBesetzung;

    public AbstractAlgorithmus(Sitzplatz[] sitzplatzListe, Passagier[] passagierListe) {
        this.sitzplatzListe = sitzplatzListe;
        this.passagierListe = passagierListe;
        this.wagonBesetzung = new WagonBesetzung(sitzplatzListe, passagierListe);
    }

    public Sitzplatz[] getSitzplatzListe() {
        return sitzplatzListe;
    }

    public Passagier[] getPassagierListe() {
        return passagierListe;
    }

    public WagonBesetzung getWagonBesetzung() {
        return wagonBesetzung;
    }
}
