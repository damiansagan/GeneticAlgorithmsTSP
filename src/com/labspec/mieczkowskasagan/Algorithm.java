package com.labspec.mieczkowskasagan;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class Algorithm {
    private static final Random generator = new Random();

    //variables
    private final Region region;
    private List<Solution> solutionList; //population
    private boolean isSorted = false;

    private int currentNumberOfGeneration = 0;
    private int currentMinimalFitness = Integer.MAX_VALUE;
    private Solution currentBestSolution = null;

    //parameters
    private final int generationsRequired;
    private final int maximalAcceptableFitness;
    private final double coefficientOfMutantsEachGeneration;
    private final double coefficientOfMutatedGenesInChromosomes;


    Algorithm(int numberOfChromosomes, int initialPopulation, int generationsRequired, int maximalAcceptableFitness,
              double coefficientOfMutantsEachGeneration, double coefficientOfMutatedGenesInChromosomes) {
        this.generationsRequired = generationsRequired;
        this.maximalAcceptableFitness = maximalAcceptableFitness;
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
        region = new RegionMatrix(numberOfChromosomes);
        solutionList = Solution.produce(initialPopulation,region);
        Collections.sort(solutionList);
        isSorted=true;
    }

    public void naturalSelection() {
        if(!isSorted) {
            Collections.sort(solutionList);
            isSorted=true;
        }

    }

    public void crossover() {
        //DOROTA
    }

    public void mutate() {
        for(Solution solution : solutionList){
            if(probabilityTest(coefficientOfMutantsEachGeneration))
                solution.mutate(coefficientOfMutatedGenesInChromosomes);
        }
        isSorted=false;
    }

    public void analyzePopulation() {
        //it does NOT need the population to be sorted
        if(!isSorted)
            currentBestSolution=Collections.min(solutionList);
        else
            currentBestSolution=solutionList.get(0);
        currentMinimalFitness=currentBestSolution.getFitness();
    }

    public boolean isFinished(){
        currentNumberOfGeneration++;
        return currentNumberOfGeneration >= generationsRequired ||
                currentMinimalFitness <= maximalAcceptableFitness;
    }

    public boolean probabilityTest(double probability){
        return generator.nextDouble() <= probability;
    }

    public void testPrint(){
        System.out.println(region);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
        System.out.println("Dzieci:");
        System.out.println(Solution.makeOffspringFrom(solutionList.get(0),solutionList.get(1)));

    }

    public int getNumberOfSolutions(){
        return solutionList.size();
    }
    public int getGeneration() { return currentNumberOfGeneration;  }
    public int getMinimalFitness() { return currentMinimalFitness; }

    @Override
    public String toString() {
        return "Algorithm{" +
                "currentMinimalFitness=" + currentMinimalFitness +
                ", currentNumberOfGeneration=" + currentNumberOfGeneration +
                '}';
    }
}
