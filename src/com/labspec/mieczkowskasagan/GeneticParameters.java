package com.labspec.mieczkowskasagan;


class GeneticParameters {

    private int initialPopulation;
    private int generationsRequired;
    private int maximalAcceptableFitness;
    private double coefficientOfMutantsEachGeneration;
    private double coefficientOfMutatedGenesInChromosomes;

    GeneticParameters(int initialPopulation, int generationsRequired, int maximalAcceptableFitness, double coefficientOfMutantsEachGeneration, double coefficientOfMutatedGenesInChromosomes) {
        this.initialPopulation = initialPopulation;
        this.generationsRequired = generationsRequired;
        this.maximalAcceptableFitness = maximalAcceptableFitness;
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
    }

    int getGenerationsRequired() {
        return generationsRequired;
    }

    void setGenerationsRequired(int generationsRequired) {
        this.generationsRequired = generationsRequired;
    }

    int getMaximalAcceptableFitness() {
        return maximalAcceptableFitness;
    }

    void setMaximalAcceptableFitness(int maximalAcceptableFitness) {
        this.maximalAcceptableFitness = maximalAcceptableFitness;
    }

    double getCoefficientOfMutantsEachGeneration() {
        return coefficientOfMutantsEachGeneration;
    }

    void setCoefficientOfMutantsEachGeneration(double coefficientOfMutantsEachGeneration) {
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
    }

    double getCoefficientOfMutatedGenesInChromosomes() {
        return coefficientOfMutatedGenesInChromosomes;
    }

    void setCoefficientOfMutatedGenesInChromosomes(double coefficientOfMutatedGenesInChromosomes) {
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
    }

    int getInitialPopulation() {
        return initialPopulation;
    }

    void setInitialPopulation(int initialPopulation) {
        this.initialPopulation = initialPopulation;
    }
}
