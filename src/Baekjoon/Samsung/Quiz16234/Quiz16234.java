package Baekjoon.Samsung.Quiz16234;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz16234 {
    // 그래프의 크기와 인구 범위값을 계산하기 위한 변수
    private static int N, L, R;

    // 상하좌우로 이동을 위한 변수
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    // 인구상태를 나타내는 graph와 점들의 방문여부를 나타내는 변수
    private static int[][] graph;
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = 0;

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        isVisited = new boolean[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 끝났는지를 검사하고, 끝나지 않았다면 bfs를 통해 인구이동을 수행한다.
        while(!isFinish()){
            for(int i = 1 ; i <= N ; i++){
                for(int j = 1;  j <= N ; j++){
                    if(!isVisited[i][j]){
                        bfs(i, j);
                    }
                }
            }

            // 한번 전체 루프가 끝났으므로 방문여부 변수를 초기화한다.
            for(int i = 1 ; i <= N ; i++){
                Arrays.fill(isVisited[i], false);
            }

            // 방문 횟수를 1 증가시킨다.
            cnt++;
        }

        System.out.println(cnt);
    }

    // 해당 그래프가 더 이상의 인구이동이 존재하지 않는지 판단하는 함수
    private static boolean isFinish(){
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1;  j <= N ; j++){
                for(int k = 0 ; k < 4; k++){
                    if(isMovable(i, j, k)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // bfs를 사용하여 주변 노드를 탐색하는 함수
    private static void bfs(int y, int x){
        Queue<Point> pointQueue = new LinkedList<>();
        ArrayList<Point> pointList = new ArrayList<>();
        int sum = 0;

        pointQueue.offer(new Point (x, y));
        isVisited[y][x] = true;

        while(!pointQueue.isEmpty()){
            Point point = pointQueue.poll();
            pointList.add(point);
            sum += graph[point.y][point.x];

            //System.out.println("x: "+ point.x + "    y: "+ point.y + "    g: " + graph[point.y][point.x]);

            for (int i = 0; i < 4; i++) {
                if (isMovable(point.y, point.x, i) && !isVisited[point.y + dy[i]][point.x + dx[i]]) {
                    isVisited[point.y + dy[i]][point.x + dx[i]] = true;
                    pointQueue.offer(new Point(point.x + dx[i], point.y + dy[i]));
                }
            }
        }

        // 인구이동을 수행한다.
        for(Point point : pointList){
            graph[point.y][point.x] = sum / pointList.size();
        }

    }

    // 해당 좌표가 경계안에 있고, 이동가능한지 판단하는 함수
    private static boolean isMovable(int y, int x, int i){
        return withinBoundaries(y + dy[i], x + dx[i]) && (Math.abs(graph[y + dy[i]][x + dx[i]] - graph[y][x]) >= L && Math.abs(graph[y + dy[i]][x + dx[i]] - graph[y][x]) <= R);
    }

    // 목적 y, x가 경계내에 존재하는지 검사하는 함수
    private static boolean withinBoundaries(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }

}

/* 반례 모음
4 1 9
96 93 74 30
60 90 65 96
5 27 17 98
10 41 46 20
=> 1


5 1 5
88 27 34 84 9
40 91 11 30 81
2 88 65 26 75
75 66 16 14 28
89 10 5 30 75
=> 1

* */