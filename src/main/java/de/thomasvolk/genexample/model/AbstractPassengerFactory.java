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
package de.thomasvolk.genexample.model;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class AbstractPassengerFactory implements PassagierFactory {
    public static final String FENSTERPLATZ = "Fensterplatz";
    public static final String IN_FAHRTRICHTUNG = "in Fahrtrichtung";
    public static final String ABTEIL = "Abteil";

    @Override
    public List<Passenger> lese(InputStream is, int anzahl) throws IOException {
        List<Passenger> passagiere = lesePassagiere(is, anzahl);
        int count = passagiere.size();
        while(count < anzahl) {
            count++;
            passagiere.add(new Passenger(count, Request.NULL));
        }
        return passagiere;
    }

    protected int getWertung(String inEingabe) {
        String eingabe = inEingabe.trim();
        if(StringUtils.isEmpty(eingabe)) {
            return Request.NULL_GEWICHTUNG;
        }
        try {
            return Integer.valueOf(eingabe);
        } catch (NumberFormatException e) {
            int anzahl = eingabe.length();
            return Request.EINFACHE_GEWICHTUNG * anzahl;
        }
    }

    protected abstract List<Passenger> lesePassagiere(InputStream is, int anzahl) throws IOException;
}
