package com.labspec.mieczkowskasagan;


abstract class Experiment implements Runnable{
    private static int counter = 0;
    private int id;

    public Experiment() {
        this.id=++counter;
    }

    public int getId() {
        return id;
    }

    public abstract String getName();
    public abstract Solution getSolution();
}
