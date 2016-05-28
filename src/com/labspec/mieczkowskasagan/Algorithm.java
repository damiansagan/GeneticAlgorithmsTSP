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


    Algorithm(int numberOfChromosomes, int initialPopulation, int generationsRequired, int maximalAcceptableFitness,
              double coefficientOfMutantsEachGeneration, double coefficientOfMutatedGenesInChromosomes) {
        this.generationsRequired = generationsRequired;
        this.maximalAcceptableFitness = maximalAcceptableFitness;
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
        region = new RandomDistancesRegion(numberOfChromosomes);
        solutionList = Solution.produce(initialPopulation,region);
    }

    void randomSelection(double coefficientOfLinearSelection) {
        if (solutionList == null || solutionList.size() < 2) return;
        boolean linearSelection = probabilityTest(coefficientOfLinearSelection);
        if (linearSelection)
            linearSelection(); //linear selection algorithm
        else
            rouletteSelection(); //roulette selection algorithm
    }

    private void linearSelection(){
        Collections.sort(solutionList,Collections.reverseOrder());
        double Sn=(1+solutionList.size())*0.5*solutionList.size();
        double rescale = (double)solutionList.size()/Sn;
        List<Solution> newPopulation = new ArrayList<>(solutionList.size());
        while(newPopulation.size()<solutionList.size()) {
            ListIterator<Solution> iterator = solutionList.listIterator();
            int index = 1;
            while (iterator.hasNext() && newPopulation.size() < solutionList.size()) {
                Solution solution = iterator.next();
                if (generator.nextDouble()*rescale <= (double) index / Sn)
                    newPopulation.add(solution);
                index++;
            }
        }
        solutionList=newPopulation;
    }

    private void rouletteSelection() {
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int val;
        for(Solution solution : solutionList) {
            val = solution.getFitness();
            sum += val;
            if(val>maxValue) maxValue = val;
            if(val<minValue) minValue = val;
        }
        double rescale = (double)(maxValue-minValue+1) / (sum+1);
        List<Solution> newPopulation = new ArrayList<>(solutionList.size());
        while(newPopulation.size()<solutionList.size()) {
            ListIterator<Solution> iterator = solutionList.listIterator();
            int index = 1;
            while (iterator.hasNext() && newPopulation.size() < solutionList.size()) {
                Solution solution = iterator.next();
                if (generator.nextDouble()*rescale <= (double)(maxValue-solution.getFitness()+1) / (sum+1))
                    newPopulation.add(solution);
                index++;
            }
        }
        solutionList=newPopulation;
    }

    void crossover() {
        List<Solution> newPopulation = new ArrayList<>(solutionList.size());
        Collections.sort(solutionList);
        newPopulation.addAll(solutionList.subList(0,generator.nextInt(solutionList.size())));
        while(newPopulation.size()<solutionList.size()){
            newPopulation.addAll(Solution.makeOffspringFrom(
                    solutionList.get(generator.nextInt(solutionList.size())),
                    solutionList.get(generator.nextInt(solutionList.size()))
            ));
        }
        solutionList=newPopulation;
    }

    void mutate() {
        for(Solution solution : solutionList)
            if(probabilityTest(coefficientOfMutantsEachGeneration))
                solution.mutate(coefficientOfMutatedGenesInChromosomes);
    }

    void analyzePopulation(){
        if(solutionList==null || solutionList.isEmpty()) return;
        currentBestSolution=Collections.min(solutionList);
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
    void printPopulation(){ System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator"))); }

    @Override
    public String toString() {
        return "Algorithm{" +
                "currentMinimalFitness=" + currentMinimalFitness +
                ", currentNumberOfGeneration=" + currentNumberOfGeneration +
                '}';
    }
}
