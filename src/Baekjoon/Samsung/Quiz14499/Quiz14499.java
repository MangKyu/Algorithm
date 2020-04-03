package Baekjoon.Samsung.Quiz14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Quiz14499 {

    private static int N, M, x, y;
    private static int[][] map;
    private static int[] dice;
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{1, -1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        int k;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        // 각 배열의 인덱스는 초기 주사위의 고정된 1, 2, 3, 4, 5, 6 위치를 나타냄
        dice = new int[7];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while(k --> 0){
            int dir = Integer.parseInt(st.nextToken()) - 1;

            // 이동할 좌표가 경계 안에 있으면
            if(withinBoundaries(x + dx[dir], y + dy[dir])){
                // 좌표를 새로 바꾸고
                x += dx[dir];
                y += dy[dir];
                // 주사위를 이동한다.
                moveDice(dir);
                // 그리고 바닥면에 있는 값을 입력하고 지도와 비교해 값을 복사한다.
                sj.add(String.valueOf(dice[1]));
                copyNumbers();
            }
        }

        System.out.println(sj.toString());
    }

    // 조건에 따라 값을 복사하는 함수
    private static void copyNumbers(){
        if(map[x][y] == 0){
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
    }

    // 동서남북으로 주사위를 이동시키는 함수
    private static void moveDice(int dir){
        int d1 = dice[1];
        int d2 = dice[2];
        int d3 = dice[3];
        int d4 = dice[4];
        int d5 = dice[5];
        int d6 = dice[6];

        switch (dir){
            // 동으로 이동
            case 0:
              dice[1] = d4;
              dice[3] = d1;
              dice[4] = d6;
              dice[6] = d3;
              break;

            // 서로 이동
            case 1:
                dice[1] = d3;
                dice[3] = d6;
                dice[4] = d1;
                dice[6] = d4;
                break;

            // 북으로 이동
            case 2:
                dice[1] = d2;
                dice[2] = d6;
                dice[5] = d1;
                dice[6] = d5;
                break;

            // 남으로 이동
            case 3:
                dice[1] = d5;
                dice[2] = d1;
                dice[5] = d6;
                dice[6] = d2;
                break;
        }
    }

    // 새로운 좌표가 경계 안에 존재하는지 검사하는 함수
    private static boolean withinBoundaries(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
