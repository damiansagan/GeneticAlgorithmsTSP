package com.labspec.mieczkowskasagan;

import java.awt.*;
import java.util.*;
import java.util.List;

class RandomXYRegion extends Region {


    private java.util.List<Point> locationOfCity;

    RandomXYRegion(int numberOfCities){
        super(numberOfCities);
        Random generator = new Random();
        int maxXY = 100;
        this.locationOfCity= new ArrayList<>(numberOfCities);
        for (int i=0; i<numberOfCities; i++){
            int randX = Math.abs(generator.nextInt(maxXY)+1);
            int randY = Math.abs(generator.nextInt(maxXY)+1);
            Point p = new Point(randX,randY);
            locationOfCity.add(p);
        }
    }

    @Override
    public double getDistanceBetween(int cityA, int cityB) {
        return +locationOfCity.get(cityA).distanceSq(locationOfCity.get(cityB));
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

    public Point getPosition(int city){ return locationOfCity.get(city);
    }

    /*
    proponuję zrobić sobie strukturę danych zawierającą punkty,
    na przykład listę losowych punktow i to będą nasze miasta,
    a potem łatwo będzie używać wbudowanych metod :)

    trzeba rozszerzyć klasę Region tak jak w RandomDistancesRegion
    WAŻNE: na zewnątrz ma być nie widać, że to jest zaimplementowane jako Point,
    czyli zgodnie z sygnaturami klasy bazowej :)
    public abstract int getDistanceBetween(int cityA, int cityB);
    public abstract Integer getNearestCityFrom(Integer city, List<Integer> excluding);

    zrob tez metode get city position: Point getPosition(int city);
    przyda się potem podczas rysowania :)

    poniżej przykład:

    {
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,5);

    System.out.println("Odleglosc miedzy p1 i p2: "+p1.distanceSq(p2));
    }*/

}
