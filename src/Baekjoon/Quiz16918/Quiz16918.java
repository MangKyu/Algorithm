package Baekjoon.Quiz16918;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz16918 {

    // 입력 변수
    private static int R, C, N;
    // 폭탄의 시간을 저장하기 위한 2차원 배열
    private static int[][] array;

    // 상하좌우를 탐색하기 위한 변수
    private static int[] dx = {0, 0, 1, 0, -1};
    private static int[] dy = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        array = new int[R][C];
        Queue<Point> queue = new LinkedList<>();

        // 입력을 받음
        for (int i = 0; i < R; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == 'O') {
                    array[i][j] = 1;
                }
            }
        }

        int time = 1;
        while (time < N) {

            time++;

            // 짝수 초일 경우에는 모든 원소를 ++해줌
            if (time % 2 == 0) {
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        array[j][k]++;
                    }
                }
            } else {
                // 홀수 초일 경우에는 폭탄의 시간이 2이상이면 Queue에 넣어서 터뜨림
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        if (array[j][k] > 1) {
                            queue.offer(new Point(k, j));
                        }
                    }
                }

                while (!queue.isEmpty()) {
                    Point point = queue.poll();
                    for (int l = 0; l < 5; l++) {
                        if (withinBoundaries(point.y + dy[l], point.x + dx[l])) {
                            array[point.y + dy[l]][point.x + dx[l]] = 0;
                        }
                    }
                }
            }
        }

        printArray();
    }

    private static boolean withinBoundaries(int y, int x) {
        return y < R && y >= 0 && x < C && x >= 0;
    }

    private static void printArray(){

        for (int a = 0; a < R; a++) {
            for (int b = 0; b < C; b++) {
                if (array[a][b] > 0) {
                    System.out.print('O');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

}
