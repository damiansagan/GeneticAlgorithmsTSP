package com.labspec.mieczkowskasagan;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

class ChartManager {

    private JFreeChart chart;
    private ChartPanel chartPanel;

    ChartManager(XYSeriesCollection dataset, String xAxis, String yAxis) {
        chart = ChartFactory.createXYLineChart(null, // Title
                xAxis, // X-Axis label
                yAxis, // Y-Axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // show legend
                false, // Show tooltips
                false // url show
        );

        formatXYPlot(chart);
        setChartPanel(new ChartPanel(chart));
    }

    private void formatXYPlot(JFreeChart chart) {
        XYPlot xyPlot = chart.getXYPlot();
        xyPlot.setBackgroundPaint(Color.WHITE);
        xyPlot.setRangeGridlinePaint(Color.BLACK);
        xyPlot.setDomainGridlinePaint(Color.BLACK);
//        NumberAxis domainAxis = (NumberAxis) xyPlot.getDomainAxis();
//        domainAxis.setTickUnit(new NumberTickUnit(1));
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
