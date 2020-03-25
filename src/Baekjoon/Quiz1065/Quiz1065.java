package Baekjoon.Quiz1065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1065 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int count = 0;

        if(num < 100){
            count = num;
        }else{
            count = 99;
            for(int i = 100; i <= num ; i++){
                count += checkHanNumber(i);
            }

            if(num == 1000){
                count--;
            }

        }
        System.out.println(count);
    }

    private static int checkHanNumber(int num) {
        int num1 = num / 100 % 10;
        int num2 = num / 10 % 10;
        int num3 = num % 10;

        if (num2 * 2 == num1 + num3) {
            return 1;
        }
        return 0;
    }

}