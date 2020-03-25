package Baekjoon.Quiz2798;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2798 {

    private static int[] cards;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cards = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int i = 1;
        int sum = 0;

        while(st.hasMoreTokens()){
            cards[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        for(i = 1 ; i <= N ; i++){
            for(int j = i + 1 ; j <= N ; j++) {
                for (int k = j + 1; k <= N; k++){
                    int tempSum = cards[i] + cards[j] + cards[k];

                    if(tempSum <= M && ((M - tempSum) - (M - sum) < 0)){
                        sum = tempSum;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}