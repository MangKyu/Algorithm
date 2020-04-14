package Baekjoon.Quiz5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz5014 {

    private static int F, S, G, U, D;
    // 해당 칸까지 이동하는데 클릭한 버튼의 개수를 저장하는 변수
    private static int[] buttonCnt;
    // 해당 칭의 방문 여부를 저장하는 변수
    private static boolean[] isVisited;
    private static Queue<Integer> queue;

    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        buttonCnt = new int[F + 1];
        isVisited = new boolean[F + 1];

        queue = new LinkedList<>();
        System.out.println(bfs(S));
    }

    private static String bfs(int start){
        isVisited[start] = true;
        queue.offer(start);
        int temp;

        while (!queue.isEmpty()){
            Integer num = queue.poll();
            if(num.equals(G)){
                return String.valueOf(buttonCnt[num]);
            }

            // 위로 이동하는 버튼을 누른 경우에 해당 층수를 저장한다.
            temp = num + U;
            // 위로 이동하면 범위 안에 존재하고, 방문하지 않았으면 방문한다.
            if(temp <= G && !isVisited[temp]){
                visit(temp, num);
            }

            // 아래로 이동하는 버튼을 누른 경우에 해당 층수를 저장한다.
            temp = num - D;
            // 아래로 이동하면 범위 안에 존재하고, 방문하지 않았으면 방문한다.
            if(num - D >= 1 && !isVisited[temp]){
                visit(temp, num);
            }
        }

        return "use the stairs";
    }

    // dst에 해당하는 층을 방문한다.
    // 이전 층에 누적된 버튼 입력횟수에 +1을 하고, queue에 넣고, 방문 여부룰 true로 바꾸어준다.
    private static void visit(int dst, int src){
        queue.offer(dst);
        buttonCnt[dst] += buttonCnt[src] + 1;
        isVisited[dst] = true;
    }

}
