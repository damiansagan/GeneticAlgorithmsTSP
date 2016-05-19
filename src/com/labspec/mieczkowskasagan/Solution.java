package com.labspec.mieczkowskasagan;


import java.util.*;

class Solution implements Comparable<Solution>{
    private static final Random generator = new Random();

    private Integer fitness;
    private List<Integer> series;
    private Region region;

    public Solution(Region region) {
        this.region = region;
        this.series = new ArrayList<>(region.listOfCities);
        Collections.shuffle(series);
    }


    public static List<Solution> makeOffspringFrom(Solution solution1, Solution solution2){
        //DOROTA
        return null;
    }


    private Integer computeFitness(){
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

    public void mutate(double coefficient){
        //there will be implemented mutation algorithm
        int numberOfMutations = (int)Math.ceil(coefficient*series.size());
        if(numberOfMutations<1 || series==null || series.size()<2) return; //makes no sense to go further
        int firstID = generator.nextInt(series.size());
        int lastID = firstID;
        int lastValue = series.get(lastID);
        for(int i = 0; i<numberOfMutations; i++){
            int currentID = generator.nextInt(series.size());
            while(currentID==lastID){ //can't be itself
                currentID = generator.nextInt(series.size());
            }
            int currentValue = series.get(currentID);
            series.set(currentID,lastValue);
            lastValue=currentValue;
            lastID=currentID;
        }
        series.set(firstID,lastValue);
        this.fitness = computeFitness();
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

    public Integer getFitness() {
        return fitness != null ? fitness : (fitness = computeFitness());
    }

    public static class Comparators {

        public static Comparator<Solution> FITNESS = new Comparator<Solution>() {
            @Override
            public int compare(Solution solution1, Solution solution2) {
                return solution1.getFitness().compareTo(solution2.getFitness());
            }
        };
    }

    @Override
    public int compareTo(Solution solution) {
        return Comparators.FITNESS.compare(this, solution);
    }

}
