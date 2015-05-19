package de.thomasvolk.genexample.bericht;

import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;

public class HtmlBericht {
    private final String zielPfad;
    private final int schritte;

    public HtmlBericht(String zielPfad, int schritte) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
    }

    public String getZielPfad() {
        return zielPfad;
    }

    public int getSchritte() {
        return schritte;
    }

    public AlgorithmusBericht newAlgorithmusBericht(AlgorithmusTyp algTyp) {
        return new HtmlAlgorithmusBericht(getZielPfad() + "/" + algTyp.name(), getSchritte());
    }

    public void generiere() {
        
    }
}
