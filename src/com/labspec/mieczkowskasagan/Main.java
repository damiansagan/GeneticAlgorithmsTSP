package com.labspec.mieczkowskasagan;

import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        XYSeries chartSeriesGenetic = new XYSeries("genetic");
        XYSeries population = new XYSeries("population");
        Algorithm algorithm = new Algorithm(
                50, //numberOfChromosomes
                10000, //initialPopulation
                1000, //generationsRequired
                0, //maximalAcceptableFitness
                0.05, //coefficientOfMutantsEachGeneration
                0.05, //coefficientOfMutatedGenesInChromosomes
                0 //coefficientOfLinearSelection
        );
        algorithm.testPrint();

        while(!algorithm.isFinished()){
            algorithm.naturalSelection();
            algorithm.crossover();
            algorithm.mutate();
            algorithm.analyzePopulation();
            chartSeriesGenetic.add(algorithm.getGeneration(), algorithm.getMinimalFitness());
            population.add(algorithm.getGeneration(),algorithm.getNumberOfSolutions());
        }

        showGUI(population, "Population in function of generation", "generation number", "population");
        showGUI(chartSeriesGenetic, "Fitness in function of generation", "generation number", "fitness");
    }

    private static void showGUI(XYSeries chartSeries, String title, String XAxis, String YAxis) throws InvocationTargetException, InterruptedException {
        // provide GUI to be run on SWING thread
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                // create GUI object
                ChartManager chartManagerGenetic = new ChartManager(chartSeries,XAxis,YAxis);
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
