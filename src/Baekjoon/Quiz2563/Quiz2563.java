package Baekjoon.Quiz2563;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz2563 {

    // 색족이 목록을 저장하는 변수
    private static List<Point> pointList;
    // 해당 영억이 색종이에 붙은지 검사하기 위한 변수
    private static boolean[][] map;
    // 전체 도화지의 크기
    private static final int MAP_SIZE = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Point point;

        int N = Integer.parseInt(br.readLine());
        pointList = new ArrayList<>();
        map = new boolean[MAP_SIZE][MAP_SIZE];

        while(N-->0){
            point = new Point();
            st = new StringTokenizer(br.readLine());
            point.x = Integer.parseInt(st.nextToken());
            point.y = Integer.parseInt(st.nextToken());

            pointList.add(point);
        }

        drawMap();
        System.out.println(calculateMapSize());

    }

    // 색종이가 덮여지는 부분을 true로 바꾸어준다.
    private static void drawMap(){
        for(Point point : pointList){
            for(int i = 0 ; i < 10 ; i++) {
                for (int j = 0; j < 10; j++) {
                    if (point.x + j < 100 && point.x + i < 100) {
                        map[point.y + i][point.x + j] = true;
                    }
                }
            }
        }
    }

    // true로 바뀐 부분의 개수를 세준다.
    private static int calculateMapSize(){
        int cnt = 0;
        for(int i = 0 ; i < MAP_SIZE ; i++){
            for(int j = 0 ; j < MAP_SIZE ; j++){
                if(map[i][j]){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}