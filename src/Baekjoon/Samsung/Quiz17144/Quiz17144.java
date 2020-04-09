package Baekjoon.Samsung.Quiz17144;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz17144 {

    private static int R, C, T;
    // 변화(확산, 청소) 이전의 그래프를 저장하는 변수
    private static int[][] before;
    // 변화(확산, 청소) 이후의 그래프를 저장하는 변수
    private static int[][] after;

    // 상하좌우 이동을 위한 변수
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    // 이동한 좌표를 저장하는 변수
    private static int newX, newY;
    // 확산하는 미세먼지 좌표를 저장하는 임시 변수
    private static List<Point> tempList;
    // 청소기 위치를 저장하는 변수
    private static List<Point> cleaner;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        before = new int[R][C];
        after = new int[R][C];
        tempList = new ArrayList<>();
        cleaner = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
                if (before[i][j] == -1) {
                    cleaner.add(new Point(j, i));
                }
            }
        }

        while (T-- > 0) {
            // 확산을 하고
            spread();
            // 진공청소기를 가동하고
            activateCleaner();
            // 배열을 복사한다.
            copyArray(after, before);
        }

        System.out.println(countDust());
    }

    private static int countDust() {
        int cnt = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (before[i][j] > 0) {
                    cnt += before[i][j];
                }
            }
        }

        return cnt;
    }

    // 청소기의 규칙에 따라 청소를 진행한다.
    private static void activateCleaner() {
        Point point = cleaner.get(0);

        //1번 청소기의 서쪽을 복사한다.
        for (int i = point.y - 1; i > 0; i--) {
            after[i][point.x] = after[i - 1][point.x];
        }

        //1번 청소기의 북쪽을 복사한다.
        for (int i = 0; i < C - 1; i++) {
            after[0][i] = after[0][i + 1];
        }

        //1번 청소기의 동쪽을 복사한다.
        for (int i = 0; i < point.y; i++) {
            after[i][C - 1] = after[i + 1][C - 1];
        }

        //1번 청소기의 남쪽을 복사한다.
        for (int i = C - 1; i > 1; i--) {
            after[point.y][i] = after[point.y][i - 1];
        }
        // 청소기 위치와 총소기의 위치 +1 를 0으로 초기화한다.
        after[point.y][0] = 0;
        after[point.y][1] = 0;

        point = cleaner.get(1);

        // 2번 청소기의 서쪽을 복사한다.
        for (int i = point.y + 1; i < R - 1; i++) {
            after[i][point.x] = after[i + 1][point.x];
        }

        // 2번 청소기의 남쪽을 복사한다.
        for (int i = 0; i < C - 1; i++) {
            after[R - 1][i] = after[R - 1][i + 1];
        }

        // 2번 청소기의 동쪽을 복사한다.
        for (int i = R - 1; i > point.y; i--) {
            after[i][C - 1] = after[i - 1][C - 1];
        }

        // 2번 청소기의 북쪽을 복사한다.
        for (int i = C - 1; i > 1; i--) {
            after[point.y][i] = after[point.y][i - 1];
        }

        // 청소기 위치와 총소기의 위치 +1 를 0으로 초기화한다.
        after[point.y][1] = 0;
        after[point.y][0] = 0;
    }

    private static void copyArray(int[][] src, int[][] dst) {
        for (int i = 0; i < R; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, src[0].length);
        }

        for (int i = 0; i < dst.length; i++) {
            Arrays.fill(src[i], 0);
        }

    }

    private static void spread() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (before[i][j] > 0) {
                    tempList.clear();

                    // 4방향에서 확산 가능한 지역을 탐색한다.
                    for (int k = 0; k < 4; k++) {
                        newX = j + dx[k];
                        newY = i + dy[k];
                        if (withinBoundaries(newY, newX) && before[newY][newX] >= 0) {
                            tempList.add(new Point(newX, newY));
                        }
                    }

                    // 미세먼지를 확산시킨다.
                    for (Point point : tempList) {
                        after[point.y][point.x] += (before[i][j] / 5);
                    }
                    after[i][j] += before[i][j] - (before[i][j] / 5 * tempList.size());
                }
            }
        }
    }

    private static boolean withinBoundaries(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }

}
