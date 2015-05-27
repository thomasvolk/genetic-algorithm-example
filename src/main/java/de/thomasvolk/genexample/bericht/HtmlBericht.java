/**
 * Copyright (C) 2015 Thomas Volk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
