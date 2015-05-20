package de.thomasvolk.genexample.bericht;

import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.bericht.templates.Template;
import de.thomasvolk.genexample.model.Wagon;

import java.util.*;

public class HtmlBericht {
    private final String zielPfad;
    private final int schritte;
    private final Wagon wagon;
    private final Template wagonJsTemplate;
    private final Template cssTemplate;
    private final Template indexTemplate;
    private final Set<String> algorithmusTypen = new HashSet<>();

    public HtmlBericht(String zielPfad, int schritte, Wagon wagon) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
        this.wagon = wagon;
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
        algorithmusTypen.add(algTyp.name());
        return new HtmlAlgorithmusBericht(getZielPfad() + "/" + algTyp.name(), getSchritte());
    }

    public void generiere() {
        wagonJsTemplate.generiere(getZielPfad());
        cssTemplate.generiere(getZielPfad());
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("algorithmusTypen", algorithmusTypen);
        ctx.put("wagon", wagon);
        indexTemplate.generiere(getZielPfad(), ctx);
    }
}
