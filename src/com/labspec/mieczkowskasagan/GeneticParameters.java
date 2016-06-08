package com.labspec.mieczkowskasagan;


class GeneticParameters {

    private int initialPopulation;
    private int generationsRequired;
    private double coefficientOfMutantsEachGeneration;
    private double coefficientOfMutatedGenesInChromosomes;

    GeneticParameters(int initialPopulation, int generationsRequired, double coefficientOfMutantsEachGeneration, double coefficientOfMutatedGenesInChromosomes) {
        this.initialPopulation = initialPopulation;
        this.generationsRequired = generationsRequired;
        this.coefficientOfMutantsEachGeneration = coefficientOfMutantsEachGeneration;
        this.coefficientOfMutatedGenesInChromosomes = coefficientOfMutatedGenesInChromosomes;
    }

    int getGenerationsRequired() {
        return generationsRequired;
    }

    void setGenerationsRequired(int generationsRequired) {
        this.generationsRequired = generationsRequired;
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
