package de.thomasvolk.genexample.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

public class PassagierFactoryTest {

    @Test
    public void leseCsv() throws IOException {
        CSVPassagierFactory factory = new CSVPassagierFactory();
        Collection<Passagier> pasagiere = factory.lese(getClass().getResourceAsStream("/passagiere.csv"), 10);
        assertEquals(10, pasagiere.size());
        pasagiere = factory.lese(getClass().getResourceAsStream("/passagiere.csv"), 100);
        assertEquals(100, pasagiere.size());

    }

    @Test
    public void leseExcel() throws IOException {
        ExcelPassagierFactory factory = new ExcelPassagierFactory();
        Collection<Passagier> pasagiere = factory.lese(getClass().getResourceAsStream("/passagiere.xlsx"), 10);
        assertEquals(10, pasagiere.size());
        pasagiere = factory.lese(getClass().getResourceAsStream("/passagiere.xlsx"), 100);
        assertEquals(100, pasagiere.size());

    }

}
