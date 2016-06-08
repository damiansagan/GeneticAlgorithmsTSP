package com.labspec.mieczkowskasagan;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        XYSeries genetic = new XYSeries("genetic");
        XYSeries greedy = new XYSeries("greedy");
        Region region = new RandomXYRegion(100); //numberOfCities

        GeneticParameters parameters = new GeneticParameters(
                1000, //initialPopulation
                2000, //generationsRequired
                0, //maximalAcceptableFitness
                0.2, //coefficientOfMutantsEachGeneration
                0.005 //coefficientOfMutatedGenesInChromosomes
        );

        Population population = new Population(region,parameters);

        //Double g = new GreedyExperiment().getFitness();
        while(!population.fulfillCriteria()){
            population.linearSelection();
            population.crossover();
            population.mutate();
            population.analyze();
            genetic.add(population.getGenerationNumber(), population.getBestFitness());
            //greedy.add(population.getGenerationNumber(), g);
        }


        XYSeriesCollection fitnessCollection = new XYSeriesCollection(genetic);
        fitnessCollection.addSeries(greedy);
        showGUI(fitnessCollection, "Fitness in function of generation", "generation number", "fitness");
    }

    private static void showGUI(XYSeriesCollection dataset, String title, String XAxis, String YAxis) throws InvocationTargetException, InterruptedException {
        // provide GUI to be run on SWING thread
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                // create GUI object
                ChartManager chartManagerGenetic = new ChartManager(dataset,XAxis,YAxis);
                startJFrame(chartManagerGenetic, title);
            }
        });
    }

    private static void startJFrame(ChartManager chartManager, String title){
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(1, 1));
        f.setSize(1200, 300);
        f.setMinimumSize(new Dimension(200, 200));
        f.setLocation(300, 300);
        f.setLayout(new BorderLayout());
        f.add(chartManager.getChartPanel(), BorderLayout.CENTER);
        f.setVisible(true);
    }

}
