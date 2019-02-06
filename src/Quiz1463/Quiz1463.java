package Quiz1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        makeOne(num);
    }

    private static void makeOne(int num) {
        int[] dp = new int[num + 1];

        // 질문
        // 이러면 순차적으로 탐색하는 것이라 성능이 떨어지지 않나 생각해서 while문으로 skip하는 형태로 진행했었음
        for(int i = 2 ; i <= num ; i++){
            dp[i] = dp[i-1] + 1;

            if(i % 3 == 0 ){
                dp[i] = Math.min(dp[i/3] + 1, dp[i]);
            }

            if(i%2 == 0){
                dp[i] = Math.min(dp[i/2] + 1, dp[i]+1);
            }

        }


        /*
        while (dp[1] == -1) {

            if (num % 3 == 0) {
                dp[num / 3] = dp[num] + 1;
            }

            if (num % 2 == 0) {
                dp[num / 2] = dp[num] + 1;
            }

            if (num - 1 > 1) {
                dp[num - 1] = dp[num] + 1;
            }

        }
        */
        System.out.println(dp[num]);

    }

}