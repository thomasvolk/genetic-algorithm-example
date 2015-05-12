package de.thomasvolk.genexample;

import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.AlgorithmusFactory;
import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.report.HtmlReport;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws IOException {
        System.out.println("Genetische Algorithmen");
        WagonFactory wagonFactory = new WagonFactory();
        PassagierFactory passagierFactory = new CSVPassagierFactory();
        Main main = new Main(wagonFactory, passagierFactory);
        // TODO: werte mit cli auslesen
        main.berechnen(AlgorithmusTyp.GENETISCH, null, null, null, 100);
    }
    private final WagonFactory wagonFactory;
    private final PassagierFactory passagierFactory;

    public Main(WagonFactory wagonFactory, PassagierFactory passagierFactory) {
        this.wagonFactory = wagonFactory;
        this.passagierFactory = passagierFactory;
    }

    public void berechnen(AlgorithmusTyp algTyp, String passagierDatei, String wagonDatei, String inReportDir, int steps) throws IOException {
        String reportDir = StringUtils.isBlank(inReportDir) ? "target/report" : inReportDir;
        try(InputStream wagonSrc = getSource(wagonDatei, "/wagon.txt");
            InputStream passagierQuelle = getSource(passagierDatei, "/passagiere.csv")) {
            Wagon wagon = wagonFactory.lese(wagonSrc);
            List<Passagier> passagierListe = passagierFactory.lese(passagierQuelle, wagon.getSitzplatzListe().length);
            AlgorithmusFactory algorithmusFactory = new AlgorithmusFactory();
            Algorithmus algorithmus = algorithmusFactory.erzeugeAlgorithmus(algTyp, passagierListe.toArray(new Passagier[passagierListe.size()]), wagon);
            algorithmus.berechneWagon(new HtmlReport(reportDir, steps));
        }
    }

    private InputStream getSource(String datei, String internalDefaultSrc) throws FileNotFoundException {
        InputStream is;
        if(StringUtils.isEmpty(datei)) {
            is = getClass().getResourceAsStream(internalDefaultSrc);
        }
        else {
            is = new FileInputStream(datei);
        }
        return is;
    }

}
