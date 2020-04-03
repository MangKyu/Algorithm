package Baekjoon.Samsung.Quiz14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz14500 {

    private static int N, M;
    private static int[][] map;
    private static int max, sum, cnt;
    private static int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    private static int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};
    private static boolean[][] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isSelected = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j);
            }
        }

        System.out.println(max);

    }

    private static void dfs(int y, int x) {
        cnt++;
        sum += map[y][x];
        isSelected[y][x] = true;
        if (cnt == 4) {
            max = Math.max(max, sum);
        } else {
            // 한 점을 기준으로 8방향을 검색한다.
            for (int i = 0; i < 8; i++) {
                // 이동할 좌표가 경계 안에 있고, 선택되지 않았으며
                if (withinBoundaries(y + dy[i], x + dx[i]) && !isSelected[y + dy[i]][x + dx[i]]){
                    // 상하좌우의 점과 연결된 경우
                    if (isConnected(y + dy[i], x + dx[i])) {
                        // dfs로 탐색을 진행한다.
                        dfs(y + dy[i], x + dx[i]);
                    }
                }
            }
        }
        cnt--;
        sum -= map[y][x];
        isSelected[y][x] = false;
    }

    // 해당 좌표가 경계 안에 존재하는지 검사하는 함수
    private static boolean withinBoundaries(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    // 상하 좌우에 연결된 정사각형이 있는지 검사하는 함수
    private static boolean isConnected(int y, int x) {
        int newY, newX;
        for (int i = 0; i < 4; i++) {
            newY = y + dy[i];
            newX = x + dx[i];
            if(withinBoundaries(newY, newX) && isSelected[newY][newX]){
                return true;
            }
        }
        return false;
    }

}
