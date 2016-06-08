package com.labspec.mieczkowskasagan;


import java.util.ArrayList;
import java.util.List;

class GreedySolution extends Solution{

    GreedySolution(Region region) {
        super(null);
        this.region=region;
        Double best = Double.MAX_VALUE;
        List<Integer> bestSeries = null;
        for(Integer last: region.getListOfCities()){
            final List<Integer> current = new ArrayList<>(region.getNumberOfCities());
            current.add(last);
            while(current.size()<region.getNumberOfCities())
                current.add(last=region.getNearestCityFrom(last,current));
            series = current;
            fitness = null;
            if(getFitness()<best) {
                best=getFitness();
                bestSeries=current;
            }
        }
        series = bestSeries;
    }
}
