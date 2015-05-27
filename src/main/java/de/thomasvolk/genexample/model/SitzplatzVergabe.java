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

public class SitzplatzVergabe {
    private final Sitzplatz sitzplatz;
    private final Passagier passagier;

    public SitzplatzVergabe(Sitzplatz sitzplatz, Passagier passagier) {
        this.sitzplatz = sitzplatz;
        this.passagier = passagier;
    }

    public double getZufriedenheitFaktor() {
        if(getPassagier().getMaximaleZufriedenheit() == 0) {
            return -1;
        }
        else {
            return ((double) getZufriedenheit()) / ((double) getPassagier().getMaximaleZufriedenheit());
        }
    }

    public int getZufriedenheit() {
        int value = 0;
        if(sitzplatz.isAbteil()) {
            value += passagier.getWertung().getAbteil();
        }
        if(sitzplatz.isFenster()) {
            value += passagier.getWertung().getFensterPlatz();
        }
        if(sitzplatz.isInFahrtrichtung()) {
            value += passagier.getWertung().getFahrtRichtung();
        }
        return value;
    }

    public Sitzplatz getSitzplatz() {
        return sitzplatz;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SitzplatzVergabe that = (SitzplatzVergabe) o;

        return sitzplatz.equals(that.sitzplatz);

    }

    @Override
    public int hashCode() {
        return sitzplatz.hashCode();
    }

    @Override
    public String toString() {
        return "SitzplatzVergabe{" +
                "sitzplatz=" + sitzplatz.getNummer() +
                ", passagier=" + passagier.getId() +
                ", zufriedenheit=" + getZufriedenheit() +
                '}';
    }
}
