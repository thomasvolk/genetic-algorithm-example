package de.thomasvolk.genexample.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AbstractWagonTest {
    protected static final int ANZAHL_SITZPLAETZE = 15;
    private static final int REIHEN = 5;
    private static final int BREITE = 3;
    private final Sitzplatz[] sitzPlaetze = new SitzplatzFactory().lese("GgGaA\nGgGaA\nGgGaA").toArray(new Sitzplatz[] {});
    //                                   Einfachgewichtung                   : 21223  10112  21223
    //                                          "          ohne Fahrtrichtung: 11122  00011  11122
    /*

    2 5 8 11 14
    1 4 7 10 13
    0 3 6  9 12

    GgGaA
    GgGaA
    GgGaA


     */

    public Wagon getWagon() {
        return new Wagon(sitzPlaetze, REIHEN, BREITE);
    }

    protected Passagier[] getPassagiere(String text) throws IOException {
        return getPassagiere(text, ANZAHL_SITZPLAETZE);
    }

    protected Passagier[] getPassagiere(String text, int len) throws IOException {
        return new PassagierFactory().lese(new ByteArrayInputStream(("Fensterplatz,\"in Fahrtrichtung\",Abteil\n" +
                text).getBytes()), len).toArray(new Passagier[] {});
    }

    protected Passagier[] getPassagiere(int von, int bis, Wertung w) {
        return IntStream.range(von, bis).mapToObj(i -> new Passagier(i, w)).collect(Collectors.toCollection(ArrayList::new)).toArray(new Passagier[] {});
    }
}
