package Baekjoon.Quiz10451;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz10451 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력 결과를 만들기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            // 배열의 인덱스를 0이 아닌 1부터 시작하기 위해 배열의 크기를 N+1로 해준다.
            // 1, 2, 3 ... N에 해당하는 인덱스가 어떤값을 가지고 있는지를 저장
            int[] cycleArr = new int[N + 1];

            // 싸이클을 돌며 방문한 경우에는 이후에 재방문을 할 필요가 없으므로 방문 여부를 저장
            boolean[] isVisited = new boolean[N + 1];
            // 초기에는 아무곳도 방문하지 않았다고 초기화한다.
            Arrays.fill(isVisited, false);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                // 1 2 3 4 인덱스에 해당하는 번호를 초기화한다.
                // 2 4 3 1
                cycleArr[j] = Integer.parseInt(st.nextToken());
            }
            int cnt = calculateCycle(cycleArr, isVisited);
            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int calculateCycle(int[] cycleArr, boolean[] isVisited) {
        int cnt = 0;

        for(int i = 1 ; i < cycleArr.length; i++){

            // 싸이클을 돌며 방문한 경우 재방문할 필요가 X
            if(!isVisited[i]){
                //해당 번호에 도착하였으므로 방문=true로, 해당 인덱스에 해당하는 num을 가져오기
                isVisited[i] = true;
                int num = cycleArr[i];
                // 1부터 시작해서 1, 3, 5 1 로 돌아와서 시작한 num이 방문=true가 되면 종료
                while(!isVisited[num]){
                    isVisited[num] = true;
                    num = cycleArr[num];
                }
                cnt++;
            }
        }

        return cnt;
    }
}
