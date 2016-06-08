package com.labspec.mieczkowskasagan;


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
}
