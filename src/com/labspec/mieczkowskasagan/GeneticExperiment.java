package com.labspec.mieczkowskasagan;


import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.List;

class GeneticExperiment extends Experiment{

    private final GeneticParameters parameters;
    private List<Solution> solutions;

    GeneticExperiment(Region region, GeneticParameters parameters) {
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
        return "genetic"+getId();
    }

    public Region getRegion() {
        return region;
    }

    public GeneticParameters getParameters() {
        return parameters;
    }

    synchronized List<Solution> getSolutions() {
        if(solutions.size()<parameters.getGenerationsRequired()-1)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        notify();
        return solutions;
    }

    static List<Experiment> produce(Region region, GeneticParameters parameters, int n) {
        List<Experiment> experiments= new ArrayList<>();
        experiments.add(new GreedyExperiment(region));
        for(int i = 0; i<n; i++)
            experiments.add(new GeneticExperiment(region,parameters));
        return experiments;
    }

    static XYSeriesCollection dispersionXYCollection (List<Experiment> experiments) throws InterruptedException {
        XYSeriesCollection dispersionXYCollection = new XYSeriesCollection();
        XYSeries geneticSeries = new XYSeries("fluctuations of best genetic");
        XYSeries greedySeries = new XYSeries("best greedy solution");
        List<Thread> seriesAdding = new ArrayList<>();
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
        dispersionXYCollection.addSeries(geneticSeries);
        dispersionXYCollection.addSeries(greedySeries);
        return dispersionXYCollection;
    }

    private static XYSeries generateGreedyXYSeries(GreedyExperiment experiment,int generations){
        XYSeries series = new XYSeries(experiment.getName());
        double fitness = experiment.getSolution().getFitness();
        series.add(1,fitness);
        series.add(generations,fitness);
        return series;
    }

    private static XYSeries generateGeneticXYSeries(GeneticExperiment experiment){
        XYSeries series = new XYSeries(experiment.getName());
        int i = 1;
        List<Solution> solutions = experiment.getSolutions();
        for(Solution s : solutions) {
            series.add(i,s.getFitness());
            i++;
        }
        return series;
    }

    static XYSeriesCollection generationXYCollection (List<Experiment> experiments, GeneticParameters parameters) throws InterruptedException {
        XYSeriesCollection generationXYCollection = new XYSeriesCollection();
        List<Thread> seriesAdding = new ArrayList<>();
        experiments.forEach(experiment ->
                seriesAdding.add(new Thread(() -> {
                        if(experiment instanceof GeneticExperiment)
                            generationXYCollection.addSeries(generateGeneticXYSeries((GeneticExperiment) experiment));
                        else
                            generationXYCollection.addSeries(generateGreedyXYSeries((GreedyExperiment) experiment,parameters.getGenerationsRequired()));
                })));
        for(Thread t : seriesAdding) {
            t.start();
            t.join();
        }
        return generationXYCollection;
    }

}
