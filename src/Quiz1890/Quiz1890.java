package Quiz1890;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz1890 {
    //FIXME BFS로 풀면 메모리 초과가 난다. 이런 이유로 기존에는 그래프 문제였는데, 현재는 DP문제로 변경됨!

    // 우 또는 히로 가는 dx, dy를 미리 만들어둔다.
    private static int[] dx = {1, 0};
    private static int[] dy = {0, 1};
    private static int N;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                // 배열을 초기화해준다.
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    private static long bfs() {
        long cnt = 0;
        Queue<Point> queue = new LinkedList<>();

        //0, 0부터 탐색을 시작한다.
        queue.offer(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            // num은 해당 x, y가 점프할 칸의 수이다.
            int num = graph[point.x][point.y];

            //만약 도착했다면 cnt를 높여준다.
            if (point.x == N - 1 && point.y == N - 1) {
                cnt++;
            } else {
                //num이 0인 경우에는 이동할 수 없으므로 num != 0인 경우만 점프한다.
                if (num != 0) {
                    // dx, dy를 사용하여 이동하는데, * num을 하여 그만큼 이동하게 한다. i==0일때는 우로 num, i==1일때는 하로 num
                    for (int i = 0; i < 2; i++) {
                        //System.out.println("X: " + point.x + num * dx[i] + "    Y: " + point.y + num * dy[i]);

                        if (point.x + num * dx[i] < N && point.y + num * dy[i] < N) {
                            queue.offer(new Point(point.x + num * dx[i], point.y + num * dy[i]));
                        }
                    }

                }
            }
        }

        return cnt;
    }

}