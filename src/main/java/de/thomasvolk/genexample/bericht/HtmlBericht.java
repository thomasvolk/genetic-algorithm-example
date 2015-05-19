package de.thomasvolk.genexample.bericht;

import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.bericht.templates.Template;

public class HtmlBericht {
    private final String zielPfad;
    private final int schritte;
    private final Template wagonJsTemplate;
    private final Template cssTemplate;
    private final Template indexTemplate;

    public HtmlBericht(String zielPfad, int schritte) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
        wagonJsTemplate = new Template("wagon.js");
        cssTemplate = new Template("default.css");
        indexTemplate = new Template("index.html");
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
        wagonJsTemplate.generiere(getZielPfad());
        cssTemplate.generiere(getZielPfad());
        indexTemplate.generiere(getZielPfad());
    }
}
