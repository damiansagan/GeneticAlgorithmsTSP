package com.labspec.mieczkowskasagan;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private int fitness;
    private List<Integer> series;

    public Solution(List<Integer> listOfCities) {
        this.series = new ArrayList<>(listOfCities);
        Collections.shuffle(series);
        this.fitness = getFitness();
    }

    public int getFitness(){
        //there will be fitness computing method
        return 0;
    }

    public static List<Solution> produce(int numberOfSolutions, Region region){
        List<Solution> result = new ArrayList<>(region.getNumberOfCities());
        for(int i = 0; i<numberOfSolutions; i++){
            result.add(new Solution(region.getListOfCities()));
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
