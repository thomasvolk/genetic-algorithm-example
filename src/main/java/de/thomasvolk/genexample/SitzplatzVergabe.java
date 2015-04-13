package de.thomasvolk.genexample;


class SitzplatzVergabe {
    private final Sitzplatz sitzplatz;
    private final Passagier passagier;

    SitzplatzVergabe(Sitzplatz sitzplatz, Passagier passagier) {
        this.sitzplatz = sitzplatz;
        this.passagier = passagier;
    }

    public double getZufriedenheit() {
        double value = 0.0;
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
}
