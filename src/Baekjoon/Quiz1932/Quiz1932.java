package Baekjoon.Quiz1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // idx는 dp를 계산하기 위해 0번부터 순차로 순회하는 인덱스값
        int idx;
        // tokenCnt는 해당 입력의 갯수로 해당 tokenCnt만큼 dp계산을 해주어야 한다.
        int tokenCnt;
        // max값을 갖는 변수
        int maxNum = 0;
        // 이전의 결과를 저장하여 dp를 계산하기 위해 사용하는 tempDp 배열과 계산한 결과를 저장하기 위한 dp 배열
        int[] dp = new int[N + 1];
        int[] tempDp;

        while (N-- > 0) {
            // 순차적으로 순회하는 idx를 0으로 초기화하고 tempDp에 이전 결과를 복사한다. 그리고 새로운 결과는 dp 배열에 저장한다.
            idx = 0;
            tempDp = Arrays.copyOf(dp, dp.length);

            // 입력을 받아 토큰이 몇개인지 계산한다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            tokenCnt = st.countTokens();

            while (st.hasMoreTokens()) {
                // 덧셈을 처리해야하는 새로운 입력 숫자
                int num = Integer.parseInt(st.nextToken());

                // idx가 0이면 왼쪽에 해당하는 dp값은 존재하지 않으므로 오른쪽 값으로만 계산한다.
                if (idx == 0) {
                    dp[idx] = tempDp[idx] + num;

                    // idx == tokenCnt이면 dp 배열의 끝이므로 오른쪽을 검사하면 안된다. 그러므로 왼쪽으로만 계산한다.
                }else if(idx == tokenCnt){
                    dp[idx] = tempDp[idx-1] + num;
                }else{
                    // 중간에 있는 경우에는 좌와 우의 값에 num을 더한 값중 max값을 갖는다.
                    dp[idx] = Math.max(tempDp[idx - 1] + num, tempDp[idx] + num);
                }
                idx++;
            }
        }

        //최대값을 계산하고 출력한다.
        for (int num : dp) {
            if (maxNum < num) {
                maxNum = num;
            }
        }

        System.out.println(maxNum);

    }
}