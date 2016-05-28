package com.labspec.mieczkowskasagan;

import java.awt.*;

public class RandomXYRegion {

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
    */
    {
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,5);
    System.out.println("Odleglosc miedzy p1 i p2: "+p1.distanceSq(p2));
    }

}
