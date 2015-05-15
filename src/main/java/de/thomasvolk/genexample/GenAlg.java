package de.thomasvolk.genexample;

import de.thomasvolk.genexample.algorithmus.Algorithmus;
import de.thomasvolk.genexample.algorithmus.AlgorithmusFactory;
import de.thomasvolk.genexample.algorithmus.AlgorithmusTyp;
import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.report.HtmlReport;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

public class GenAlg {

    public static void main( String[] args ) throws IOException, ParseException {
        System.out.println("Genetische Algorithmen");

        Options options = new Options();
        options.addOption(option("s", "Jede s Generation wird im Bericht ausgegeben"));
        options.addOption(option("w", "Quelldatei Wagon"));
        options.addOption(option("p", "Quelldatei Passagierliste"));
        options.addOption(option("d", "Zielverzeichnis Bericht"));
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse( options, args);
        int schritte = 100;
        if(cmd.hasOption('s')) {
            schritte = Integer.valueOf(cmd.getOptionValue('s'));
        }
        AlgorithmusTyp[] alg = AlgorithmusTyp.values();
        if(cmd.hasOption('a')) {
            alg = new AlgorithmusTyp[] { AlgorithmusTyp.valueOf(cmd.getOptionValue('a')) };
        }
        String reportDir = cmd.getOptionValue('d');
        reportDir = StringUtils.isBlank(reportDir) ? "report" : reportDir;
        String wagonDatei = cmd.getOptionValue('w');
        if(wagonDatei == null) {
            wagonDatei = erzeugeBeispielDatei("wagon.txt");
        }
        String passagierDatei = cmd.getOptionValue('p');
        if(passagierDatei == null) {
            passagierDatei = erzeugeBeispielDatei("passagiere.csv");
        }
        WagonFactory wagonFactory = new WagonFactory();
        PassagierFactory passagierFactory = new CSVPassagierFactory();
        GenAlg genAlg = new GenAlg(wagonFactory, passagierFactory);
        for(AlgorithmusTyp algorithmusTyp: alg) {
            genAlg.berechnen(algorithmusTyp, passagierDatei, wagonDatei, reportDir, schritte);
        }
    }

    private static Option option(String name, String descr) {
        Option opt = new Option(name, true, descr);
        opt.setRequired(false);
        return opt;
    }

    private final WagonFactory wagonFactory;
    private final PassagierFactory passagierFactory;

    public GenAlg(WagonFactory wagonFactory, PassagierFactory passagierFactory) {
        this.wagonFactory = wagonFactory;
        this.passagierFactory = passagierFactory;
    }

    public void berechnen(AlgorithmusTyp algTyp, String passagierDatei, String wagonDatei, String reportDir, int steps) throws IOException {
        try(InputStream wagonSrc = new FileInputStream(wagonDatei);
            InputStream passagierQuelle = new FileInputStream(passagierDatei)) {
            Wagon wagon = wagonFactory.lese(wagonSrc);
            List<Passagier> passagierListe = passagierFactory.lese(passagierQuelle, wagon.getSitzplatzListe().length);
            AlgorithmusFactory algorithmusFactory = new AlgorithmusFactory();
            Algorithmus algorithmus = algorithmusFactory.erzeugeAlgorithmus(algTyp, passagierListe.toArray(new Passagier[passagierListe.size()]), wagon);
            algorithmus.berechneWagon(new HtmlReport(reportDir + "/" + algTyp.name(), steps));
        }
    }

    private static String erzeugeBeispielDatei(String beispielDatei) throws IOException {
        IOUtils.copy(GenAlg.class.getResourceAsStream("/" + beispielDatei), new FileOutputStream(beispielDatei));
        return beispielDatei;
    }

}
