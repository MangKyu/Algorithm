package Baekjoon.Samsung.Quiz16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Quiz16235 {

    // 땅의 크기를 저장하는 변수
    private static int N;
    // 해당 좌표에 심어진 나무의 나이를 저장하는 변수
    private static ArrayList<Integer>[][] graph;
    // 해당 좌표에 남은 양분의 양을 저장하는 변수
    private static int[][] food;
    // 매년 추가될 양분의 양을 저장하는 변수
    private static int[][] A;

    // 나무의 번식을 위한 8개의 상하좌우대각 좌표
    private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        A = new int[N+1][N+1];
        graph = new ArrayList[N+1][N+1];
        food = new int[N+1][N+1];

        // 몇년이 지났는지 카운트하는 변수
        int yearCnt = 0;

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                graph[i][j] = new ArrayList<>();
                food[i][j] = 5;
            }
        }

        while(M --> 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            graph[x][y].add(z);
        }

        while(yearCnt < K){
            yearCnt++;
            afterYear();
        }

        System.out.println(retrieveAliveTree());
    }


    // 4계절의 변화를 호출하는 함수
    private static void afterYear(){
        afterSpringAndSummer();
        afterFallAndWinter();
    }

    // 봄과 여름이 지난 결과를 계산한다.
    private static void afterSpringAndSummer(){
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                int tempFood = 0;
                ArrayList<Integer> treeList = new ArrayList<>();
                Collections.sort(graph[i][j]);

                for(Integer age : graph[i][j]){
                    if(food[i][j] - age >= 0 ){
                        food[i][j] -= age;
                        treeList.add(age+1);
                    }else{
                        tempFood += ((age/2));
                    }
                }
                tempFood += food[i][j];
                graph[i][j].clear();
                graph[i][j].addAll(treeList);
                food[i][j] = tempFood;
            }
        }
    }

    // 가을과 겨울이 지난 결과를 계산한다.
    private static void afterFallAndWinter(){
        for(int i = 1 ; i<= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                food[i][j] += A[i][j];
                for(Integer age : graph[i][j]){
                    if(age % 5 == 0){
                        for(int k = 0 ; k < 8 ; k++){
                            if(withinBoundaries(i + dy[k], j + dx[k])){
                                graph[i + dy[k]][j + dx[k]].add(1);
                            }
                        }
                    }
                }
            }
        }
    }

    // 해당 좌표가 경계안에 있는지 검사한다.
    private static boolean withinBoundaries(int y, int x){
        return y <= N && x <= N && y >= 1 && x >= 1;
    }

    // 생존한 나무의 개수를 계산하는 함수
    private static int retrieveAliveTree(){
        int rsCnt = 0;

        for(int i = 1 ; i<= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                rsCnt += graph[i][j].size();
            }
        }

        return rsCnt;
    }
}