package Quiz1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz1260_2 {

    // 두 정점 사이의 연결 여부를 나타내는 상수
    private static int IS_CONNECTED = 1;
    // dfs와 bfs를 거치면서 나온 출력결과를 버퍼에 넣어두고 한번에 출력한다.
    private static StringBuilder sb;
    // 두 정점 사이의 연결 여부를 지닌 그래프
    private static int[][] graph;
    // 특정 정점의 방문여부를 나타내는 배열
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();

        // 배열의 시작을 0이 아닌 1부터 해주기 위해 N+1 크기의 배열을 선언해준다.
        graph = new int[N+1][N+1];
        isVisited = new boolean[N + 1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 두 점이 연결되어있다고 초기화해준다.
            graph[x][y] = graph[y][x] =IS_CONNECTED;
        }

        // 깊이 우선 탐색을 수행한다.
        dfs(V);

        // DFS를 수행한 결과가 저장되어있으므로 StringBuilder에 개행을 추가한다.
        sb.append("\n");
        // BFS를 수행하기 위해 방문 여부를 나타내는 배열을 모두 False로 초기화한다.
        Arrays.fill(isVisited, false);

        // 너비 우선 탐색을 수행한다.
        bfs(V);

        System.out.println(sb.toString());

    }

    /*
    * 1) 너비 우선 탐색은 시작점을 먼저 방문하고 시작점의 인접한 노드를 먼저 검사한다
    * 2) 인접 노드를 검사하고, 해당 노드와 연결된 노드를 검사한다.
    * 3) 그리고 해당 노드가 방문하지 않았다면 그 노드를 먼저 탐색하기 위해 dfs 함수를 호출한다.
    * 4) 이러한 과정을 통해 깊이 우선 탐색을 진행한다.
    * */
    private static void dfs(int index){
        // 1번 과정
        isVisited[index] = true;
        sb.append(index).append(" ");
        // 2번과정
        for(int i = 1; i < graph[0].length; i++){
            // 3번, 4번 과정
            if(graph[index][i] == IS_CONNECTED && !isVisited[i]){
                dfs(i);
            }
        }
    }

    /*
    * 1) 깊이 우선 탐색은 해당 노드의 인접한 노드들만 먼저 탐색하므로 재귀를 사용하지 않는다.
    * 2) 먼저 시작점을 큐에 넣고, 시작점을 방문 처리 해준다.
    * 3) 시작점과 인점한 정점 중에서 방문하지 않은 정점들을 모두 탐색하여 큐에 넣는다.
    * 4) 위의 과정은 시작점 V에 인점한 노드들을 모두 넣어둠으로써 너비 우선 탐색을 진행함을 의미한다.
    * 4) 그리고 시작점의 인접 노드 검사가 끝나면 먼저 큐에 들어간 노드를 꺼내게 된다.
    * 5) 그리고 해당 노드의 인접 노드들 중 방문하지 않은 노드를 먼저 검사하여 너비 우선 탐색을 진행한다.
    * */

    private static void bfs(int index){
        Queue<Integer> queue = new LinkedList<>();

        // 2번, 4번 과정
        queue.offer(index);
        isVisited[index] = true;

        while(!queue.isEmpty()){
            int num = queue.poll();
            sb.append(num).append(" ");

            // 3번 과정
            for(int i = 1 ; i < graph[0].length; i++){
                if(graph[num][i] == IS_CONNECTED && !isVisited[i]){
                    isVisited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
}