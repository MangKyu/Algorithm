package Baekjoon.Samsung.Quiz14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz14890 {

    private static int N, L, cnt;
    // 배열의 해당 인덱스가 이미 경사로가 추가되었는지를 저장하는 변수
    private static boolean[] isAppended;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        int[] rowArr = new int[N];
        int[] colArr = new int[N];
        isAppended = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행과 열을 rowArr와 colArr에 저장하여 한번에 검사함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rowArr[j] = map[i][j];
                colArr[j] = map[j][i];
            }

            checkMovable(rowArr);
            checkMovable(colArr);
        }
        System.out.println(cnt);

    }

    private static void checkMovable(int[] arr) {
        // 먼저 경사로가 추가되었는지 저장하는 변수를 초기화함
        Arrays.fill(isAppended, false);

        for (int i = 0; i < N - 1; i++) {
            int diff = arr[i] - arr[i + 1];
            // 차이 값이 1이고
            if(diff == 1){
                // 범위를 벗어나면 해당 함수를 종료한다.
                if(i + L >= N){
                    return;
                } else {
                    // 차이가 1이면 현재의 다음 블록부터 높이가 일정한지 검사해야 하므로 i++해준다.
                    i++;
                    for (int j = 0; j < L - 1; j++) {
                        // 만약 경사로를 높아야하는 길의 높이가 다르면 함수를 종료
                        if(arr[i + j] != arr[i + j + 1]){
                            return;
                        }
                    }

                    // 경사로를 놓을 수 있다면 경사로를 놓는다
                    for (int j = 0; j < L; j++) {
                        isAppended[i + j] = true;
                    }

                    // 탐색해야하는 index는 i +L -2 로 끝나고 for문의 i++를 만나 사실상 i + L - 1이 된다.
                    i = i + L - 2;
                }
            } else if (diff == -1){
                // 차이가 1인 경우 범위를 벗어나면 종료한다.
                if(i - L + 1 < 0){
                    return;
                }

                // 만약 L이 1보다 크다면
                if (L > 1){
                    for (int j = 1; j < L; j++) {
                        // 경사로를 놓아야하는 길의 높이가 같지 않거나 이미 경사로가 놓아져 있다면 경사로를 더 놓을 수 없으므로 종료한다.
                        if(arr[i - j] != arr[i - j + 1] || isAppended[i - j ] || isAppended[i - j + 1]){
                            return;
                        }
                    }

                    // 경사로를 놓을 수 있다면 경사로를 놓아준다.
                    for (int j = 0; j < L - 1; j++) {
                        isAppended[i - j ] = true;
                    }
                } else {
                    // 만약 L이 1이라면 현재 위치만 경사로를 놓았는지 않놓았는지 검사하여, 이미 경사로가 놓여져있다면 함수를 종료하고
                    if(isAppended[i]){
                        return;

                        // 경사로가 놓여져있지 않다면 경사로를 놓는다.
                    }else {
                        isAppended[i] = true;
                    }

                }

                // 차이가 1보다 크거나 -1보다 작은경우는 경사로를 놓을 수 없으므로 종료한다.
            } else if(diff > 1 || diff < -1){
                return;
            }
        }
        cnt++;
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
    }

}
