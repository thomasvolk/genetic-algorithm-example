package de.thomasvolk.genexample;

import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WagonTest extends AbstractWagonTest {

    @Test
    public void nullPassagiere() throws IOException {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(0.0, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungPassagiere() throws IOException {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(Wertung.EINFACHE_GEWICHTUNG * 25, wagon.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungOhneFahrtrichtungPassagiere() throws IOException {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.NULL_GEWICHTUNG));
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere);
        assertEquals(Wertung.EINFACHE_GEWICHTUNG * 16, wagon.getZufriedenheit(), 0);
    }

    @Test(expected = ArrayStoreException.class)
    public void dubeltten() {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        new Wagon(getSitzPlaetze(), passagiere, new int[] {0,1,2,3,4,5,12,7,8,9,10,11,12,13,14});
    }

}
