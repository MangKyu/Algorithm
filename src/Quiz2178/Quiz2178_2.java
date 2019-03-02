package Quiz2178;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz2178_2 {

    // 상하좌우로 이동하기 위한 x, y 변수
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 입력을 저장하는 그래프
    private static String[][] graph;
    // 이동 가능 여부를 나타내는 변수
    private static final String isEnable = "1";
    // 방문 횟수를 저장하는 변수
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new String[N][M];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                graph[i][j] = inputs[j];
            }
        }

        // 마지막 N-1, M-1의 값을 출력한다.
        System.out.println(bfs());
    }

    private static int bfs() {
        // 방문 횟수를 저장하는 변수
        int[][] visited = new int[N][M];
        Queue<Point> queue = new LinkedList<>();

        // 0,0 부터 시작하고, 0,0의 방문 횟수를 1로 초기화
        queue.offer(new Point(0, 0));
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            // 상하좌우를 검사
            for (int i = 0; i < 4; i++) {
                //먼저 범위안에 포함되는지 검사
                if (point.x + dx[i] >= 0 && point.x + dx[i] < N && point.y + dy[i] >= 0 && point.y + dy[i] < M) {
                    // 해당 칸이 이동가능하고, 방문한 적이 없는지 검사(중복 방문을 하면 무한루프에 빠질 수 있음)
                    if (graph[point.x + dx[i]][point.y + dy[i]].equals(isEnable) && visited[point.x + dx[i]][point.y + dy[i]] == 0) {
                        visited[point.x + dx[i]][point.y + dy[i]] = visited[point.x][point.y] + 1;
                        queue.offer(new Point(point.x + dx[i], point.y + dy[i]));
                    }
                }
            }
        }

        return visited[N-1][M-1];
    }
}
