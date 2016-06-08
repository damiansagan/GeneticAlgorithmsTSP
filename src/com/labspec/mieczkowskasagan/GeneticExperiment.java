package com.labspec.mieczkowskasagan;


import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GeneticExperiment extends Experiment{

    private final GeneticParameters parameters;
    private List<Solution> solutions;

    public GeneticExperiment(Region region, GeneticParameters parameters) {
        super(region);
        this.parameters = parameters;
        new Thread(this,getName()).start();
    }

    @Override
    public void run() {
        System.out.println("GeneticExperiment of id: "+ getId() +" has started.");
        solutions = new ArrayList<>();
        Population population = new Population(region,parameters);
        while(!population.fulfillCriteria()){
            population.linearSelection();
            population.crossover();
            population.mutate();
            population.analyze();
            solutions.add(population.getBestSolution());
        }
        solution=solutions.get(solutions.size()-1);
        System.out.println("GeneticExperiment of id: "+ getId() +" is finished.");
        synchronized (this){
            notify();
        }
    }

    @Override
    public String getName() {
        return "genetic "+getId();
    }

    public Region getRegion() {
        return region;
    }

    public GeneticParameters getParameters() {
        return parameters;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public static List<Experiment> produce(Region region, GeneticParameters parameters, int n) {
        List<Experiment> experiments= new ArrayList<>();
        experiments.add(new GreedyExperiment(region));
        for(int i = 0; i<n; i++)
            experiments.add(new GeneticExperiment(region,parameters));
        return experiments;
    }

    public static XYSeriesCollection dispersionXYCollection (List<Experiment> experiments) throws InvocationTargetException, InterruptedException {
        XYSeriesCollection fitnessCollection = new XYSeriesCollection();
        XYSeries geneticSeries = new XYSeries("fluctuations of genetic");
        XYSeries greedySeries = new XYSeries("best greedy solution");
        java.util.List<Thread> seriesAdding = new ArrayList<>();
        experiments.forEach(experiment ->
                seriesAdding.add(new Thread(() -> {
                    if(experiment instanceof GeneticExperiment){
                        geneticSeries.add(experiment.getId(),experiment.getSolution().getFitness());
                        greedySeries.add(experiment.getId(),experiments.get(0).getSolution().getFitness());
                    }
                })));
        for(Thread t : seriesAdding) {
            t.start();
            t.join();
        }
        fitnessCollection.addSeries(geneticSeries);
        fitnessCollection.addSeries(greedySeries);
        return fitnessCollection;
    }

}
