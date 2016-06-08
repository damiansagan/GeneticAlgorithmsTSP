package com.labspec.mieczkowskasagan;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static javax.swing.SwingUtilities.invokeAndWait;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        Region region = new RandomXYRegion(25); //numberOfCities
        GeneticParameters parameters = new GeneticParameters(
                1000, //initialPopulation
                200, //generationsRequired
                0.01, //coefficientOfMutantsEachGeneration
                0.005 //coefficientOfMutatedGenesInChromosomes
        );

        List<Experiment> experiments = GeneticExperiment.produce(region,parameters,10);

        showGUI(ChartManager.createXYScatterChart(GeneticExperiment.dispersionXYCollection(experiments),
                "Fitness in function of experiment",
                "experiment number",
                "fitness"));

        showGUI(ChartManager.createXYLineChart(GeneticExperiment.generationXYCollection(experiments,parameters),
                "Fitness in function of generation",
                "generation number",
                "fitness"));
    }

    private static void showGUI(JFreeChart chart) throws InvocationTargetException, InterruptedException {
        invokeAndWait(() -> startJFrame(new ChartManager(chart), chart.getTitle().toString()));
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
