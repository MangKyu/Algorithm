package Baekjoon.Quiz1929;

import java.io.*;
import java.util.StringTokenizer;

public class Quiz1929 {

    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int cnt = 0;

        for(int i=M; i<=N; i++) {
            int divide = 2;
            double root = Math.sqrt(i);
            boolean primeNumberFlag = true;

            if(i == 1) {
                primeNumberFlag = false;
            }

            while(divide <= root) {

                if(i%divide == 0) {
                    primeNumberFlag = false;
                    break;
                }
                divide++;
            }
            if(primeNumberFlag == true) {
                bw.write(String.valueOf(i));
                cnt++;
                bw.newLine();
            }
        }
        bw.flush();
        System.out.println(cnt);
    }

}
