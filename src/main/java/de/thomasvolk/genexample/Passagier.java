package de.thomasvolk.genexample;

public class Passagier {
    static class Wertung {
        private final int fensterPlatz;
        private final int abteil;
        private final int fahrtRichtung;

        Wertung(int fensterPlatz, int abteil, int fahrtRichtung) {
            this.fensterPlatz = fensterPlatz;
            this.abteil = abteil;
            this.fahrtRichtung = fahrtRichtung;
        }
        public int getFensterPlatz() {
            return fensterPlatz;
        }

        public int getAbteil() {
            return abteil;
        }

        public int getFahrtRichtung() {
            return fahrtRichtung;
        }

        @Override
        public String toString() {
            return "Wuensche{" +
                    "fensterPlatz=" + fensterPlatz +
                    ", abteil=" + abteil +
                    ", fahrtRichtung=" + fahrtRichtung +
                    '}';
        }
    }
    private final int id;
    private final Wertung wertung;

    public Passagier(int id) {
        this(id, new Wertung(0,0,0));
    }

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
