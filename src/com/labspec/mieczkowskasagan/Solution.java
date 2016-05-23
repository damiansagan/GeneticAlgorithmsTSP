package com.labspec.mieczkowskasagan;


import java.util.*;

class Solution implements Comparable<Solution>{
    private static final Random generator = new Random();

    private Integer fitness;
    private List<Integer> series;
    private Region region;

    Solution(Region region) {
        this.region = region;
        this.series = new ArrayList<>(region.listOfCities);
        Collections.shuffle(series);
    }

    Solution(List<Integer> population, Region region){
        this.series = population;
        this.region = region;
    }



    public static List<Solution> makeOffspringFrom(Solution mom, Solution dad){
        //DOROTA

        int size = mom.region.getNumberOfCities();
        List<Solution> offspring = new ArrayList<>(2);
        List<Integer> firstChild = new ArrayList<>();
        List<Integer> secondChild = new ArrayList<>();

        for (int i=0; i<size; i++){
            firstChild.add(null);
            secondChild.add(null);
        }

        final ListIterator<Integer> momIterator = mom.series.listIterator();
        final ListIterator<Integer> dadIterator = dad.series.listIterator();

        int momCurrent;
        int dadCurrent;
        int firstCurrent=0;
        int secondCurrent=0;

        while(momIterator.hasNext() || dadIterator.hasNext()) {
            if (mom.generator.nextBoolean() && dadIterator.hasNext()){
                dadCurrent = dadIterator.next();
                if (firstChild.contains(dadCurrent)){
                    secondChild.set(secondCurrent,dadCurrent);
                    secondCurrent++;
                    }
                    else {
                    firstChild.set(firstCurrent,dadCurrent);
                    firstCurrent++;
                    }
            }
            else if (!mom.generator.nextBoolean() && momIterator.hasNext()){
                momCurrent = momIterator.next();
                if (firstChild.contains(momCurrent)){
                    secondChild.set(secondCurrent,momCurrent);
                    secondCurrent++;
                }
                else {
                    firstChild.set(firstCurrent,momCurrent);
                    firstCurrent++;
                }
            }
        }
        offspring.add(new Solution(firstChild,mom.region));
        offspring.add(new Solution(secondChild,mom.region));

        return offspring;
    }


    private Integer computeFitness(){
        //there will be fitness computing method
        if(series == null || series.isEmpty()) throw new IllegalStateException();
        final ListIterator<Integer> iterator = series.listIterator();
        int first = iterator.next();
        int prev = first;
        int sum = 0;
        int current;
        while (iterator.hasNext()) {
            current = iterator.next();
            sum+=region.getDistanceBetween(prev,current);
            prev=current;
        }
        sum+=region.getDistanceBetween(prev,first);
        return sum;
    }

    void mutate(double coefficient){
        //there will be implemented mutation algorithm
        int numberOfMutations = (int)Math.ceil(coefficient*series.size())%series.size();
        if(numberOfMutations<1 || series==null || series.size()<2) return; //makes no sense to go further
        int firstID = generator.nextInt(series.size());
        int lastID = firstID;
        int lastValue = series.get(lastID);
        int currentID = generator.nextInt(series.size());
        for(int i = 0; i<numberOfMutations; i++){
            while(currentID==lastID || currentID==firstID){ //can't be itself
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

    static List<Solution> produce(int numberOfSolutions, Region region){
        List<Solution> result = new ArrayList<>(numberOfSolutions);
        for(int i = 0; i<numberOfSolutions; i++){
            result.add(new Solution(region));
        }
        return result;
    }

    void testForDuplicates(){
        Set<Integer> set = new HashSet<>(series);
        if(set.size() < series.size()){
            System.out.println(this);
            throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        return "Solution{" +
                "fitness=" + fitness +
                ", series=" + series +
                '}';
    }

    Integer getFitness() {
        return fitness != null ? fitness : (fitness = computeFitness());
    }

    private static class Comparators {

        static Comparator<Solution> FITNESS = new Comparator<Solution>() {
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
