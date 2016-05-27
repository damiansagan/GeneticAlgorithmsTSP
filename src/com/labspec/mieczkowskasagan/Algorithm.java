package com.labspec.mieczkowskasagan;

import java.util.*;

class Algorithm {
    private static final Random generator = new Random();

    //variables
    private final Region region;
    private List<Solution> solutionList;//population
    private List<Solution> childrenList;
    private boolean isSorted = false;

    private int currentNumberOfGeneration = 0;
    private int currentMinimalFitness = Integer.MAX_VALUE;
    private Solution currentBestSolution = null;

    //parameters
    private final int generationsRequired;
    private final int maximalAcceptableFitness;
    private final double coefficientOfMutantsEachGeneration;
    private final double coefficientOfMutatedGenesInChromosomes;
    private final double coefficientOfLinearSelection;


    Algorithm(int numberOfChromosomes, int initialPopulation, int generationsRequired, int maximalAcceptableFitness,
              double coefficientOfMutantsEachGeneration, double coefficientOfMutatedGenesInChromosomes, double coefficientOfLinearSelection) {
        this.generationsRequired = generationsRequired;
        this.maximalAcceptableFitness = maximalAcceptableFitness;
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
        this.coefficientOfLinearSelection=coefficientOfLinearSelection;
        region = new RandomDistancesRegion(numberOfChromosomes);
        solutionList = Solution.produce(initialPopulation,region);
    }

    void naturalSelection() {
        if(solutionList==null || solutionList.size()<2) return;
        boolean linearSelection = probabilityTest(coefficientOfLinearSelection);
        if(linearSelection) { //linear selection algorithm
            if(!isSorted) {
                Collections.sort(solutionList);
                isSorted=true;
            }
            double Sn=(1+solutionList.size())*0.5*solutionList.size();
            ListIterator<Solution> iterator = solutionList.listIterator();
            int index=1;
            //StringBuilder stringBuilder = new StringBuilder();
            //System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
            while(iterator.hasNext()){
                iterator.next();
                //stringBuilder.append((double)index/Sn).append(" ");
                if(probabilityTest((double)index/Sn))
                    iterator.remove();
                index++;
            }
            //System.out.println(stringBuilder.toString());
        }
        else{ //roulette selection algorithm
            int sum = 0;
            for(Solution solution : solutionList)
                sum += solution.getFitness();
            ListIterator<Solution> iterator = solutionList.listIterator();
            //StringBuilder stringBuilder = new StringBuilder();
            //System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
            while(iterator.hasNext()){
                Solution solution = iterator.next();
                //stringBuilder.append((double)(solution.getFitness())/sum*solutionList.size()).append(" ");
                if(!probabilityTest((double)(solution.getFitness())/sum*solutionList.size()))
                    iterator.remove();
            }
            //System.out.println(stringBuilder.toString());
        }
    }

    void crossover() {
        List<Solution> temp = new ArrayList<>();
        for(Solution first : solutionList){

                Solution second = solutionList.get(generator.nextInt(solutionList.size()));
                temp.addAll(Solution.makeOffspringFrom(first, second));

        }
        solutionList = temp;
    }

    void mutate() {
        for(Solution solution : solutionList)
            if(probabilityTest(coefficientOfMutantsEachGeneration))
                solution.mutate(coefficientOfMutatedGenesInChromosomes);
        isSorted=false;
    }

    void analyzePopulation() {
        //it does NOT need the population to be sorted
        if(solutionList==null || solutionList.isEmpty()) return;
        if(!isSorted)
            currentBestSolution=Collections.min(solutionList);
        else
            currentBestSolution=solutionList.get(0);
        currentMinimalFitness=currentBestSolution.getFitness();
    }

    boolean isFinished(){
        currentNumberOfGeneration++;
        System.out.println("Generation number: " + currentNumberOfGeneration);
        return currentNumberOfGeneration >= generationsRequired ||
                currentMinimalFitness <= maximalAcceptableFitness || solutionList.size()<2;
    }

    private boolean probabilityTest(double probability){
        return generator.nextDouble() <= probability;
    }

    public void testPrint(){
//        for(Integer i : region.getListOfCities())
//            System.out.println("From: " + i +" to: "+ region.getNearestCityFrom(i) +
//                    " minimum is: " + region.getDistanceBetween(i,region.getNearestCityFrom(i)));
//        System.out.println(region);
//        crossoverTest();
//        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
    }

    private void crossoverTest(){
        Solution s1 = new Solution(region);
        Solution s2 = new Solution(region);
        System.out.println(s1);
        System.out.println(s2);
        for(int i = 0;i<2;i++){
            List<Solution> l = Solution.makeOffspringFrom(s1,s2);
            for(Solution s : l)
                s.testForDuplicates();
            System.out.println(l);
        }
    }

    int getNumberOfSolutions(){
        return solutionList.size();
    }
    int getGeneration() { return currentNumberOfGeneration;  }
    int getMinimalFitness() { return currentMinimalFitness; }
    Solution getCurrentBestSolution() { return currentBestSolution; }

    @Override
    public String toString() {
        return "Algorithm{" +
                "currentMinimalFitness=" + currentMinimalFitness +
                ", currentNumberOfGeneration=" + currentNumberOfGeneration +
                '}';
    }
}
