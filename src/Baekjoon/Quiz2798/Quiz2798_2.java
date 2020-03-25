package Baekjoon.Quiz2798;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2798_2 {

    private static int[] cards;
    private static int N;
    private static int M;

    private static boolean[] isSelected;
    private static int sum;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cards = new int[N + 1];
        isSelected = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        sum = 0;
        answer = 0;
        int i = 1;

        while (st.hasMoreTokens()) {
            cards[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        dfs(0, 1);

        System.out.println(answer);
    }

    private static void dfs(int count, int index) {
        // 카드를 3개 뽑았을 때
        if (count > 2) {
            // M이랑 가장 근접한지 검사
            if (sum > answer && sum <= M) {
                answer = sum;
            }

        } else {
            // 카드가 3개보다 적게 뽑았을 때
            for (int i = index; i <= N ; i++) {
                if (!isSelected[i]) {
                    isSelected[i] = true;
                    sum = sum + cards[i];
                    dfs(count + 1, index + 1);
                    sum = sum - cards[i];
                    isSelected[i] = false;
                }
            }
        }
    }
}
