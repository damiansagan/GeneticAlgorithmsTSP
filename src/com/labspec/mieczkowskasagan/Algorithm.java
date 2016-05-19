package com.labspec.mieczkowskasagan;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class Algorithm {
    //variables
    private final Region region;
    private List<Solution> solutionList; //population
    private static final Random generator = new Random();

    private int currentNumberOfGeneration = 0;
    private int currentMinimalFitness = Integer.MAX_VALUE;

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
    }

    public void naturalSelection() {
        //DAMIAN
    }

    public void crossover() {
        //DOROTA
    }

    public void mutate() {
        for(Solution solution : solutionList){
            if(probabilityTest(coefficientOfMutantsEachGeneration))
                solution.mutate(coefficientOfMutatedGenesInChromosomes);
        }
    }

    public void analyzePopulation() {
        Collections.sort(solutionList);
        currentMinimalFitness=solutionList.get(0).getFitness();

    }

    public boolean isFinished(){
        currentNumberOfGeneration++;
        System.out.println(this);
        if(currentNumberOfGeneration >=generationsRequired ||
                currentMinimalFitness <=maximalAcceptableFitness){
            return true;
        }
        return false;
    }

    public boolean probabilityTest(double probability){
        return generator.nextDouble() <= probability ? true : false;
    }

    public void testPrint(){
        System.out.println(region);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
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
