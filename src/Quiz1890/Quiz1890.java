package Quiz1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz1890 {
    // 우 또는 하로 가는 dx, dy를 미리 만들어둔다.
    private static int[] dx = {1, 0};
    private static int[] dy = {0, 1};

    // 배열의 크기를 나타내는 N
    private static int N;

    // 그래프를 담고있는 변수
    private static int[][] graph;
    // 방문에 대한 dp값을 저장하는 배열
    private static long[][] dp;

    // 아직 방문하지 않았음을 나타내는 상수
    private static final int NOT_VISITED = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        dp = new long[N + 1][N + 1];

        // 미방문 상태로 모든 배열의 값을 초기화한다.
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], NOT_VISITED);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                // 배열을 초기화해준다.
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(1, 1));
    }

    private static long dfs(int y, int x) {
        // 만약 꼭지점이 아니라면 다른 노드로 방문을 탐색한다.
        if (y != N || x != N) {
            // 이미 방문을 한 상태라면 이전에 방문했을대의 결과를 반환한다.
            if (dp[y][x] != NOT_VISITED) {
                return dp[y][x];
            }

            // 방문을 한 상태로 변경해준다.
            dp[y][x] = 0;

            for (int i = 0; i < 2; i++) {
                int num = graph[y][x];
                // 이동할 좌표가 범위 내에 있다면 dfs를 통해 탐색한다.
                if (withinBoundaries(y + num * dy[i], x + num * dx[i]) && num != 0) {
                    dp[y][x] += dfs(y + num * dy[i], x + num * dx[i]);
                }
            }

            //계산을 통해 얻은 방문값을 반환한다.
            return dp[y][x];
        } else {
            // 목적지에 도착하면 1을 반환한다.
            return 1;
        }
    }

    // 목적 y, x가 경계내에 존재하는지 검사하는 함수
    private static boolean withinBoundaries(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }
}