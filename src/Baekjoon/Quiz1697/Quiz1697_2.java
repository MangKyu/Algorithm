package Baekjoon.Quiz1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

@SuppressWarnings("Duplicates")
public class Quiz1697_2 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        bfs(start, end);
    }

    private static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[100000 + 1];
        visited[start] = 0;
        q.offer(start);

        while (!q.isEmpty()) {
            int idx = q.poll();
            //visited[idx] = visited[idx] + 1;
            //visited[idx] = 1;
            //System.out.println(idx);
            if (idx == end) {
                System.out.println(visited[end]);
                return;
            }

            if (idx - 1 >= 0 && visited[idx - 1] == 0) {
                visited[idx - 1] = visited[idx] + 1;
                q.offer(idx - 1);
            }

            if (idx + 1 <= 100000 && visited[idx + 1] == 0) {
                visited[idx + 1] = visited[idx] + 1;
                q.offer(idx + 1);
            }

            if (idx * 2 <= 100000 && visited[idx * 2] == 0) {
                visited[idx * 2] = visited[idx] + 1;
                q.offer(idx * 2);
            }
        }
    }
}
