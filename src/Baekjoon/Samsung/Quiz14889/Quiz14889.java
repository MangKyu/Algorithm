package Baekjoon.Samsung.Quiz14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz14889 {

    // 팀의 크기를 나타내는 정수형 N
    private static int N;
    // 능력치 그래프를 나타내는 변수
    private static int[][] graph;
    // true, false 팀으로 나뉘어지는 팀 변수
    private static boolean[] team;
    // true 팀의 숫자와 최소 값(정답)을 저장하는 변수
    private static int cnt, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        team = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 정답 변수를 Integer의 최대값으로 초기화한다.
        answer = Integer.MAX_VALUE;
        cnt = 0;

        // 1번부터 dfs로 중복되지 않게 팀을 정한다.
        selectTeam(1);

        System.out.println(answer);
    }

    // 재귀를 사용해 팀을 중복되지 않게 선택하는 함수
    private static void selectTeam(int index) {
        if (cnt < N / 2) {
            for (int i = index; i <= N; i++) {
                team[i] = true;
                cnt++;
                selectTeam(i + 1);
                team[i] = false;
                cnt--;
            }
        } else {
            // true팀의 수와 false 팀의 수가 같아지면 차이를 비교하고, 최소값을 구한다.
            answer = Math.min(answer, calculateSum());
        }

    }

    // true 팀과 false팀의 합을 구해 차이를 계산하는 함수
    private static int calculateSum(){
        int team1Sum = 0;
        int team2Sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if(team[i] && team[j]){
                    team1Sum += graph[i][j];
                    team1Sum += graph[j][i];
                } else if(!team[i] && !team[j]){
                    team2Sum += graph[i][j];
                    team2Sum += graph[j][i];
                }
            }
        }
        return Math.abs(team1Sum - team2Sum);
    }

}
