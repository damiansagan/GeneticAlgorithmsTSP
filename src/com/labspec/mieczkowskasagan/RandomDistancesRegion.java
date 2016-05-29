package com.labspec.mieczkowskasagan;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
class RandomDistancesRegion extends Region {

    private int[][] distancesBetweenCities;

    RandomDistancesRegion(int numberOfCities) {
        super(numberOfCities);
        int maxDistanceBetween = 100;
        Random generator = new Random();
        this.distancesBetweenCities = new int[numberOfCities][numberOfCities];
        for(int i = 0; i<numberOfCities; i++){
            distancesBetweenCities[i][i]=0;
            for(int j = i+1; j<numberOfCities; j++){
                int rand = Math.abs(generator.nextInt(maxDistanceBetween)+1);
                distancesBetweenCities[i][j]=rand;
                distancesBetweenCities[j][i]=rand;
            }
        }
    }

    @Override
    public Integer getNearestCityFrom(Integer city, List<Integer> excluding) {
        Integer nearest = null;
        int min = Integer.MAX_VALUE;
        int val;
        for(int i = 0; i<getNumberOfCities();i++) {
            val = distancesBetweenCities[city][i];
            if(val<min && val > 0 && !excluding.contains(i)){
                min = val;
                nearest=i;
            }
        }
        return nearest;
    }

    @Override
    public double getDistanceBetween(int cityA, int cityB){
        return distancesBetweenCities[cityA][cityB];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(distancesBetweenCities).replaceAll("],", "]," + System.getProperty("line.separator"));
    }
}
*/