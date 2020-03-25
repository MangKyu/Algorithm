package Baekjoon.Quiz10871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Quiz10871_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arrayList = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ; i++){
            arrayList.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0 ; i < N ; i++){
            if(arrayList.get(i) < X ){
                sb.append(arrayList.get(i)).append("\n");
            }
        }
        System.out.print(sb.toString());

    }
}