package com.labspec.mieczkowskasagan;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Random generator = new Random();

        int N = 10; //number of cities

        int[][] distances = new int[N][N];
        for(int i = 0; i<N; i++){
            distances[i][i]=0;
            for(int j = i+1; j<N; j++){
                int rand = Math.abs(generator.nextInt()%100);
                distances[i][j]=rand;
                distances[j][i]=rand;
            }

        }
        System.out.println(Arrays.deepToString(distances).replaceAll("],", "]," + System.getProperty("line.separator")));
    }

}
