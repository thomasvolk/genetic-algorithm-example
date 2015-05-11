package de.thomasvolk.genexample.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PassagierFactory {
    List<Passagier> lese(InputStream is, int anzahl) throws IOException;
}
