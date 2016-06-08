package com.labspec.mieczkowskasagan;


import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

class XYSeriesCollectionBuilder {

    private XYSeriesCollection xySeriesCollection = new XYSeriesCollection();

    public XYSeriesCollectionBuilder addSolutions(Experiment experiment){
        XYSeries xySeries = new XYSeries(experiment.getName());
        xySeriesCollection.addSeries(xySeries);
        return this;
    }

    public XYSeriesCollection build(){
        return xySeriesCollection;
    }

}
