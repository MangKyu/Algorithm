package Baekjoon.Quiz2178;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz2178 {
    static int N, M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][M + 1];
        int[][] visited = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }
        bfs(map, visited);

        System.out.println(visited[N][M]);

    }

    private static void bfs(int[][] map, int[][] visited) {
        Queue<Point> q = new LinkedList<>();
        visited[1][1] = 1;
        q.offer(new Point(1, 1));
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int sx = p.x + dx[i];
                int sy = p.y + dy[i];
                if (sx > 0 && sx <= N && sy > 0 && sy <= M && map[sx][sy] == 1) {
                    if (visited[sx][sy] == 0 || visited[sx][sy] > visited[p.x][p.y] + 1) {
                        visited[sx][sy] = visited[p.x][p.y] + 1;
                        //System.out.println(sx + "   " + sy + "   " + visited[sx][sy]);
                        q.offer(new Point(sx, sy));
                    }

                    if (sx == N && sy == M) {
                        visited[sx][sy] = visited[p.x][p.y] + 1;
                        return;
                    }
                }
            }
        }
    }
}