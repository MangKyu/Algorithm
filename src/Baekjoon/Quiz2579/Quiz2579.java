package Baekjoon.Quiz2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stepNum = Integer.parseInt(br.readLine());
        int[] arr = new int[stepNum + 1];
        for (int i = 1; i <= stepNum; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(retrieveMaxScore(arr));
    }

    private static int retrieveMaxScore(int[] scoreArr) {
        int[] rstArr = new int[scoreArr.length];

        rstArr[1] = scoreArr[1];
        if(scoreArr.length > 2) {
            rstArr[2] = Math.max(scoreArr[0]+scoreArr[2], scoreArr[1]+scoreArr[2]);
            for(int i = 3 ; i < scoreArr.length ; i ++){
                int temp1 = rstArr[i-2] + scoreArr[i];
                int temp2 = rstArr[i-3] + scoreArr[i-1] + scoreArr[i];
                rstArr[i] = Math.max(temp1, temp2);
                System.out.println(i + "    " + rstArr[i]);
            }
        }
        return rstArr[scoreArr.length - 1];
    }
}