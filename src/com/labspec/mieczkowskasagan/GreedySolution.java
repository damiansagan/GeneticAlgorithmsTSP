package com.labspec.mieczkowskasagan;


import java.util.ArrayList;

class GreedySolution extends Solution{

    GreedySolution(Region region) {
        super(null);
        this.region=region;
        this.series = new ArrayList<>(region.getNumberOfCities());
        Integer last = generator.nextInt(region.getNumberOfCities());
        series.add(last);
        while(series.size()<region.getNumberOfCities())
            series.add(last=region.getNearestCityFrom(last,series));
//        testForDuplicates();
//        System.out.println(this);
    }
}
