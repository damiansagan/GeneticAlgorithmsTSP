package com.labspec.mieczkowskasagan;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Solution {
    //comparator albo comparable tak zeby bylo sortowanie

    private int fitness;
    private List<Integer> series;
    private Region region;

    public Solution(Region region) {
        this.region = region;
        this.series = new ArrayList<>(region.getListOfCities());
        Collections.shuffle(series);
        this.fitness = computeFitness();
    }

    public static List<Solution> makeOffspringFrom(Solution solution1, Solution solution2){
        //DOROTA
        return null;
    }


    public int computeFitness(){
        //there will be fitness computing method
        if(series == null || series.isEmpty()) throw new IllegalStateException();
        final ListIterator<Integer> iterator = series.listIterator();
        int first = iterator.next();
        int prev = first;
        int sum = 0;
        while (iterator.hasNext()) {
            int current = iterator.next();
            sum+=region.getDistanceBetween(prev,current);
            prev=current;
        }
        sum+=region.getDistanceBetween(prev,first);
        return sum;
    }

    public void mutate(int numberOfMutations){
        //there will be implemented mutation algorithm
        //DAMIAN
    }

    public static List<Solution> produce(int numberOfSolutions, Region region){
        List<Solution> result = new ArrayList<>(numberOfSolutions);
        for(int i = 0; i<numberOfSolutions; i++){
            result.add(new Solution(region));
        }
        return result;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "fitness=" + fitness +
                ", series=" + series +
                '}';
    }

    public int getFitness() {
        return fitness;
    }
}
