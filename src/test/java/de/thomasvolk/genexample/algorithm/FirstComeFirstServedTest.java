package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public class FirstComeFirstServedTest extends AbstractWagonTest {


    @Test
    public void nullWertungen() {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, Wertung.NULL);
        FirstComeFirstServed algorithmus = new FirstComeFirstServed(
                getSitzPlaetze().toArray(new Sitzplatz[getSitzPlaetze().size()]),
                passagiere.toArray(new Passagier[passagiere.size()]));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, algorithmus.getPassagierReihenfolge());
    }

    @Test
    public void einfacheWertungen() {
        List<Passagier> passagiere = getPassagiere(0, ANZAHL_SITZPLAETZE, new Wertung(Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG,Wertung.EINFACHE_GEWICHTUNG));
        FirstComeFirstServed algorithmus = new FirstComeFirstServed(
                getSitzPlaetze().toArray(new Sitzplatz[getSitzPlaetze().size()]),
                passagiere.toArray(new Passagier[passagiere.size()]));
        assertArrayEquals(new int[]{12, 14, 0, 2, 6, 8, 9, 11, 13, 1, 3, 5, 7, 10, 4}, algorithmus.getPassagierReihenfolge());
    }


    @Test
    public void unterschiedlicheWertungen() throws IOException {
        List<Passagier> passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        FirstComeFirstServed algorithmus = new FirstComeFirstServed(
                getSitzPlaetze().toArray(new Sitzplatz[getSitzPlaetze().size()]),
                passagiere.toArray(new Passagier[passagiere.size()]));
        int[] passagierReihenfolge = algorithmus.getPassagierReihenfolge();
        assertArrayEquals(new int[]{12, 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14}, passagierReihenfolge);
    }

}
