package de.thomasvolk.genexample;


class SitzplatzVergabe {
    private final Sitzplatz sitzplatz;
    private final Passagier passagier;

    SitzplatzVergabe(Sitzplatz sitzplatz, Passagier passagier) {
        this.sitzplatz = sitzplatz;
        this.passagier = passagier;
    }

    public Sitzplatz getSitzplatz() {
        return sitzplatz;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    public double getZufriedenheit() {
        return 0.0;
    }
}
