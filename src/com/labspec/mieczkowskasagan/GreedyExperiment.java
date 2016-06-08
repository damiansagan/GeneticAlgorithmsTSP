package com.labspec.mieczkowskasagan;


public class GreedyExperiment {

    private Solution solution;

    public GreedyExperiment(Region region) {
        solution = new GreedySolution(region);
    }

    public Solution getSolution(){
        return solution;
    }

    public Double getFitness(){
        return solution.getFitness();
    }
}
