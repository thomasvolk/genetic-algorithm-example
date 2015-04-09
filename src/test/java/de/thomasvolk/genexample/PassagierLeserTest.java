package de.thomasvolk.genexample;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

public class PassagierLeserTest {

    @Test
    public void lesen() throws IOException {
        PassagierLeser leser = new PassagierLeser();
        Collection<Passagier> pasagiere = leser.lese(getClass().getResourceAsStream("/passagiere.csv"));
        assertEquals(40, pasagiere.size());
    }
}
