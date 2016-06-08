package com.labspec.mieczkowskasagan;

import java.util.*;

class Population {
    private static final Random generator = new Random();
    private static int counter = 0;

    private int id;
    private int generation = 0;
    private final GeneticParameters parameters;

    private List<Solution> solutionList;
    private Solution bestSolution = null;
    private double bestFitness = Double.MAX_VALUE;

    Population(Region region, GeneticParameters parameters) {
        this.id=++counter;
        this.parameters=parameters;
        solutionList = Solution.produce(parameters.getInitialPopulation(),region);
    }

    void randomSelection(double coefficientOfLinearSelection) {
        if (solutionList == null || solutionList.size() < 2) return;
        boolean linearSelection = probabilityTest(coefficientOfLinearSelection);
        if (linearSelection)
            linearSelection(); //linear selection algorithm
        else
            rouletteSelection(); //roulette selection algorithm
    }

    void linearSelection(){
        Collections.sort(solutionList,Collections.reverseOrder());
        double Sn=(1+solutionList.size())*0.5*solutionList.size();
        double rescale = (double)solutionList.size()/Sn;
        List<Solution> newPopulation = new ArrayList<>(solutionList.size());
        while(newPopulation.size()<parameters.getInitialPopulation()) {
            ListIterator<Solution> iterator = solutionList.listIterator();
            int index = 1;
            while (iterator.hasNext() && newPopulation.size() < parameters.getInitialPopulation()) {
                Solution solution = iterator.next();
                if (generator.nextDouble()*rescale <= (double) index / Sn)
                    newPopulation.add(solution);
                index++;
            }
        }
        solutionList=newPopulation;
    }

    void rouletteSelection() {
        double sum = 0;
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        double val;
        for(Solution solution : solutionList) {
            val = solution.getFitness();
            sum += val;
            if(val>maxValue) maxValue = val;
            if(val<minValue) minValue = val;
        }
        double rescale = (maxValue-minValue+1) / (sum+1);
        List<Solution> newPopulation = new ArrayList<>(solutionList.size());
        while(newPopulation.size()<parameters.getInitialPopulation()) {
            ListIterator<Solution> iterator = solutionList.listIterator();
            while (iterator.hasNext() && newPopulation.size() < parameters.getInitialPopulation()) {
                Solution solution = iterator.next();
                if (generator.nextDouble()*rescale <= (maxValue-solution.getFitness()+1) / (sum+1))
                    newPopulation.add(solution);
            }
        }
        solutionList=newPopulation;
    }

    void crossover() {
        List<Solution> newPopulation = new ArrayList<>(solutionList.size());
        Collections.sort(solutionList);
        newPopulation.addAll(solutionList.subList(0,generator.nextInt(solutionList.size())));
        while(newPopulation.size()<parameters.getInitialPopulation()){
            newPopulation.addAll(Solution.makeOffspringFrom(
                    solutionList.get(generator.nextInt(solutionList.size())),
                    solutionList.get(generator.nextInt(solutionList.size()))
            ));
        }
        solutionList=newPopulation;
    }

    void mutate() {
        for(Solution solution : solutionList)
            if(probabilityTest(parameters.getCoefficientOfMutantsEachGeneration()))
                solution.mutate(parameters.getCoefficientOfMutatedGenesInChromosomes());
    }

    void analyze(){
        if(solutionList==null || solutionList.isEmpty()) return;
        bestSolution =Collections.min(solutionList);
        bestFitness = bestSolution.getFitness();
    }

    boolean fulfillCriteria(){
        generation++;
        System.out.println("Generation number: " + generation);
        return generation >= parameters.getGenerationsRequired() ||
                bestFitness <= parameters.getMaximalAcceptableFitness() || solutionList.size()<2;
    }

    private boolean probabilityTest(double probability){
        return generator.nextDouble() <= probability;
    }

    private void crossoverTest(Region region){
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

    int getSize(){
        return solutionList.size();
    }
    int getGenerationNumber() { return generation;  }
    Double getBestFitness() { return bestFitness; }
    Solution getBestSolution() { return bestSolution; }
    void printPopulation(){ System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator"))); }

    @Override
    public String toString() {
        return "Population{" +
                "bestFitness=" + bestFitness +
                ", generation=" + generation +
                '}';
    }
}
