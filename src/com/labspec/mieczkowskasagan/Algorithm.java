package com.labspec.mieczkowskasagan;

import java.util.Collections;
import java.util.List;

public class Algorithm {
    private Region region;
    List<Solution> solutionList;


    public Algorithm() {
        region = new RegionMatrix(10);
        solutionList = Solution.produce(10,region);

    }

    public void testPrint(){
        System.out.println();
        System.out.println(region);
        Collections.sort(solutionList);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
        System.out.println("Dzieci:");
        System.out.println(Solution.makeOffspringFrom(solutionList.get(0),solutionList.get(1)));

    }

    public int getParameterP(){
        return solutionList.size();
    }

}
