package Baekjoon.Quiz14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz14499 {

    private static int N, M, x, y, K;
    private static int[][] map;
    private static int[] dx = {0, 1, -1, 0, 0};
    private static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = N - Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = N - 1; i >= 0; i--) {
            st = new StringTokenizer(br.readLine());
            for (int j = M - 1; j >= 0; j--) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (K-- > 0) {
            int dir = Integer.parseInt(st.nextToken());
            int newX = x + dx[dir];
            int newY = y + dy[dir];



        }


    }


}
