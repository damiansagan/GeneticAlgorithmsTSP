package com.labspec.mieczkowskasagan;


class GreedyExperiment extends Experiment  {

    GreedyExperiment(Region region) {
        super(region);
        new Thread(this,getName()).start();
    }

    @Override
    public String getName() {
        return "greedy"+getId();
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
