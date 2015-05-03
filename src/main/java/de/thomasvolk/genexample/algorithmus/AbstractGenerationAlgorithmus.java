package de.thomasvolk.genexample.algorithmus;

import de.thomasvolk.genexample.model.Generation;
import de.thomasvolk.genexample.report.Report;
import de.thomasvolk.genexample.model.Passagier;
import de.thomasvolk.genexample.model.Sitzplatz;
import de.thomasvolk.genexample.model.WagonBesetzung;

public abstract class AbstractGenerationAlgorithmus extends AbstractAlgorithmus {
    private int maxEvolutions = 1000;

    public AbstractGenerationAlgorithmus(Passagier[] passagierListe, Sitzplatz[] sitzplatzListe) {
        super(sitzplatzListe, passagierListe);
    }


    public int getMaxEvolutions() {
        return maxEvolutions;
    }

    public void setMaxEvolutions(int maxEvolutions) {
        this.maxEvolutions = maxEvolutions;
    }


    abstract protected Generation getGeneration(int generation);

    @Override
    public WagonBesetzung berechneWagon(Report report) {
        report.start(getWagonBesetzung());
        int nummer = 0;
        Generation gen = null;
        for (; nummer < getMaxEvolutions(); nummer++) {
            gen = getGeneration(nummer);

            report.evolutionsSchritt(gen);
            if(gen.getZufriedenheit() == getWagonBesetzung().getMaximaleZufriedenheit()) {
                break;
            }
        }
        report.ende(gen);
        return gen.getBesterWagonBesetzung();
    }
}
