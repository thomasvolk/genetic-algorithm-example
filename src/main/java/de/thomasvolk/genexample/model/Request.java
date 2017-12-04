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

public class Request {
    public static final Request NULL = new Request(0, 0, 0);
    public static final int EINFACHE_GEWICHTUNG = 100;
    public static final int NULL_GEWICHTUNG = 0;
    private final int windowSeat;
    private final int cabin;
    private final int drivingDirection;

    public Request(int windowSeat, int cabin, int drivingDirection) {
        this.windowSeat = windowSeat;
        this.cabin = cabin;
        this.drivingDirection = drivingDirection;
    }

    public int getMaximaleHappiness() {
        return norm(getDrivingDirection()) + norm(getWindowSeat()) + norm(getCabin());
    }

    private int norm(int wert) {
        return wert < 0 ? 0 : wert;
    }

    public int getWindowSeat() {
        return windowSeat;
    }

    public int getCabin() {
        return cabin;
    }

    public int getDrivingDirection() {
        return drivingDirection;
    }

    public boolean keineWertung() {
        return  getCabin() == 0 && getDrivingDirection() == 0 && getWindowSeat() == 0;
    }

    @Override
    public String toString() {
        return "Wertug{" +
                "windowSeat=" + windowSeat +
                ", cabin=" + cabin +
                ", drivingDirection=" + drivingDirection +
                '}';
    }
}
