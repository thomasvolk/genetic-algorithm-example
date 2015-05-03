package de.thomasvolk.genexample.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class WagonBesetzungTest extends AbstractWagonTest {

    @Test
    public void nullPassagiere() throws IOException {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        WagonBesetzung wagonBesetzung = new WagonBesetzung(getSitzPlaetze(), passagiere);
        assertEquals(0.0, wagonBesetzung.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungPassagiere() throws IOException {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        WagonBesetzung wagonBesetzung = new WagonBesetzung(getSitzPlaetze(), passagiere);
        assertEquals(Wertung.EINFACHE_GEWICHTUNG * 25, wagonBesetzung.getZufriedenheit(), 0);
    }

    @Test
    public void einfachWertungOhneFahrtrichtungPassagiere() throws IOException {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE,
                new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.NULL_GEWICHTUNG));
        WagonBesetzung wagonBesetzung = new WagonBesetzung(getSitzPlaetze(), passagiere);
        assertEquals(Wertung.EINFACHE_GEWICHTUNG * 16, wagonBesetzung.getZufriedenheit(), 0);
    }

    @Test(expected = ArrayStoreException.class)
    public void dubeltten() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        new WagonBesetzung(getSitzPlaetze(), passagiere, new int[] {0,1,2,3,4,5,12,7,8,9,10,11,12,13,14});
    }

    @Test
    public void sortierung() {
        Passagier[] passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        WagonBesetzung wagonBesetzung = new WagonBesetzung(getSitzPlaetze(), passagiere, new int[]{14, 0, 1, 2, 3, 4, 5, 12, 13, 8, 9, 10, 11, 6, 7});
        List<SitzplatzVergabe> sitzplatzVergabeListe = wagonBesetzung.getSitzplatzVergabeListe();
        SitzplatzVergabe sitzplatzVergabe = sitzplatzVergabeListe.get(0);
        assertEquals(1, sitzplatzVergabe.getPassagier().getId());
        assertEquals(1, sitzplatzVergabe.getSitzplatz().getNummer());
        sitzplatzVergabe = sitzplatzVergabeListe.get(14);
        assertEquals(0, sitzplatzVergabe.getPassagier().getId());
        assertEquals(15, sitzplatzVergabe.getSitzplatz().getNummer());
        sitzplatzVergabe = sitzplatzVergabeListe.get(5);
        assertEquals(6, sitzplatzVergabe.getPassagier().getId());
        assertEquals(6, sitzplatzVergabe.getSitzplatz().getNummer());
    }

}
