package Baekjoon.Quiz2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2293 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 해당 가치를 만드는 경우의 수를 저장하는 배열
        int[] dp = new int[k + 1];
        // 코인 정보를 저장하는 배열
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;

        /*
        idx      0 1 2 3 4 5 6 7 8 9 10
        dp[idx] 1 0 0 0 0 0 0 0 0 0  0 => 초기화
        dp[idx] 1 1 1 1 1 1 1 1 1 1  1 => 1만을 이용해서 해당 숫자 만들기
        dp[idx] 1 1 2 2 3 3 4 4 5 5  6 => 1과 2를 사용해서 해당 숫자 만들기
        dp[idx] 1 1 2 2 3 4 5 6 7 8 10 => 1과 2와 5를 사용해서 해당 숫자 만들기

        1과 2를 사용하여 10까지 만든 상황에서 5동전을 추가로 사용하는 경우, 해당 숫자가 5보다 작으면 5동전을 쓸일이 없다.
        5보다 더 큰 숫자에 대해 5를 추가하여 만드는 방법을 생각해보면 아래와 같다.
        1. 5: 기존의 경우에 5동전 1개 추가
        2. 6: 기존의 경우에 5동전 1개 추가, 6-5=1 만드는 법을 추가해야 함 => dp[6] + dp[6-5]
        3. 7: 기존의 경우에 5동전 1개 추가, 7-5=2 만드는 법을 추가해야 함 => dp[7] + dp[7-5]
        4. 8: 기존의 경우에 5동전 1개 추가, 8-5=3 만드는 법을 추가해야 함 => dp[8] + dp[8-5]
        5. 9: 기존의 경우에 5동전 1개 추가, 9-5=4 만드는 법을 추가해야 함 => dp[9] + dp[9-5]
        6. 10: 기존의 경우에 5동전 1개 추가, 10-5=5 만드는 법을 추가해야 함=> dp[10] + dp[10-5]
         */

        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= k; i++) {
                if (i - coins[j] >= 0) {
                    dp[i] = dp[i] + dp[i - coins[j]];
                }
            }
        }

        System.out.println(dp[k]);
    }
}