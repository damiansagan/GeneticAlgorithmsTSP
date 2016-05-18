package com.labspec.mieczkowskasagan;

import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        XYSeries chartSeriesGenetic = new XYSeries("genetic");
        Algorithm algorithm = new Algorithm(10, 5000, 20, 20);

        algorithm.testPrint();

        while(!algorithm.isFinished()){

            chartSeriesGenetic.add(algorithm.getGeneration(), algorithm.getMinimalFitness());
        }

        // provide GUI to be run on SWING thread
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                // create GUI object
                ChartManager chartManagerGenetic = new ChartManager(chartSeriesGenetic,"generation number","fitness");
                startJFrame(chartManagerGenetic);
            }
        });
    }

    private static void startJFrame(ChartManager chartManager){
        JFrame f = new JFrame("Genetic Chart");
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
