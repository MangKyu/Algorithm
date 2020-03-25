package Baekjoon.Quiz1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1325 {
    private static int N;
    // ArrayList를 갖는 배열을 선언한다.
    private static ArrayList<Integer>[] graph;
    private static boolean[] isVisited;
    // 해당 컴퓨터가 몇번 해킹에 참여하는지 저장한다.
    private static int[] cntArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 최대값을 0으로 초기화하고 배열을 선언한다.
        int maxCnt = 0;
        graph = new ArrayList[N+1];
        cntArr = new int[N+1];

        for(int i = 1 ; i <= N ; i++){
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
        }

        for (int i = 1; i <= N; i++) {
            // dfs를 위한 변수들을 초기화한다.
            initData();
            dfs(i);
            //bfs(i);
        }
        for (int i = 1; i <= N; i++) {
            if (cntArr[i] > maxCnt) {
                sb = new StringBuilder();
                sb.append(i).append(" ");
                maxCnt = cntArr[i];
            } else if (cntArr[i] == maxCnt) {
                sb.append(i).append(" ");
            }
        }


        System.out.println(sb.toString());
    }

    // 방문 여부를 초기화하고, 탐색 횟수를 0으로 초기화한다.
    private static void initData() {
        Arrays.fill(isVisited, false);
    }

    // dfs를 통해 탐색한다.
    private static void dfs(int index) {
        isVisited[index] = true;
        for(int i :graph[index]){
            if (!isVisited[i]) {
                cntArr[i]++;
                dfs(i);
            }
        }
    }

    private static void bfs(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);
        isVisited[index] = true;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            for (int i  : graph[num]) {
                if (!isVisited[i]) {
                    queue.offer(i);
                    cntArr[i]++;
                }
            }
        }
    }
}