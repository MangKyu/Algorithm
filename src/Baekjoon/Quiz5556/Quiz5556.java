package Baekjoon.Quiz5556;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz5556 {

    // 입력으로 받는 N
    private static int N;
    // 1사분면 기준(1,1)의 색
    private static int base;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int cnt = Integer.parseInt(br.readLine());
        int x, y;
        StringBuilder sb = new StringBuilder();
        // 6을 기준으로 가운데의 색이 정해진다. 그러므로 가운데의 색을 계산하여 정해준다.
        // ex) N = 1 ~ 6 => 빨,빨,파,파,노,노
        int remains = N % 6;
        if (remains >= 1 && remains < 3) {
            base = 1;
        } else if (remains >= 3 && remains < 5) {
            base = 2;
        } else {
            base = 3;
        }

        while (cnt --> 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            // x, y의 좌표를 1사분면으로 이동시킨다.
            if (x < N / 2 + 1){
                x = (N + 1 - x) - N / 2;
            }else {
                x = x - N / 2;
            }
            if (y < N / 2 + 1){
                y = (N + 1 -y) - N / 2;
            }else{
                y = y - N / 2;
            }

            // x와 y가 다른경우에는 작은 값을 x에 넣는다.
            if(x != y){
                if(y > x){
                    x = y;
                }
            }
            // 해당 좌표의 색상을 계산한다.
            int num = getColor(x);
            sb.append(num).append("\n");
        }
        System.out.println(sb.toString());

    }

    // 해당 좌표의 색을 계산하는 함수
    private static int getColor(int x){
        int num = 0;
        int remains = (x - 1) % 3;
        switch (remains){
            case 0:
                num = base;
                break;
            case 1:
                num = ((base + 2) % 3);
                break;
            case 2:
                num = ((base + 1) % 3);
                break;
        }
        if (num == 0){
            num = 3;
        }
        return num;
    }

}
