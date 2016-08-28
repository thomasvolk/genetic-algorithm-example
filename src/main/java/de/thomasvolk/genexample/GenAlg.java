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
package de.thomasvolk.genexample;

import de.thomasvolk.genexample.algorithm.*;
import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.report.HtmlReport;

import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GenAlg {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Genetische Algorithmen");

        Options options = new Options();
        options.addOption(option("s", "Jede s Generation wird im Bericht ausgegeben"));
        options.addOption(option("w", "Quelldatei Wagon"));
        options.addOption(option("l", "Quelldatei Passagierliste"));
        options.addOption(option("d", "Zielverzeichnis Bericht"));
        options.addOption(option("a", "Algorithmus Typ " + Arrays.asList(AlgorithmTyp.values()).toString()));
        options.addOption(option("g", "Anzahl der Generationen"));
        options.addOption(option("p", "Anzahl der Populationen"));
        options.addOption("h", false, "Hilfe");
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                printUsage(options);
                System.exit(0);
            }

            int generationen = getNummer(options, cmd.getOptionValue('g'), 2000);
            int populationen = getNummer(options, cmd.getOptionValue('p'), 20);
            int schritte = getNummer(options, cmd.getOptionValue('s'), 100);
            AlgorithmTyp[] alg = AlgorithmTyp.values();
            if (cmd.hasOption('a')) {
                try {
                    alg = new AlgorithmTyp[]{AlgorithmTyp.valueOf(cmd.getOptionValue('a'))};
                } catch (IllegalArgumentException e) {
                    printErrorAndExit(e, options);
                }
            }
            String reportDir = cmd.getOptionValue('d');
            reportDir = StringUtils.isBlank(reportDir) ? "report" : reportDir;
            String wagonDatei = cmd.getOptionValue('w');
            String passagierDatei = cmd.getOptionValue('l');
            if (wagonDatei == null) {
                wagonDatei = erzeugeBeispielDatei("wagon.txt");
            }
            if (passagierDatei == null) {
                passagierDatei = erzeugeBeispielDatei("passengers.csv");
            }
            System.out.println("Wagon Datein: " + wagonDatei);
            System.out.println("Passagier Datei: " + passagierDatei);
            System.out.println("Bericht: " + new File(reportDir).getAbsolutePath());
            System.out.println("Anzahl Generationen: " + generationen);
            System.out.println("Anzahl Populationen: " + populationen);
            System.out.printf("Protokolliere jede %dte Generation im Bericht\n", schritte);

            WagonFactory wagonFactory = new WagonFactory();
            PassagierFactory passagierFactory = new CSVPassengerFactory();

            GenAlg genAlg = new GenAlg(wagonFactory, passagierFactory);
            genAlg.run(alg, passagierDatei, wagonDatei, reportDir, schritte, generationen, populationen);
        } catch (ParseException e) {
            printErrorAndExit(e, options);
        }
    }

    private static int getNummer(Options options, String value, int defaultValue) {
        int wert = defaultValue;
        if (value != null) {
            try {
                wert = Integer.valueOf(value);
            } catch (NumberFormatException e) {
                printErrorAndExit(e, options);
            }
        }
        return wert;
    }

    private static void printErrorAndExit(Exception e, Options options) {
        System.err.printf("Fehler: %s - %s\n", e.getClass().getSimpleName(), e.getMessage());
        printUsage(options);
        System.exit(1);
    }

    private static void printUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar genetic.jar", options);
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

    public void run(AlgorithmTyp[] algorithmen, String passagierDatei, String wagonDatei,
                    String reportDir, int schritte,
                    int generationen, int populationen) throws IOException {
        try (InputStream wagonSrc = new FileInputStream(wagonDatei);
             InputStream passagierQuelle = new FileInputStream(passagierDatei)) {
            Wagon wagon = wagonFactory.lese(wagonSrc);
            List<Passenger> passagierListe = passagierFactory.lese(passagierQuelle, wagon.getSitzplatzListe().length);
            WagonAllocation wagonBelegung = new WagonAllocation(wagon,
                    passagierListe.toArray(new Passenger[passagierListe.size()]));
            HtmlReport bericht = new HtmlReport(reportDir, schritte, wagonBelegung);
            for (AlgorithmTyp algorithmusTyp : algorithmen) {
                System.out.println("Algorithmus: " + algorithmusTyp);
                berechnen(algorithmusTyp, bericht, wagonBelegung,
                        generationen, populationen);
            }
            bericht.generiere();
        }
    }

    private void berechnen(AlgorithmTyp algTyp, HtmlReport bericht,
                           WagonAllocation wagonBelegung,
                           int generationen, int populationen) throws IOException {
        AlgorithmFactory algorithmusFactory = new AlgorithmFactory();
        Algorithm algorithmus = algorithmusFactory.erzeugeAlgorithmus(algTyp, wagonBelegung);
        if (algorithmus instanceof AbstractGenerationAlgorithm) {
            ((AbstractGenerationAlgorithm) algorithmus).setMaxEvolutions(generationen);
        }
        if (algorithmus instanceof GeneticAlgorithm) {
            ((GeneticAlgorithm) algorithmus).setPopulationSize(populationen);
        }
        algorithmus.berechneWagon(bericht.newAlgorithmusBericht(algTyp));
    }

    private static String erzeugeBeispielDatei(String beispielDatei) throws IOException {
        if(!new File(beispielDatei).exists()) {
            IOUtils.copy(GenAlg.class.getClassLoader().getResourceAsStream(beispielDatei), new FileOutputStream(beispielDatei));
        }
        return beispielDatei;
    }

}
