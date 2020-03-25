package Baekjoon.Quiz11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11724 {

    // 숫자를 저장하고 있는 배열
    private static int[] numArr;
    // 방문 여부를 저장중인 변수
    private static boolean[] isVisited;
    // 연결 관계를 나타내는 그래프
    private static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 변수들을 초기화한다.
        numArr = new int[N + 1];
        isVisited = new boolean[N + 1];
        graph = new boolean[N + 1][N + 1];

        // 연결 요소의 개수를 나타내는 변수
        int cnt = 0;

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y] = graph[y][x] = true;
        }

        // 변수들에 값을 대입하여 초기화한다.
        for(int i = 1 ; i < numArr.length ; i++) {
            numArr[i] = i;
        }

        // 1부터 시작해서 끝까지 탐색하여 연결 요소를 검사한다.
        for(int i = 1 ; i < numArr.length ; i++){
            // i를 방문하지 않은 경우 해당 i에 대해 dfs를 통해 연결 요소를 검사한다.
            if(!isVisited[i]){
                dfs(i);
                // 연결 요소의 개수를 1 늘려준다.
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void dfs(int index){
        // index를 방문하지 않은 경우
        if(!isVisited[index]){
            isVisited[index] = true;
            for(int i = 1 ; i < numArr.length ; i++){
                // i를 방문하지 않았고, index와 i 가 연결된 경우 i로 재귀를 호출한다.
                if(!isVisited[i] && graph[i][index]){
                    dfs(i);
                }
            }
        }
    }

}
