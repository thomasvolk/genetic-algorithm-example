package de.thomasvolk.genexample;

import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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

    @Test
    public void sortierung() {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        Wagon wagon = new Wagon(getSitzPlaetze(), passagiere, new int[]{14, 0, 1, 2, 3, 4, 5, 12, 13, 8, 9, 10, 11, 6, 7});
        List<SitzplatzVergabe> sitzplatzVergabeListe = wagon.getSitzplatzVergabeListe();
        SitzplatzVergabe sitzplatzVergabe = sitzplatzVergabeListe.get(0);
        assertEquals(0, sitzplatzVergabe.getPassagier().getId());
        assertEquals(15, sitzplatzVergabe.getSitzplatz().getNummer());
        sitzplatzVergabe = sitzplatzVergabeListe.get(14);
        assertEquals(14, sitzplatzVergabe.getPassagier().getId());
        assertEquals(8, sitzplatzVergabe.getSitzplatz().getNummer());
        sitzplatzVergabe = sitzplatzVergabeListe.get(5);
        assertEquals(5, sitzplatzVergabe.getPassagier().getId());
        assertEquals(5, sitzplatzVergabe.getSitzplatz().getNummer());
    }

}
