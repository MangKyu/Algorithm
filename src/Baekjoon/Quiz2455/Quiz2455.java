package Baekjoon.Quiz2455;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2455 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int maxPerson = Integer.MIN_VALUE;
        int curPerson = 0;

        for(int i = 0 ; i < 4 ; i++){
            st = new StringTokenizer(br.readLine());
            int out = Integer.parseInt(st.nextToken());
            int in = Integer.parseInt(st.nextToken());

            curPerson = curPerson - out + in;
            if(curPerson > maxPerson){
                maxPerson = curPerson;
            }
        }

        System.out.println(maxPerson);

    }
}
