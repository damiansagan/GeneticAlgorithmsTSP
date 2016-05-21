package com.labspec.mieczkowskasagan;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    private final double coefficientOfLinearSelection = 1;


    Algorithm(int numberOfChromosomes, int initialPopulation, int generationsRequired, int maximalAcceptableFitness,
              double coefficientOfMutantsEachGeneration, double coefficientOfMutatedGenesInChromosomes) {
        this.generationsRequired = generationsRequired;
        this.maximalAcceptableFitness = maximalAcceptableFitness;
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
        region = new RegionMatrix(numberOfChromosomes);
        solutionList = Solution.produce(initialPopulation,region);
    }

    void naturalSelection() {
        if(solutionList==null || solutionList.isEmpty()) return;
        boolean linearSelection = probabilityTest(coefficientOfLinearSelection);
//        if(linearSelection) { //linear selection algorithm
//            if(!isSorted) {
//                Collections.sort(solutionList);
//                isSorted=true;
//            }
//            double Sn=(1+solutionList.size())*0.5*solutionList.size();
//            ListIterator<Solution> iterator = solutionList.listIterator();
//            int index=0;
//            while(iterator.hasNext()){
//                Solution solution = iterator.next();
//                if(probabilityTest(1-(double)index/Sn)){
//                    System.out.println(solution);
//                    iterator.remove();
//                }
//                index++;
//            }
//
//        }
//        else{ //roulette selection algorithm
//            System.out.println("roulette");
//        }

    }

    void crossover() {
            //DOROTA
    }

    void mutate() {
        for(Solution solution : solutionList)
            if(probabilityTest(coefficientOfMutantsEachGeneration))
                solution.mutate(coefficientOfMutatedGenesInChromosomes);
        isSorted=false;
    }

    void analyzePopulation() {
        //it does NOT need the population to be sorted
        if(!isSorted)
            currentBestSolution=Collections.min(solutionList);
        else
            currentBestSolution=solutionList.get(0);
        currentMinimalFitness=currentBestSolution.getFitness();
    }

    boolean isFinished(){
        currentNumberOfGeneration++;
        return currentNumberOfGeneration >= generationsRequired ||
                currentMinimalFitness <= maximalAcceptableFitness;
    }

    private boolean probabilityTest(double probability){
        return generator.nextDouble() <= probability;
    }

    public void testPrint(){
        System.out.println(region);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
    }

    private void crossoverTest(){
        Solution s1 = new Solution(region);
        Solution s2 = new Solution(region);
        System.out.println(s1);
        System.out.println(s2);
        for(int i = 0;i<2000;i++){
            List<Solution> l = Solution.makeOffspringFrom(s1,s2);
            for(Solution s : l){
                s.testForDuplicates();
            }
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
