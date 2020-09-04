package Baekjoon.Quiz16988;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Quiz16988 {

    private static int N, M, answer;
    private static int[][] arr;
    private static boolean[][] isVisited;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        isVisited = new boolean[N][M];
        List<Point> pointList = new ArrayList<>();
        queue = new LinkedList<>();
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    pointList.add(new Point(j, i));
                }
            }
        }

        // 비어있는 칸을 순차적으로 2개씩 1로 바꾼 후에 점령한 갯수를 검사한다.
        for (int i = 0; i < pointList.size() - 1; i++) {
            for (int j = i + 1; j < pointList.size(); j++) {
                Point point1 = pointList.get(i);
                Point point2 = pointList.get(j);
                arr[point1.y][point1.x] = 1;
                arr[point2.y][point2.x] = 1;
                // System.out.printf("(%d, %d) , (%d, %d) -> ", point1.y, point1.x, point2.y, point2.x);
                bfs();
                arr[point1.y][point1.x] = 0;
                arr[point2.y][point2.x] = 0;
            }
        }

        System.out.println(answer);
    }

    private static void bfs() {
        // bfs를 돌렸을 때 나온 정답의 갯수
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 해당 좌표를 아직 방문하지 않았으며, 상대방의 말인 경우 bfs의 queue에 추가한다.
                if (arr[i][j] == 2 && !isVisited[i][j]) {
                    queue.offer(new Point(j, i));
                    isVisited[i][j] = true;
                }

                // 해당 좌표가 점령되지 않은 경우일 수 있으므로 temp 변수에 저장하였다가 점령한 경우라면 count에 추가한다.
                int temp = 0;
                boolean isSuccess = true;
                while (!queue.isEmpty()) {
                    Point point = queue.poll();
                    // 점령한 개수를 늘려준다.
                    temp++;
                    //System.out.printf("(%d, %d)\n", point.y, point.x);

                    for (int k = 0; k < 4; k++) {
                        int newY = point.y + dy[k];
                        int newX = point.x + dx[k];

                        // 이동할 좌표가 범위 안에 있으며, 아직 방문하지 않은 경우
                        if (withinBoundaries(newY, newX) && !isVisited[newY][newX]) {
                            // 새로운 좌표가 비어있는 경우는 점령하지 못한 경우이므로 성공 여부를 false로 바꾼다.
                            if (arr[newY][newX] == 0) {
                                isSuccess = false;
                            } else if (arr[newY][newX] == 2 ) {
                                // 새로운 좌표가 상대의 돌인 경우, 방문 여부를 true로 바꾸고 큐에 추가한다.
                                Point p = new Point(newX, newY);
                                isVisited[p.y][p.x] = true;
                                queue.offer(p);
                            }
                        }
                    }
                }

                // 점령에 성공하면 최종 개수에 더해준다.
                if(isSuccess){
                    count += temp;
                }
            }

        }
        for (int k = 0; k < N; k++) {
            Arrays.fill(isVisited[k], false);
        }
        // 최대값을 answer 변수에 대입한다.
        answer = Math.max(answer, count);

    }

    private static boolean withinBoundaries(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

}