package Baekjoon.Samsung.Quiz14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Quiz14501 {

    // 입력으로 받는 변수 N
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Consult consult;

        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 2];
        Stack<Consult> stack = new Stack<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stack.push(new Consult(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while (!stack.empty()) {
            consult = stack.pop();
            // 해당 상담 날짜가 범위 안에 있으면
            if(consult.day + consult.t - 1 <= N){
                // 이전의 최대값과 현재의 p를 더해 dp[현재날]에 넣어준다.
                dp[consult.day] = consult.p + findMax(dp, consult.t + consult.day);
            }
        }
        System.out.println(findMax(dp, 0));
    }

    private static int findMax(int[] dp, int index){
        int max = 0;
        for(int i = index ; i < dp.length; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private static void printArr(int[] dp) {
        for (int i = 1; i <= N; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println(" ");
    }

}

class Consult {
    int day;
    int t;
    int p;

    // 몇번째 날의 상담인지(index), t, p를 담는 클래스
    Consult(int day, int t, int p) {
        this.day = day;
        this.t = t;
        this.p = p;
    }

}