package de.thomasvolk.genexample.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class PassagierFactoryTest {

    @Test
    public void leseCsv() throws IOException {
        CSVPassagierFactory factory = new CSVPassagierFactory();
        doTest(factory, "/passagiere.csv");
    }

    @Test
    public void leseExcel() throws IOException {
        ExcelPassagierFactory factory = new ExcelPassagierFactory();
        doTest(factory, "/passagiere.xlsx");
    }

    private void doTest(PassagierFactory factory, String src) throws IOException {
        Collection<Passagier> pasagiere = factory.lese(getClass().getResourceAsStream(src), 10);
        assertEquals(10, pasagiere.size());
        Iterator<Passagier> iterator = pasagiere.iterator();
        Passagier passagier = iterator.next();
        assertEquals(100, passagier.getWertung().getFahrtRichtung());
        pasagiere = factory.lese(getClass().getResourceAsStream(src), 100);
        assertEquals(100, pasagiere.size());
    }

}
