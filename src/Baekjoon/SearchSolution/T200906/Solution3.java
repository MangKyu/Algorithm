package Baekjoon.SearchSolution.T200906;

public class Solution3 {

    private int N, M;
    private boolean[][] isVisited;
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {-1, 0, 1, 0};
    private int[][] A;

    public int solution(int[][] A) {
        N = A.length;
        M = A[0].length;
        int answer = 0;
        isVisited = new boolean[N][M];
        this.A = A;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!isVisited[i][j]){
                    answer++;
                    dfs(i, j);
                }
            }
        }

        return answer;
    }

    private void dfs(int y, int x){
        isVisited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];
            if(withinBoundaries(newY, newX) && !isVisited[newY][newX] && A[newY][newX] == A[y][x]){
                //System.out.println(newY + " " + newX);
                dfs(newY, newX);
            }
        }

    }

    private boolean withinBoundaries(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

}
