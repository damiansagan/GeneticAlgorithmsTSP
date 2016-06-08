package com.labspec.mieczkowskasagan;


abstract class Experiment implements Runnable{
    private static int counter = 0;
    private int id;
    final Region region;
    Solution solution;

    public Experiment(Region region) {
        this.id=counter++;
        this.region = region;
    }

    int getId() {
        return id;
    }
    public abstract String getName();
    synchronized public Solution getSolution(){
        if(solution==null)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        notify();
        return solution;
    }
}
