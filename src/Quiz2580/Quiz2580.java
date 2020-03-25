package Quiz2580;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz2580 {

    // 스도쿠의 크기 N
    private static int N = 9;
    // 공란을 나타내는 상수
    private static int EMPTY_SPACE = 0;
    // 스도쿠를 담는 2차원 배열
    private static int[][] map;
    // 공란인 점을 담고있는 list
    private static List<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[N][N];
        list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 해당 칸이 0인 숫자는 list에 추가한다.
                if (map[i][j] == EMPTY_SPACE) {
                    list.add(new Point(j, i));
                }
            }
        }

        // 백트래킹 탐색을 진행한다.
        backtracking(0, 0);
    }

    // 조회해야하는 list의 index와 cnt를 인자로 받아 백트래킹을 진행한다.
    private static void backtracking(int index, int cnt) {
        // 만약 0을 채운 숫자와 list의 크기가 같으면 출력하고 종료한다.
        if (cnt == list.size()) {
            printMap();
            System.exit(0);
        }

        Point point = list.get(index);

        for (int i = 1; i <= N; i++) {
            // 해당 좌표에 i 값이 입력 가능하다면
            if (retrieveAll(point, i)) {
                // 해당 좌표에 값을 입력하고
                map[point.y][point.x] = i;
                // 백트래킹을 다음 index에 대해 cnt를 1증가시켜 진행한다.
                backtracking(index + 1, cnt + 1);
                map[point.y][point.x] = 0;
            }
        }

    }

    // row, col, box를 모두 조회하여 해당 포인트와 값이 입력 가능한지 조회하는 함수
    private static boolean retrieveAll(Point point, int num) {
        return retrieveBox(point, num) && retrieveRowAndCol(point, num);
    }

    // row, col를 조회하여 해당 포인트와 값이 입력 가능한지 조회하는 함수
    private static boolean retrieveRowAndCol(Point point, int num) {
        for (int j = 0; j < N; j++) {
            if (map[point.y][j] == num) {
                return false;
            }
            if (map[j][point.x] == num) {
                return false;
            }
        }
        return true;
    }

    // box를 조회하여 해당 포인트와 값이 입력 가능한지 조회하는 함수
    private static boolean retrieveBox(Point point, int num) {
        int row = (point.y / 3) * 3;
        int col = (point.x / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i + row][j + col] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
