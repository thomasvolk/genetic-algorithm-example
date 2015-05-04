package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.*;
import de.thomasvolk.genexample.report.Report;

public abstract class AbstractGenerationAlgorithmus extends AbstractAlgorithmus {
    private int maxEvolutions = 1000;

    public AbstractGenerationAlgorithmus(Passagier[] passagierListe, Wagon wagon) {
        super(wagon, passagierListe);
    }


    public int getMaxEvolutions() {
        return maxEvolutions;
    }

    public void setMaxEvolutions(int maxEvolutions) {
        this.maxEvolutions = maxEvolutions;
    }


    abstract protected Generation getGeneration(int generation);

    @Override
    public WagonBelegung berechneWagon(Report report) {
        report.start(getWagonBelegung());
        int nummer = 0;
        Generation gen = null;
        for (; nummer < getMaxEvolutions(); nummer++) {
            gen = getGeneration(nummer);

            report.evolutionsSchritt(gen);
            if(gen.getZufriedenheit() == getWagonBelegung().getMaximaleZufriedenheit()) {
                break;
            }
        }
        report.ende(gen);
        return gen.getBesteWagonBelegung();
    }
}
