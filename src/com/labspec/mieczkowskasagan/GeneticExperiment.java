package com.labspec.mieczkowskasagan;


import java.util.ArrayList;
import java.util.List;

public class GeneticExperiment extends Experiment{
    private final Region region;
    private final GeneticParameters parameters;
    private List<Solution> solutions;

    public GeneticExperiment(Region region, GeneticParameters parameters) {
        this.region = region;
        this.parameters = parameters;
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
        System.out.println("GeneticExperiment of id: "+ getId() +" is finished.");
    }

    @Override
    public String getName() {
        return "genetic "+getId();
    }

    @Override
    public Solution getSolution(){
        return (solutions!=null && !solutions.isEmpty()) ? solutions.get(solutions.size()-1) : null;
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
