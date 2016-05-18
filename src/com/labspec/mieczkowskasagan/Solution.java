package com.labspec.mieczkowskasagan;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private int fitness;
    private List<Integer> series;
    private Region region;

    public Solution(Region region) {
        this.region = region;
        this.series = new ArrayList<>(region.getListOfCities());
        Collections.shuffle(series);
        this.fitness = getFitness();
    }

    //public Solution(int N)

    public int getFitness(){
        //there will be fitness computing method
        if(series == null || series.isEmpty()) throw new IllegalStateException();
//        final ListIterator<Integer> i = series.listIterator();
//        while (i.hasNext()) {
//            final String element = i.next();
//            i.set(element + "yaddayadda");
//        }

        return 0;
    }

    public void mutate(){
        //there will be implemented mutation algorithm
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
}
