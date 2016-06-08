package com.labspec.mieczkowskasagan;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

class ChartManager {

    private JFreeChart chart;
    private ChartPanel chartPanel;

    ChartManager(JFreeChart chart) {
        this.chart =chart;
        formatXYPlot(chart);
        setChartPanel(new ChartPanel(chart));
    }

    static JFreeChart createXYLineChart(XYSeriesCollection dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createXYLineChart(title, // Title
                xAxis, // X-Axis label
                yAxis, // Y-Axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // show legend
                false, // Show tooltips
                false // url show
        );
    }

    static JFreeChart createXYScatterChart(XYSeriesCollection dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createScatterPlot(title, // Title
                xAxis, // X-Axis label
                yAxis, // Y-Axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // show legend
                false, // Show tooltips
                false // url show
        );
    }


    private void formatXYPlot(JFreeChart chart) {
        XYPlot xyPlot = chart.getXYPlot();
        xyPlot.setBackgroundPaint(Color.WHITE);
        xyPlot.setRangeGridlinePaint(Color.BLACK);
        xyPlot.setDomainGridlinePaint(Color.BLACK);

        NumberAxis domainAxis = (NumberAxis) xyPlot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }

    void refresh() {
        if (chartPanel.getVisibleRect() != null)
            chartPanel.repaint();
    }

    ChartPanel getChartPanel() {
        return chartPanel;
    }

    private void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

}
