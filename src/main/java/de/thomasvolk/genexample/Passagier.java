package de.thomasvolk.genexample;

public class Passagier {
    private final int id;
    private final Wertung wertung;

    public Passagier(int id, Wertung wertung) {
        this.id = id;
        this.wertung = wertung;
    }

    public int getId() {
        return id;
    }

    public Wertung getWertung() {
        return wertung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passagier passagier = (Passagier) o;

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
                ", wuensche=" + wertung +
                '}';
    }
}
