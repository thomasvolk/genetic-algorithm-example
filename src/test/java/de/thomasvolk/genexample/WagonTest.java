package de.thomasvolk.genexample;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WagonTest {
    public static final int ANZAHL_SITZPLAETZE = 15;
    private final List<Sitzplatz> sitzPlaetze = new SitzplatzFactory().lese("GgGaA\nGgGaA\nGgGaA");
    //                                 Einfachgewichtung                   : 21223  10112  21223
    //                                        "          ohne Fahrtrichtung: 11122  00011  11122
    private ArrayList<Passagier> getPassagiere(int von, int bis, Wertung w) {
        return IntStream.range(von, bis).mapToObj(i -> new Passagier(i, w)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    public void nullPassagiere() throws IOException {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Wagon wagon = new Wagon(sitzPlaetze, passagiere);
        assertEquals(0.0, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungPassagiere() throws IOException {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Wagon wagon = new Wagon(sitzPlaetze, passagiere);
        assertEquals(Wertung.EINFACHE_GEWICHTUNG * 25, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungOhneFahrtrichtungPassagiere() throws IOException {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.NULL_GEWICHTUNG));
        Wagon wagon = new Wagon(sitzPlaetze, passagiere);
        assertEquals(Wertung.EINFACHE_GEWICHTUNG * 16, wagon.getZufriedenheit(), 0);
    }
}
