package de.thomasvolk.genexample.bericht;

import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.bericht.templates.Template;
import de.thomasvolk.genexample.model.WagonBelegung;

import java.util.*;

public class HtmlBericht {
    private final String zielPfad;
    private final int schritte;
    private final WagonBelegung wagonBelegung;
    private final Template wagonJsTemplate;
    private final Template cssTemplate;
    private final Template indexTemplate;
    private final Template wagonBelegungDataJsTemplate;
    private final Set<String> algorithmusTypen = new HashSet<>();

    public HtmlBericht(String zielPfad, int schritte, WagonBelegung wagonBelegung) {
        this.zielPfad = zielPfad;
        this.schritte = schritte;
        this.wagonBelegung = wagonBelegung;
        wagonJsTemplate = new Template("wagon.js");
        cssTemplate = new Template("default.css");
        indexTemplate = new Template("index.html");
        wagonBelegungDataJsTemplate = new Template("data.js");
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
        ctx.put("startWagonBelegung", wagonBelegung);
        indexTemplate.generiere(getZielPfad(), ctx);
        wagonBelegungDataJsTemplate.generiere(getZielPfad(), ctx, "index");
    }
}
