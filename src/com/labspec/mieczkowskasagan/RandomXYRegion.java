package com.labspec.mieczkowskasagan;

import java.awt.*;
import java.util.*;
import java.util.List;

class RandomXYRegion extends Region {

    private List<Point> locationOfCity;

    RandomXYRegion(int numberOfCities, int maxXY){
        super(numberOfCities);
        Random generator = new Random();
        this.locationOfCity= new ArrayList<>(numberOfCities);
        for (int i=0; i<numberOfCities; i++){
            int randX = generator.nextInt(maxXY)+1;
            int randY = generator.nextInt(maxXY)+1;
            Point p = new Point(randX,randY);
            locationOfCity.add(p);
        }
    }

    @Override
    public double getDistanceBetween(int cityA, int cityB) {
        return locationOfCity.get(cityA).distanceSq(locationOfCity.get(cityB));
    }

    @Override
    public Integer getNearestCityFrom(Integer city, List<Integer> excluding) {
        Integer nearest = null;
        double min = Double.MAX_VALUE;
        double val;
        for(int i = 0; i<getNumberOfCities();i++) {
            val = getDistanceBetween(city,i);
            if(val<min && val > 0 && !excluding.contains(i)){
                min = val;
                nearest=i;
            }
        }
        return nearest;
    }

    public Point getPosition(int city){ return locationOfCity.get(city); }
}
