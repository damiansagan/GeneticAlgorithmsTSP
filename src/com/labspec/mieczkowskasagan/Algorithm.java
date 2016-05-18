package com.labspec.mieczkowskasagan;

import java.util.Collections;
import java.util.List;

class Algorithm {
    //variables
    private final Region region;
    private List<Solution> solutionList;
    private int currentNumberOfGenerations = 0;
    private int currentMinimalFitnessSeries = Integer.MAX_VALUE;

    //parameters
    private final int generationsRequired;
    private final int maximalAcceptableFitness;
    private final int percentageOfMutantsEachGeneration;
    private final int percentageOfMutatedGenesInChromosomes;




    Algorithm(int generationsRequired, int maximalAcceptableFitness, int percentageOfMutantsEachGeneration, int percentageOfMutatedGenesInChromosomes) {
        this.generationsRequired = generationsRequired;
        this.maximalAcceptableFitness = maximalAcceptableFitness;
        this.percentageOfMutantsEachGeneration = percentageOfMutantsEachGeneration;
        this.percentageOfMutatedGenesInChromosomes = percentageOfMutatedGenesInChromosomes;
        region = new RegionMatrix(20);
        solutionList = Solution.produce(10,region);
    }

    public boolean isFinished(){
        currentNumberOfGenerations++;
        if(currentNumberOfGenerations>=generationsRequired || currentMinimalFitnessSeries<=maximalAcceptableFitness){
            System.out.println(this);
            return true;
        }
        return false;
    }

    public void testPrint(){
//        Solution s = new Solution(region);
//        System.out.println(s);
//        s.mutate(20);
//        System.out.println(s);

        System.out.println(region);
        Collections.sort(solutionList);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
        System.out.println(Solution.makeOffspringFrom(solutionList.get(0),solutionList.get(1)));
    }

    public int getNumberOfSolutions(){
        return solutionList.size();
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "currentMinimalFitnessSeries=" + currentMinimalFitnessSeries +
                ", currentNumberOfGenerations=" + currentNumberOfGenerations +
                '}';
    }
}
