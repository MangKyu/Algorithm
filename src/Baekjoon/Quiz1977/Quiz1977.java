package Baekjoon.Quiz1977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1977 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int num1 = (int) Math.ceil(Math.sqrt(M));

        for(int i = num1; i*i<= N; i++){
            sum += i*i;
        }

        if(sum ==0){
            System.out.println(-1);
        }else{
            System.out.println(sum);
            System.out.println(num1 * num1);
        }
    }
}
