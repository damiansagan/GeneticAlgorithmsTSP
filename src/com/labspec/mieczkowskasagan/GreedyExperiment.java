package com.labspec.mieczkowskasagan;


public class GreedyExperiment extends Experiment  {

    public GreedyExperiment(Region region) {
        super(region);
        new Thread(this,getName()).start();
    }

    @Override
    public String getName() {
        return "greedy "+getId();
    }

    @Override
    public void run() {
        System.out.println("GreedyExperiment of id: "+ getId() +" has started.");
        solution = new GreedySolution(region);
        System.out.println("GreedyExperiment of id: "+ getId() +" is finished.");
        synchronized (this){
            notify();
        }
    }
}
