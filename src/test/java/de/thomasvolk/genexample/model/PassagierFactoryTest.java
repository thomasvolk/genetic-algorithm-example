/**
 * Copyright (C) 2015 Thomas Volk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
        doTest(factory, "/passagiere-test.csv");
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
