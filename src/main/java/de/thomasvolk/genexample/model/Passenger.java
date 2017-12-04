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

public class Passenger {
    private final int id;
    private final Request request;

    public Passenger(int id, Request wertung) {
        this.id = id;
        this.request = wertung;
    }

    public int getId() {
        return id;
    }

    public Request getRequest() {
        return request;
    }

    public int getHappiness(Seat sitzplatz) {
        int value = 0;
        if(sitzplatz.isCabin()) {
            value += getRequest().getCabin();
        }
        if(sitzplatz.isFenster()) {
            value += getRequest().getWindowSeat();
        }
        if(sitzplatz.isInFahrtrichtung()) {
            value += getRequest().getDrivingDirection();
        }
        return value;
    }

    public int getMaximaleHappiness() {
        return getRequest().getMaximaleHappiness();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passagier = (Passenger) o;

        return id == passagier.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Passagier{" +
                "id=" + id +
                ", wuensche=" + request +
                '}';
    }
}
