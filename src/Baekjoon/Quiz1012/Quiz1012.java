package Baekjoon.Quiz1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1012 {

    // 상하좌우로 이동하기 위한 x, y 변수
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    // 배추 정보를 저장하는 그래프
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 만큼 입력을 받고 실행한다.
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            graph = new int[N][M];

            // 벌레의 개수를 저장하는 변수
            int cnt = 0;
            // 배추가 심어진 위치의 개수
            int k = Integer.parseInt(st.nextToken());

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[y][x] = 1;
            }

            // 모든 [y][x]에 대해 검사를 하여 1인 경우 dfs를 실행한다.
            for (int y = 0; y < graph.length; y++) {
                for (int x = 0; x <graph[0].length; x++){
                    if(graph[y][x] == 1){
                        cnt++;
                        dfs(y, x);
                    }
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    // 1인 y, x에 대해 dfs를 실행한다. 0인 경우는 dfs를 호추랗지 않는경우이다.
    private static void dfs(int y, int x) {
        // dfs를 실행한 점(1을 값으로 지님)에 대해 1을 감소시켜 dfs 중복검사를 동시에 처리한다.
        graph[y][x]--;

        // 상하좌우의 x와 y에 대해 범위 안에 있고, 값이 1이라면 dfs를 통해 주변을 탐색한다.
        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];

            if (newY >= 0 && newY < graph.length && newX >= 0 && newX < graph[0].length) {
                if (graph[newY][newX] == 1) {
                    dfs(newY, newX);
                }
            }
        }
    }

}
