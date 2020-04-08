package Baekjoon.Quiz2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz2468 {

    private static int N;
    private static int[][] map;
    private static boolean[][] isVisited;
    private static int answer, cnt, maxHeight;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        // 최대 높이까지를 잠식시킨다.
        for(int i = 0 ; i <= maxHeight ; i++){
            // 초기화 함수와 잠식시키는 함수를 호출한다.
            init();
            immerseArea(i);
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    // 어떤 좌표를 방문하지 않은 경우 cnt를 1개 늘리고, dfs로 탐색을 진행한다.
                    if(!isVisited[j][k]){
                        cnt++;
                        dfs(j, k);
                    }
                }
            }
            // answer변수는 answer와 cnt중 최대값을 취한다.
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }

    // dfs로 지역을 탐색한다.
    private static void dfs(int y, int x){
        isVisited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            if(withinBoundaries(y + dy[i], x + dx[i]) && !isVisited[y + dy[i]][x + dx[i]]){
                dfs(y + dy[i], x + dx[i]);
            }
        }
    }

    // 변수들을 초기화한다.
    private static void init(){
        cnt = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(isVisited[i], false);
        }
    }

    // 해당 높이보다 낮은 값은 잠식시킨다.(방문 상태로 바꾼다)
    private static void immerseArea(int height){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] <= height){
                    isVisited[i][j] = true;
                }
            }
        }
    }

    // 해당 좌표가 경계 안에 존재하는지 검사하는 함수
    private static boolean withinBoundaries(int y, int x){
        return y < N && y >= 0 && x < N && x >= 0;
    }

}
