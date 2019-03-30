package Quiz2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz2606 {
    // 연결 관계를 저장하는 graph 변수와 방문 여부를 저장하는 isVisited 변수
    private static int[][] graph;
    private static boolean[] isVisited;

    // 감염된 횟수를 저장하는 cnt
    private static int cnt;
    // 연결된 상태를 의미하는 상수 변수
    private static final int IS_CONNECTED = 1;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        isVisited = new boolean[N+1];
        graph = new int[N+1][N+1];
        cnt = 0;

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = IS_CONNECTED;
        }

        // 1부터 시작하여 감염가능한 노드들을 찾는다.
        dfs(1);
        //bfs(1);

        System.out.println(cnt);

    }

    /*
    * 1. 방문을 시작한 노드의 번호에 해당하는 searchIndex(노드 번호)를 방문=true로 바꾸어주고
    * 2. 모든 노드에 대해 방문 여부를 검사한다.
    * 3. 어떤 노드와 연결되어 있으며, 아직 방문을 하지 않았다면 해당 노드를 방문한다.
    */
    private static void dfs(int searchIndex){
        isVisited[searchIndex] = true;
        for(int i = 1; i <= N ; i++){
            if(graph[searchIndex][i] == IS_CONNECTED && !isVisited[i]){
                cnt++;
                dfs(i);
            }
        }
    }

    // 1부터 시작하여 연결된 그래프의 개수를 출력한다. 너비우선탐색을 위해 큐를 사용한다.
    private static void bfs(int startIndex) {
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
    }
}