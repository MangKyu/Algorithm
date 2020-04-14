package Baekjoon.Quiz2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2644 {

    // 사람의 수 N, 정답, 찾아야 하는 번호를 저장하는 변수
    private static int N, answer, end;
    // parent[자식]=부모 의 형태로, 부모와 자식의 관계를 표현하는 배열
    private static int[] parent;
    // 방문 여부를 저장하는 변수
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        isVisited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        int p, c;
        while(M --> 0 ){
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            parent[c] = p;
        }
        answer = -1;
        dfs(start, 0);
        System.out.println(answer);

    }

    // 탐색을 시작하는 index와 시작점과 해당 index와의 거리를 계산하는 cnt 변수를 매개변수로 받는다.
    private static void dfs(int index, int cnt){
        isVisited[index] = true;

        if(index == end){
            answer = cnt;
        } else {
            for (int i = 1; i <= N; i++) {
                // 만약 해당 인덱스가 어떤 자식의 부모이거나 해당 인덱스의 부모를 만난 경우 dfs를 돌린다.
                if(parent[i] == index && !isVisited[i]){
                    dfs(i, cnt + 1);
                } else if (parent[index] == i && !isVisited[i]){
                    dfs(i, cnt + 1);
                }
            }
        }
    }

}
