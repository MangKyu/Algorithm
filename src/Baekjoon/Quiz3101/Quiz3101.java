package Baekjoon.Quiz3101;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Quiz3101 {
    private static HashMap<Point, Integer> pointHash;
    private static long[][] graph;
    private static int N;
    private static int graphX, graphY;
    private static long num;
    private static boolean isUp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());



        graph = new long[N + 1][N + 1];
        graphX = 1;
        graphY = 1;
        num = 1;
        graph[graphY][graphX] = num;
        isUp = false;

        pointHash = new HashMap<>();

        long score = 1;
        int x = 1;
        int y = 1;

        //initGraph();
        char[] inputs = br.readLine().toCharArray();

        for (char input : inputs) {
            switch (input) {
                case 'D':
                    y++;
                    break;
                case 'R':
                    x++;
                    break;
                case 'U':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
            }
            retrieveGraph(y, x);
            score += graph[y][x];
        }
        System.out.println(score);

    }

    private static void retrieveGraph(int y, int x) {
        // 해당 그래프가 상향중인지, 하향중인지를 알려준다.


        while (graph[y][x] == 0) {
            graph[graphY][graphX] = num++;
            //System.out.printf("graph[%d][%d]: %d \n", y, x, num);
            graph[N + 1 - graphY][N + 1 - graphX] = N * N + 1 - num;
            // graphX, graphY가 1, 1일때는 오른쪽으로 이동, 상향중이 아니다.
            if (graphX == 1 && graphY == 1) {
                graphX++;
                isUp = false;

                // graphX의 좌표가 1인경우 즉, 왼쪽 벽에 부딪힌 경우
            } else if (graphX == 1) {

                //하향중이 아니라면 오른쪽 대각선 위로 이동
                if (isUp) {
                    graphX++;
                    graphY--;
                    //하향중이라면 한칸 내려가야 하므로 아래로 이동
                } else {
                    graphY++;
                }
                isUp = true;

                // graphY의 좌표가 1인경우 즉, 위쪽 벽에 부딪힌 경우
            } else if (graphY == 1) {
                // 상향중이라면 오른쯕으로 이동
                if (isUp) {
                    graphX++;

                    // 상향중인 상태가 아니라면 우하향
                } else {
                    graphX--;
                    graphY++;
                }
                isUp = false;

                // 경계에 부딪히는 좌표가 아닌 경우에는 상향/하향에 따라 값을 다르게 처리한다.
            } else {
                if (isUp) {
                    graphX++;
                    graphY--;
                } else {
                    graphX--;
                    graphY++;
                }
            }

            // 값을 1 증가시켜준다.
            //num++;

        }
    }

    // 그래프를  초기화하는 함수로 그래프의 중간까지 탐색하는데, 대칭인 특성을 이용하여 동시에 2개씩 초기화한다.
    private static void initGraph() {
        //해당 그래프의 좌표와 값을 갖는다.
        num = 1;
        graphX = 1;
        graphY = 1;

        // 해당 그래프가 상향중인지, 하향중인지를 알려준다.
        boolean isUp = false;

        //graph의 크기가 1인경우도 있으므로 미리 할당해준다.
        graph[graphX][graphY] = num;

        while (num != ((N * N) / 2 + 1)) {
            //SgraphYstem.out.printf("graph[%d][%d]: %d \n", graphY, graphX, num);
            graph[graphY][graphX] = num;
            graph[N + 1 - graphY][N + 1 - graphX] = N * N + 1 - num;
            // graphX, graphY가 1, 1일때는 오른쪽으로 이동, 상향중이 아니다.
            if (graphX == 1 && graphY == 1) {
                graphX++;
                isUp = false;

                // graphX의 좌표가 1인경우 즉, 왼쪽 벽에 부딪힌 경우
            } else if (graphX == 1) {

                //하향중이 아니라면 오른쪽 대각선 위로 이동
                if (isUp) {
                    graphX++;
                    graphY--;
                    //하향중이라면 한칸 내려가야 하므로 아래로 이동
                } else {
                    graphY++;
                }
                isUp = true;

                // graphY의 좌표가 1인경우 즉, 위쪽 벽에 부딪힌 경우
            } else if (graphY == 1) {
                // 상향중이라면 오른쯕으로 이동
                if (isUp) {
                    graphX++;

                    // 상향중인 상태가 아니라면 우하향
                } else {
                    graphX--;
                    graphY++;
                }
                isUp = false;

                // 경계에 부딪히는 좌표가 아닌 경우에는 상향/하향에 따라 값을 다르게 처리한다.
            } else {
                if (isUp) {
                    graphX++;
                    graphY--;
                } else {
                    graphX--;
                    graphY++;
                }
            }

            // 값을 1 증가시켜준다.
            num++;
        }

        // N이 홀수인 경우에 중간값이 채워지지 않으므로 채워준다.
        if (N % 2 == 1) {
            graph[N / 2 + 1][N / 2 + 1] = N * N / 2 + 1;
        }
        graphX = 1;
        graphY = 1;
        num = 1;
    }
}