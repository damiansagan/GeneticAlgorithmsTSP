package com.labspec.mieczkowskasagan;

import java.util.List;

public class Algorithm {
    private Region region;
    List<Solution> solutionList;


    public Algorithm() {
        region = new RegionMatrix(4);
        solutionList = Solution.produce(3,region);
    }

    public void testPrint(){
        System.out.println(region);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));
        for(Solution s : solutionList){
            System.out.println(s.computeFitness());
        }
        System.out.println(Solution.makeOffspringFrom(solutionList.get(0),solutionList.get(1)));
    }

}
