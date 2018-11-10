import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Quiz1260 {
    //http://javannspring.tistory.com/174
    //https://www.acmicpc.net/problem/1260

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int V;
        int E;
        int startPoint;
        boolean visited[];
        V = s.nextInt();
        E = s.nextInt();
        startPoint = s.nextInt();

        int graph[][] = new int[V + 1][V + 1];

        for (int i = 1; i <= E; i++) {
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            graph[v1][v2] = graph[v2][v1] = 1;
        }

        visited = new boolean[V + 1];
        dfs(graph, visited, startPoint);
        System.out.println("");
        visited = new boolean[V + 1];
        bfs(graph, visited, startPoint);

    }

    public static void dfs(int graph[][], boolean visited[], int startPoint) {
        System.out.print(startPoint + " ");
        visited[startPoint] = true;
        for (int i = 1; i < graph[0].length; i++) {
            if (graph[startPoint][i] == 1 && visited[i] == false) {
                dfs(graph, visited, i);
            }
        }
    }

    public static void bfs(int graph[][], boolean visited[], int startPoint) {
        Queue<Integer> q = new LinkedList<>();
        visited[startPoint] = true;
        q.add(startPoint);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int i = 1; i < graph[0].length; i++) {
                if (graph[node][i] == 1 && visited[i] == false){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
