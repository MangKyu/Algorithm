package Baekjoon.Quiz11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11403 {
    private static int N;

    // 입력으로 부터 받은 연결 지도
    private static int[][] map;
    // 연결상태를 표현하기 위한 지도
    private static int[][] connectedMap;

    // src에서 dst로 방문 했는지를 저장
    private static boolean[][] isVisited;
    public static final int IS_CONNECTED = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        connectedMap = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            // y값을 기준으로 순회를 하는데, isVisited를 초기화해준다.
            isVisited = new boolean[N+1][N+1];
            dfs(i, i);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(connectedMap[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // srcIndex는 연결 여부를 검사하기 시작한 점이다. dstIndex는 dfs를 돌면서 계속 연결상태를 찾아나가는 점이다.
    // 시작점으로부터 해서 1~N까지의 모든 노드에 대해 dfs를 수행검사한다. src로부터 시작해서 dfs를 돌면 해당 src는 dst에 해당하는 i는 연결되어있다고 파악할 수 있다
    private static void dfs(int srcIndex, int dstIndex){
        for(int i = 1 ; i <= N ; i++){
            if(map[dstIndex][i] == IS_CONNECTED && !isVisited[dstIndex][i]){
                connectedMap[srcIndex][i] = IS_CONNECTED;
                isVisited[dstIndex][i] = true;
                dfs(srcIndex, i);
            }
        }
    }
}