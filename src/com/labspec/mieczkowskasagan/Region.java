package com.labspec.mieczkowskasagan;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

abstract class Region {

    final List<Integer> listOfCities;
    final int maxDistanceBetween = 100;

    Region(int numberOfCities) {
        listOfCities = new ArrayList<>(numberOfCities);
        IntStream.range(0, numberOfCities).forEach(listOfCities::add);
    }

    public abstract int getDistanceBetween(int cityA, int cityB);

    Integer getNumberOfCities(){
        return listOfCities.size();
    }

    public List<Integer> getListOfCities() {
        return listOfCities;
    }

    @Override
    public String toString() {
        return "Region{" +
                "numberOfCities=" + getNumberOfCities() +
                ", listOfCities=" + listOfCities +
                '}';
    }

}
