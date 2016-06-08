package com.labspec.mieczkowskasagan;

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
                1000, //initialPopulation
                500, //generationsRequired
                0, //maximalAcceptableFitness
                0.2, //coefficientOfMutantsEachGeneration
                0.005 //coefficientOfMutatedGenesInChromosomes
        );

        List<Thread> threads= new ArrayList<>();
        threads.add(new Thread(new GreedyExperiment(region)));
        for(int i = 0; i<2; i++)
            threads.add(new Thread(new GeneticExperiment(region,parameters)));
        threads.forEach(Thread::start);


        XYSeriesCollection fitnessCollection = new XYSeriesCollection();
        //fitnessCollection.addSeries();
        showGUI(fitnessCollection, "Fitness in function of generation", "generation number", "fitness");
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
