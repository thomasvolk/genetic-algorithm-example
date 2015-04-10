package de.thomasvolk.genexample;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

public class PassagierFactoryTest {

    @Test
    public void lesen() throws IOException {
        PassagierFactory factory = new PassagierFactory();
        Collection<Passagier> pasagiere = factory.lese(getClass().getResourceAsStream("/passagiere.csv"));
        assertEquals(98, pasagiere.size());
    }
}
