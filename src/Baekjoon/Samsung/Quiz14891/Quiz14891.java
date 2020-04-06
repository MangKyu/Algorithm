package Baekjoon.Samsung.Quiz14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz14891 {

    private static int[][] gear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gear = new int[5][9];
        int k, index, dir;
        for (int i = 1; i < gear.length; i++) {
            for (int j = 1; j < gear[0].length; j++) {
                gear[i][j] = br.read() - '0';
            }
            br.readLine();
        }

        k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            index = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            // 만약 현재 바퀴의 다음 바퀴가 존재하고, 현재 바퀴의 3번째 톱니와 다음 바퀴의 7번째 톱니의 방향이 다르다면,
            // 오른쪽으로 회전을 전파시키며 회전 방향을 역전시킴
            if(index + 1 <= 4 && gear[index][3] != gear[index + 1][7]) {
                rotateRight(index + 1, reverseDir(dir));
            }

            // 만약 현재 바퀴의 이전 바퀴가 존재하고, 현재 바퀴의 7번째 톱니와 이전 바퀴의 3번째 톱니의 방향이 다르다면,
            // 왼쪽으로 회전을 전파시키며 회전 방향을 역전시킴
            if (index - 1 >= 1 && gear[index][7] != gear[index - 1][3]){
                rotateLeft(index - 1, reverseDir(dir));
            }
            rotateArray(index, dir);
        }

        int score = 0;

        for (int i = 1; i < gear.length; i++) {
            if(gear[i][1] == 1){
                score += Math.pow(2, i - 1);
            }
        }

        System.out.println(score);
    }

    // 그래프를 출력하기 위한 함수
    private static void printGraph(int[][] graph) {
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[0].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 오른쪽으로 회전하는 재귀함수
    private static void rotateRight(int index, int dir){
        if(index <= 4){
            if(index + 1 <= 4 && gear[index][3] != gear[index + 1][7]) {
                rotateRight(index + 1, reverseDir(dir));
            }
            //System.out.printf("Right --> index: %d, dir: %d \n", index, dir);
            rotateArray(index, dir);
        }
    }

    // 왼쪽으로 회전하는 재귀함수
    private static void rotateLeft(int index, int dir){
        if(index >= 1){
            if (index - 1 >= 1 && gear[index][7] != gear[index - 1][3]){
                rotateLeft(index - 1, reverseDir(dir));
            }
            //System.out.printf("Left --> index: %d, dir: %d \n", index, dir);
            rotateArray(index, dir);
        }
    }

    // 배열을 원하는 방향으로 rotate시키는 함수
    private static void rotateArray(int index, int dir){
        int[] arr = new int[9];
        if(dir == 1){
            arr[1] = gear[index][8];
            System.arraycopy(gear[index], 1, arr, 2, 7);
        } else {
            arr[8] = gear[index][1];
            System.arraycopy(gear[index], 2, arr, 1, 7);
        }

        gear[index] = arr;
    }

    // 다음 회전 방향을 뒤집어주는 함수 시계 <-> 반시계
    private static int reverseDir(int dir){
        return dir == 1 ? -1 : 1;
    }

}
