package Baekjoon.Quiz1476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1476 {

    private static final int A = 15;
    private static final int B = 28;
    private static final int C = 19;

    public static void main(String[] args) throws IOException {
        int a, b, c;
        int cnt = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        while (true) {
            // 값을 하나씩 줄여 (1, 1, 1)이 되면 종료한다.
            if (a == 1 && b == 1 && c == 1) {
                System.out.println(cnt);
                break;
            }

            cnt++;

            a--;
            if (a <= 0) {
                a = A;
            }

            b--;
            if (b <= 0) {
                b = B;
            }

            if (c <= 0) {
                c = C;
            }
            c--;

        }


    }

}

