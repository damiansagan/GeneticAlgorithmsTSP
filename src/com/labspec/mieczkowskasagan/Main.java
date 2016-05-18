package com.labspec.mieczkowskasagan;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Region region = new RegionArray(10);
        System.out.println(region);

        List<Solution> solutionList = Solution.produce(20,region);
        System.out.println(solutionList.toString().replaceAll("},", "}," + System.getProperty("line.separator")));


    }

}
