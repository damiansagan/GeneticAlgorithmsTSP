package com.labspec.mieczkowskasagan;

import java.util.Arrays;
import java.util.Random;


class RegionMatrix extends Region {

    private int[][] distancesBetweenCities;

    RegionMatrix(int numberOfCities) {
        super(numberOfCities);

        Random generator = new Random();
        this.distancesBetweenCities = new int[numberOfCities][numberOfCities];
        for(int i = 0; i<numberOfCities; i++){
            distancesBetweenCities[i][i]=0;
            for(int j = i+1; j<numberOfCities; j++){
                int rand = Math.abs(generator.nextInt(maxDistanceBetween+1));
                distancesBetweenCities[i][j]=rand;
                distancesBetweenCities[j][i]=rand;
            }
        }
    }

    public int getDistanceBetween(int cityA, int cityB){
        return distancesBetweenCities[cityA][cityB];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(distancesBetweenCities).replaceAll("],", "]," + System.getProperty("line.separator"));
    }
}
