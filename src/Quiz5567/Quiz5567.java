package Quiz5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz5567 {

    // 동기의 수를 저장하는 변수
    private static int N;
    // 동기들의 번호를 기준으로 연결 관계를 나타내는 그래프
    private static boolean[][] graph;
    // 해당 동기를 탐색했는지 여부를 저장하는 변수로 탐색을 했다면 초대된 것
    private static boolean[] isVisited;
    // 결혼식에 초대하는 동기의 수
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        cnt = 0;
        graph = new boolean[N + 1][N + 1];
        isVisited = new boolean[N + 1];

        // 입력을 저장한다.
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = true;
        }

        invitePeople(1);

        for(int i = 2 ; i <= N ; i++){
            if(isVisited[i]){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    // 초대가능한 사람의 수를 세는 함수, index는 탐색하는 동기 번호, isEnable은 재귀를 돌릴지 결정
    // 1의 친구인 경우에는 친구의 친구까지 초대 가능하므로 isEnable이 true이고, 그렇지않으면 false를 넣어서 재귀를 호출하지 않는다.
    private static void invitePeople(int index){
        for(int i = 2 ; i <= N ; i++){
            if(graph[i][index]){
                //System.out.println(index + " "  + i);
                isVisited[i] = true;

                if(index == 1){
                    invitePeople(i);
                }
            }
        }
    }
}