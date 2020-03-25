package Baekjoon.Quiz2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz2583 {

    // 정사각형의 유무를 저장하는 변수
    private static int[][] map;

    //상하좌우 이동을 위한 dx, dy 변수
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    // 넓이의 개수를 카운팅하기 위한 변수
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        List<Integer> sizeList = new ArrayList<>();

        // 좌하단 좌표부터 우상단 좌표까지 map을 1로 바꾸어준다.
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());

            int leftX = Integer.parseInt(st.nextToken());
            int leftY = Integer.parseInt(st.nextToken());
            int rightX = Integer.parseInt(st.nextToken());
            int rightY = Integer.parseInt(st.nextToken());

            for (int i = leftY; i < rightY; i++) {
                for (int j = leftX; j < rightX; j++) {
                    map[i][j] = 1;
                }
            }


        }

        // 해당 좌표의 map값이 0이라면 dfs를 통해 넓이를 계산하고 리스트에 넣어준다.
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    cnt = 0;
                    dfs(i, j);

                    sizeList.add(cnt);
                }
            }
        }

        // 나누어진 구역의 개수와 각각의 크기를 오름차순으로 출력한다.
        Collections.sort(sizeList);
        System.out.println(sizeList.size());
        for (Integer size : sizeList) {
            System.out.print(size + " ");
        }
    }

    // 해당 좌표값을 1로 바꾸어주고 영역의 개수를 1 늘려준다.
    private static void dfs(int y, int x) {
        map[y][x] = 1;
        cnt++;

        for (int i = 0; i < 4; i++) {
            int newY = y + dy[i];
            int newX = x + dx[i];

            // 좌표가 배열의 내부에 있으며 방문하지 않은 상태인지 검사하고, 방문하지 않았다면 dfs를 통해 탐색한다.
            if (newY >= 0 && newX >= 0 && newY < map.length && newX < map[0].length) {
                if (map[newY][newX] == 0) {
                    dfs(newY, newX);
                }
            }

        }
    }

}