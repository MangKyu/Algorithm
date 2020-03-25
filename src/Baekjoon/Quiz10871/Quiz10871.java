package Baekjoon.Quiz10871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz10871 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(inputs[0]);
        int X = Integer.parseInt(inputs[1]);

        int[] arr = new int[N];

        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N ; i++){
            if(arr[i]<X){
                System.out.print(arr[i] + " ");
            }
        }

    }
}
