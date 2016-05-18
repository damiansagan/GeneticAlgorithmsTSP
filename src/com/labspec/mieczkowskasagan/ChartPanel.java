package com.labspec.mieczkowskasagan;

import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ChartPanel extends JPanel{

    private JFreeChart chart;

    ChartPanel(JFreeChart chart) {
        this.chart = chart;
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    public void paintComponent(Graphics g) {
        BufferedImage image = chart.createBufferedImage(this.getWidth(), this.getHeight());
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

}
