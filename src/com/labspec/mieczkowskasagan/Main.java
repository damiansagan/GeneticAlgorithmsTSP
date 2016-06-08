package com.labspec.mieczkowskasagan;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.invokeAndWait;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Region region = new RandomXYRegion(100); //numberOfCities
        GeneticParameters parameters = new GeneticParameters(
                100, //initialPopulation
                5000, //generationsRequired
                0, //maximalAcceptableFitness
                0.2, //coefficientOfMutantsEachGeneration
                0.005 //coefficientOfMutatedGenesInChromosomes
        );


        int NUMBER_OF_EXPERIMENTS = 10;
        List<Experiment> experiments= new ArrayList<>();
        experiments.add(new GreedyExperiment(region));
        for(int i = 0; i<NUMBER_OF_EXPERIMENTS; i++)
            experiments.add(new GeneticExperiment(region,parameters));

        XYSeriesCollection fitnessCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries("fluctuations");
        List<Thread> seriesAdding = new ArrayList<>();
        experiments.forEach(experiment ->
                seriesAdding.add(new Thread(() -> series.add(experiment.getId(),experiment.getSolution().getFitness()))));
        for(Thread t : seriesAdding) {
            t.start();
            t.join();
        }
        fitnessCollection.addSeries(series);
        showGUI(fitnessCollection, "Fitness in function of experiment", "experiment number", "fitness");
    }

    private static void showGUI(XYSeriesCollection xySeriesCollection, String title, String XAxis, String YAxis) throws InvocationTargetException, InterruptedException {
        invokeAndWait(() -> startJFrame(new ChartManager(xySeriesCollection,XAxis,YAxis), title));
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
