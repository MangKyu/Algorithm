package Baekjoon.Quiz2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz2573 {

    private static int N, M;
    private static int[][] map, meltCnt;
    private static boolean[][] isVisited;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        meltCnt = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        while (true) {
            // 빙산을 주어진 방법에 따라 녹인다.
            melt();
            // 1년을 증가시킨다.
            year++;
            // 빙산 조각의 개수를 검사한다.
            int cnt = countIceberg();
            System.out.println(cnt);
            // 빙산의 조각이 1개보다 많으면 반복문을 종료하고, 결과를 출력한다.
            if (cnt > 1) {
                break;

                // 빙산의 조각이 0개이면 0으로 초기화하고 종료한다.
            } else if (cnt == 0) {
                year = 0;
                break;
            } else {
                // 빙산이 1개면 반복문을 더 진행해야하므로 변수들을 초기화한다.
                init();
            }
        }

        System.out.println(year);

    }

    // 빙산을 조건에 따라 녹인다.
    private static void melt() {
        int newX, newY;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 해당 지역의 높이가 0보다 큰 경우
                if (map[i][j] > 0) {
                    // 상하좌우 좌표를 검사하여
                    for (int k = 0; k < 4; k++) {
                        newX = j + dx[k];
                        newY = i + dy[k];
                        // 새로운 좌표가 경계 안에 있고, 0이라면 줄여야하는 높이의 개수를 1 높인다.
                        if (withinBoundaries(newY, newX) && map[newY][newX] == 0) {
                            meltCnt[i][j]++;
                        }
                    }
                }
            }
        }

        // 검사를 끝낸 후 빙산을 녹인다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    map[i][j] -= meltCnt[i][j];
                }
            }
        }
    }

    // 빙산의 개수를 새는 함수
    private static int countIceberg() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 해당 높이가 0보다 크고, 아직 방문하지 않았다면
                if (map[i][j] > 0 && !isVisited[i][j]) {
                    // 개수를 1 높이고, dfs로 탐색을 진행한다.
                    cnt++;
                    dfs(i, j);
                }
            }
        }

        return cnt;
    }

    // dfs로 0보다 큰 높이의 빙산을 연속해서 탐색한다.
    private static void dfs(int y, int x) {
        // 해당 좌표의 방문 여부를 true로 바꾼다.
        isVisited[y][x] = true;
        for (int k = 0; k < 4; k++) {
            int newX = x + dx[k];
            int newY = y + dy[k];
            // 상하좌우를 계산하여 높이가 0보다 크고, 아직 방문하지 않은 경우, 해당 좌표의 방문을 진행한다.
            if (withinBoundaries(newY, newX) && map[newY][newX] > 0 && !isVisited[newY][newX]) {
                dfs(newY, newX);
            }
        }
    }

    // 변수들을 초기화하는 함수
    private static void init() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(meltCnt[i], 0);
            Arrays.fill(isVisited[i], false);
        }
    }

    // 해당 좌표가 경계 안에 존재하는지 검사하는 함수
    private static boolean withinBoundaries(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

}
