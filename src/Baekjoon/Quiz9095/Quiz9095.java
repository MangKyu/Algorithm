package Baekjoon.Quiz9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz9095 {
    // 순차적으로 해당 index의 숫자를 만들기 위해 필요한 숫자를 저장한다.
    private static int[] dp;

    // N의 값은 11 미만이므로 최대값은 10이다.
    private static final int MAX_NUM = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        //0~10까지를 저장하기 위해 크기가 MAX_NUM + 1인 배열을 생성한다.
        dp = new int[MAX_NUM + 1];

        //dp를 위해 1, 2, 3 까지는 초기화를 한다.
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        while (T--> 0) {
            int num = Integer.parseInt(br.readLine());
            //해당 num의 값이 0이면 아직 계산이 안된 상황이므로, 시작점(4)부터 계산을 시작한다.
            if (dp[num] == 0) {
                for (int i = 4; i <= num; i++) {
                    if (dp[i] == 0) {
                        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                    }
                }
            }
            sb.append(dp[num]).append("\n");
        }


        System.out.print(sb.toString());
    }
}