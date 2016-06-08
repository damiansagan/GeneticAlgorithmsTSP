package com.labspec.mieczkowskasagan;


public class GreedyExperiment extends Experiment  {

    private Region region;
    private Solution solution;

    public GreedyExperiment(Region region) {
        this.region=region;
    }

    @Override
    public String getName() {
        return "greedy "+getId();
    }

    @Override
    public Solution getSolution(){
        return solution;
    }

    @Override
    public void run() {
        System.out.println("GreedyExperiment of id: "+ getId() +" has started.");
        solution = new GreedySolution(region);
        System.out.println("GreedyExperiment of id: "+ getId() +" is finished.");

    }
}
