package Baekjoon.Quiz1789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 최댓값의 경우 int형을 벗어나 OverFlow가 발생한다. 그러므로 long형으로 선언한다.
        long S = Long.parseLong(br.readLine());
        long num = 1;
        /**
         * 기본적으로 1부터의 순차합이 최소라는 생각이 떠올랐다.
         * 1+2+3+.... n = 2S 이므로 N * (N+1) = 2S
         * 양변에 1/4를 더하고 루트를 씌우면 N+1/2를 구할 수 있고, 이를 통해 N을 계산한다.
         * 이때 반올림을 사용하면 19.5가 20으로 되어 총합이 S를 넘어버릴 수 있으므로 버림을 사용해주어야 한다.
        **/


        if (S != num) {
            num = ((long) Math.floor(Math.sqrt(2 * S + (double)1/4) - (double) 1 / 2));
        }
        System.out.println(num);
    }
}