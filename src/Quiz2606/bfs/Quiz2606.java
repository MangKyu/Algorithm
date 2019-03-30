package Quiz2606.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz2606 {

    private static int[][] graph;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 배열의 시작으로 0이 아닌 1로 하기 위해 배열의 크기를 N+1로 해준다.
        graph = new int[N + 1][N + 1];
        isVisited = new boolean[N + 1];

        // 방문 여부를 false로 초기화한다.
        Arrays.fill(isVisited, false);

        // M개에 대해서 그래프를 초기화한다.
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;
        }

        System.out.println(bfs(1));


    }

    // 1부터 시작하여 연결된 그래프의 개수를 출력한다. 너비우선탐색을 위해 큐를 사용한다.
    private static int bfs(int startIndex) {
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIndex);

        while (!queue.isEmpty()) {
            int num = queue.poll();
            isVisited[num] = true;

            // num과 인접하며, 방문하지 않은 노드들에 대해서는 방문하고, 감염되었다는 cnt를 1 증가시킨다.
            for (int i = 1; i < graph.length; i++) {
                if (graph[num][i] == 1 && !isVisited[i]) {
                    isVisited[i] = true;
                    queue.offer(i);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}