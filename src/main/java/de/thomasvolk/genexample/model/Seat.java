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

public class Seat {
    private final int nummer;
    private final int reihe;
    private final int position;
    private final boolean inFahrtrichtung;
    private final boolean abteil;
    private final boolean fenster;

    public Seat(int nummer, int reihe, int position, boolean fenster, boolean inFahrtrichtung, boolean abteil) {
        this.nummer = nummer;
        this.reihe = reihe;
        this.position = position;
        this.fenster = fenster;
        this.inFahrtrichtung = inFahrtrichtung;
        this.abteil = abteil;
    }

    public int getReihe() {
        return reihe;
    }

    public int getPosition() {
        return position;
    }

    public boolean isInFahrtrichtung() {
        return inFahrtrichtung;
    }

    public boolean isAbteil() {
        return abteil;
    }

    public boolean isFenster() {
        return fenster;
    }

    public int getNummer() {
        return nummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat sitzplatz = (Seat) o;

        return nummer == sitzplatz.nummer;

    }

    @Override
    public int hashCode() {
        return nummer;
    }

    @Override
    public String toString() {
        return "Sitzplatz{" +
                "nummer=" + nummer +
                ", reihe=" + reihe +
                ", position=" + position +
                ", inFahrtrichtung=" + inFahrtrichtung +
                ", abteil=" + abteil +
                ", fenster=" + fenster +
                '}';
    }
}
