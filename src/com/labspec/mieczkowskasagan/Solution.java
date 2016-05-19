package com.labspec.mieczkowskasagan;


import java.util.*;

public class Solution implements Comparable<Solution>{

    public static class Comparators {

        public static Comparator<Solution> FITNESS = new Comparator<Solution>() {
            @Override
            public int compare(Solution solution1, Solution solution2) {
                return solution1.fitness.compareTo(solution2.fitness);
            }
        };
    }

    @Override
    public int compareTo(Solution solution) {
        return Comparators.FITNESS.compare(this, solution);
    }

    private Integer fitness;
    private List<Integer> series;
    private Region region;

    public Solution(Region region) {
        this.region = region;
        this.series = new ArrayList<>(region.getListOfCities());
        Collections.shuffle(series);
        this.fitness = computeFitness();
    }


    public static List<Solution> makeOffspringFrom(Solution mom, Solution dad){
        //DOROTA

        List<Solution> offspring = new ArrayList<>(2);

        offspring.add(new Solution(mom.region));
        offspring.add(new Solution(mom.region));

        if(mom.series == null || mom.series.isEmpty()) throw new IllegalStateException();
        if(dad.series == null || dad.series.isEmpty()) throw new IllegalStateException();

        final ListIterator<Integer> momIterator = mom.series.listIterator();
        final ListIterator<Integer> dadIterator = dad.series.listIterator();

        final ListIterator<Integer> off1Iterator = offspring.get(0).series.listIterator();
        final ListIterator<Integer> off2Iterator = offspring.get(1).series.listIterator();

        Random generator = new Random();

        int off1prev = off1Iterator.next();
        int off2prev = off2Iterator.next();

        while(momIterator.hasNext() || dadIterator.hasNext()) {

            int momCurrent = momIterator.next();
            int dadCurrent = dadIterator.next();

            if (generator.nextBoolean()){

                for (int i=0; i<off1prev; i++){
                    if (offspring.get(0).series.get(i) == momCurrent) {
                            int off2Current = off2Iterator.next();
                            offspring.get(1).series.set(off2Current, momCurrent);
                            off2prev = off2Current;
                    }
                    else {
                        int off1Current = off1Iterator.next();
                        offspring.get(0).series.set(off1Current,momCurrent);
                        off1prev = off1Current;
                    }
                }

            }

            else {

                for (int i=0; i<off1prev; i++){
                    if (offspring.get(0).series.get(i) == dadCurrent){
                        offspring.get(1).series.set(off2prev, dadCurrent);
                        off2prev = off2Iterator.next();
                    }
                    else {
                        offspring.get(0).series.set(off1prev,dadCurrent);
                        off1prev = off1Iterator.next();
                    }
                }

            }
        }

        if (offspring.get(0).fitness == null || offspring.get(1).fitness == null) throw new IllegalStateException();

        return offspring;

        /*if(series == null || series.isEmpty()) throw new IllegalStateException();
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
        return sum;*/
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
