package de.thomasvolk.genexample.algorithm;


import de.thomasvolk.genexample.*;
import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.FirstComeFirstServed;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FirstComeFirstServedTest extends AbstractAlgorithmusTest {


    protected Algorithmus getAlgorithmus(List<Passagier> passagiere) {
        return new FirstComeFirstServed(
                getSitzPlaetze().toArray(new Sitzplatz[getSitzPlaetze().size()]),
                passagiere.toArray(new Passagier[passagiere.size()]));
    }

    @Test
    public void unterschiedlicheWertungen() throws IOException {
        List<Passagier> passagiere = getPassagiere("fp,fr,ap\nfp,fr,\nfp,,");
        Algorithmus algorithmus = getAlgorithmus(passagiere);
        int[] passagierReihenfolge = algorithmus.getPassagierReihenfolge();
        assertEquals(400, new Wagon(getSitzPlaetze(), passagiere).getZufriedenheit(), 0);
        assertArrayEquals(new int[]{12, 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14}, passagierReihenfolge);
        assertEquals(600, new Wagon(getSitzPlaetze(), passagiere, algorithmus.getPassagierReihenfolge()).getZufriedenheit(), 0);
    }



}
