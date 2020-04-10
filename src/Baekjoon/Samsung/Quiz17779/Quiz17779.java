package Baekjoon.Samsung.Quiz17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz17779 {

    private static int N, answer;
    private static int[][] map, temp;
    private static int[] area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        temp = new int[N + 1][N + 1];
        area = new int[6];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 1; x <= N ; x++) {
            for (int y = 1; y <= N ; y++) {
                for (int d2 = 1; d2 <= N; d2++) {
                    for (int d1 = 1; d1 <= N; d1++) {
                        // 문제에서 준 조건에 부합한 경우 구역을 나누고 차이를 계산한다.
                        if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N){
                            divide(x, y, d1, d2);
                            int diff = calculateDiff();
                            //System.out.printf("x = %d, y = %d, d1 = %d, d2 = %d \n", x, y, d1, d2);
                            //Utils.PrintUtils.print2ArrayFrom1(temp);
                            answer = Math.min(answer, diff);
                        }
                    }
                }
            }
        }

        System.out.println(answer);

    }

    // 구역을 나누어주는 함수로, 문제의 범위를 그대로 이용한다.
    private static void divide(int x, int y, int d1, int d2){
        for (int c = 1; c <= N ; c++) {
            for (int r = 1; r <= N ; r++) {
                if(1 <= r && r < x + d1 && 1 <= c &&c <= y){
                    temp[r][c] = 1;
                } else if (1 <= r && r <= x+d2 && y < c && c <= N){
                    temp[r][c] = 2;
                }else if (x+d1 <= r && r <= N && 1 <= c && c < y-d1+d2){
                    temp[r][c] = 3;
                }else if (x+d2 < r && r <= N && y-d1+d2 <= c && c <= N){
                    temp[r][c] = 4;
                } else {
                    temp[r][c] = 5;
                }
            }
        }

        // 경계값을 5로 바꾸어준다.
        for (int i = 0; i <= d1 ; i++) {
            //System.out.printf("x = %d, y = %d \n", x + i, y-i);
            //System.out.printf("x = %d, y = %d \n", x + d2 + i, y + d2 -i);
            temp[x + i][y-i] = 5;
            temp[x + d2 + i][ y + d2 - i] = 5;
        }

        // 경계값을 5로 바꾸어준다.
        for (int j = 0; j <= d2 ; j++) {
            //System.out.printf("x = %d, y = %d \n", x + j, y + j);
            //System.out.printf("x = %d, y = %d \n", x + d1 + j, y - d1 + j);
            temp[x + j][y + j] = 5;
            temp[x + d1 + j][ y - d1 + j] = 5;
        }

    }

    // 최대값과 최소값을 구하는 함수
    private static int calculateDiff(){
        Arrays.fill(area, 0);
        int num1, num2;
        for (int c = 1; c <= N ; c++) {
            num1 = -1;
            num2 = -1;

            // 5의 시작과 끝이 존재하는 경우는 해당 값을 빼고 5에 더해준다.
            for (int r = 1; r <= N ; r++) {
                if(temp[r][c] == 5){
                    if(num1 == -1 ){
                        num1 = r;
                    } else {
                        num2 = r;
                    }
                }
                area[temp[r][c]] += map[r][c];
            }

            for(int i = num1 + 1; i < num2 ; i++){
                area[temp[i][c]] -= map[i][c];
                area[5] += map[i][c];
            }


        }

        int max = area[1];
        int min = area[1];

        for (int i = 2; i <= 5 ; i++) {
            min = Math.min(min, area[i]);
            max = Math.max(max, area[i]);
        }
        //System.out.println(max - min);
        return max - min;
    }

}
