package com.labspec.mieczkowskasagan;

import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static javax.swing.SwingUtilities.invokeAndWait;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        XYSeriesCollection meanDeviationCollection = new XYSeriesCollection();
        XYSeries meanDeviationSeries = new XYSeries("mean genetic deviation");
        XYSeries greedyDeviationSeries = new XYSeries("greedy deviation");
        final GeneticParameters parameters = new GeneticParameters(
                1000, //initialPopulation
                200, //generationsRequired
                0.01, //coefficientOfMutantsEachGeneration
                0.005 //coefficientOfMutatedGenesInChromosomes
        );
        int NUMBER_OF_REGIONS = 100; //number of regions
        for(int i = 1; i<NUMBER_OF_REGIONS+1;i++) {
            Region region = new RandomXYRegion(20, 100); //numberOfCities, maxXY
            List<Experiment> experiments = GeneticExperiment.produce(region, parameters, 20); //number of experiments

            XYSeriesCollection col = GeneticExperiment.dispersionXYCollection(experiments);
            if(i==1){
                showGUI(ChartManager.createXYScatterChart(col,
                        "Fitness in function of experiment",
                        "experiment number",
                        "fitness"));

                showGUI(ChartManager.createXYLineChart(GeneticExperiment.generationXYCollection(experiments, parameters),
                        "Fitness in function of generation",
                        "generation number",
                        "fitness"));
            }

            List colItems = col.getSeries(0).getItems();
            double sum = 0;
            int n = 0;
            for (Object it : colItems) {
                n++;
                sum += ((XYDataItem) it).getYValue();
            }
            double mean = sum/n;
            meanDeviationSeries.add(i,mean);
            greedyDeviationSeries.add(i,col.getY(1,1).doubleValue());
        }
        meanDeviationCollection.addSeries(meanDeviationSeries);
        meanDeviationCollection.addSeries(greedyDeviationSeries);
        showGUI(ChartManager.createXYScatterChart(meanDeviationCollection,
                "Mean fitness of experiments in function of random region",
                "region number",
                "mean fitness"));
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
