package Baekjoon.Samsung.Quiz12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz12100 {

    private static int N;

    // BFS를 통해 탐색할 그래프를 저장해둔 큐
    private static Queue<MyGraph> queue;
    // 5회가 된 결과 그래프들을 저장해둘 리스트
    private static ArrayList<MyGraph> resultList;
    // 비어있는 여부를 검사하기 위한 변수
    private static final int IS_EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();
        resultList = new ArrayList<>();

        int[][] graph = new int[N+1][N+1];
        int maxValue = 0;

        for(int i = 1 ; i <= N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j ++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 처음 입력으로 받은 그래프를 큐에 넣어 BFS 탐색을 위해 준비한다.
        queue.offer(new MyGraph(graph, 0));

        // BFS를 돌려 5회가 된 상황의 그래프들을 모은다.
        bfs();

        // 모든 그래프들을 탐색하여 최대값을 찾아낸다.
        for(MyGraph myGraph : resultList){
            for(int i = 1 ; i <= N ; i++){
                for(int j = 1 ; j <= N ; j++){
                    if(myGraph.graph[i][j] > maxValue){
                        maxValue = myGraph.graph[i][j];
                    }
                }
            }
        }

        System.out.println(maxValue);

    }

    // BFS를 통해 그래프가 이동하고 합쳐진 모든 경우를 확인한다.
    private static void bfs(){
        while(!queue.isEmpty()){
            MyGraph myGraph = queue.poll();
            for(int i = 0 ; i < 4 ; i++){
                MyGraph resultGraph = calculateGraph(new MyGraph(myGraph.graph, myGraph.cnt+1), i);
                if(resultGraph.cnt == 5){
                    resultList.add(resultGraph);
                }else{
                    queue.offer(resultGraph);
                }
            }
        }
    }

    // 그래프를 당기고 머지한 후에 다시 당김으로써 결과 그래프를 생성한다.
    private static MyGraph calculateGraph(MyGraph myGraph, int dir){
        //printGraph(myGraph);
        pullGraph(myGraph, dir);
        //printGraph(myGraph);
        mergeGraph(myGraph, dir);
        pullGraph(myGraph, dir);
        //printGraph(myGraph);
        //System.out.println("\n\n==============");

        return myGraph;
    }

    // 인접한 블록을 검사하여 합친다.
    private static void mergeGraph(MyGraph myGraph, int dir){
        switch (dir){
            case 0:
                for(int i = 1 ; i <= N - 1 ; i++){
                    for(int j = 1 ; j <= N ; j++) {
                        if (myGraph.graph[i][j] != IS_EMPTY && myGraph.graph[i][j] == myGraph.graph[i + 1][j]) {
                            myGraph.graph[i][j] *= 2;
                            myGraph.graph[i+1][j] = IS_EMPTY;
                        }
                    }
                }
                break;

            case 1:
                for(int i = 1 ; i <= N ; i++){
                    for(int j = N ; j >= 2 ; j--){
                        if (myGraph.graph[i][j] != IS_EMPTY && myGraph.graph[i][j] == myGraph.graph[i][j-1]) {
                            myGraph.graph[i][j] *= 2;
                            myGraph.graph[i][j-1] = IS_EMPTY;
                        }
                    }
                }
                break;

            case 2:
                for(int i = N ; i >= 2 ; i--){
                    for(int j = 1 ; j <= N ; j++){
                        if (myGraph.graph[i][j] != IS_EMPTY && myGraph.graph[i][j] == myGraph.graph[i-1][j]) {
                            myGraph.graph[i][j] *= 2;
                            myGraph.graph[i-1][j] = IS_EMPTY;
                        }
                    }
                }
                break;

            case 3:
                for(int i = 1 ; i <= N ; i++){
                    for(int j = 1 ; j <= N -1 ; j++) {
                        if (myGraph.graph[i][j] != IS_EMPTY && myGraph.graph[i][j] == myGraph.graph[i][j+1]) {
                            myGraph.graph[i][j] *= 2;
                            myGraph.graph[i][j+1] = IS_EMPTY;
                        }
                    }
                }
                break;
        }
    }

    // 그래프를 당겨준다.
    private static void pullGraph(MyGraph myGraph, int dir) {
        switch (dir){
            case 0:
                for(int i = 1 ; i <= N - 1 ; i++){
                    for(int j = 1 ; j <= N ; j++){
                        if(myGraph.graph[i][j] == IS_EMPTY){
                            for(int k = i + 1 ; k <= N; k++){
                                if(myGraph.graph[k][j] != IS_EMPTY){
                                    myGraph.graph[i][j] = myGraph.graph[k][j];
                                    myGraph.graph[k][j] = IS_EMPTY;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            case 1:
                for(int i = 1 ; i <= N ; i++){
                    for(int j = N ; j >= 2 ; j--){
                        if(myGraph.graph[i][j] == IS_EMPTY){
                            for(int k = j - 1 ; k >= 1 ; k--){
                                if(myGraph.graph[i][k] != IS_EMPTY){
                                    myGraph.graph[i][j] = myGraph.graph[i][k];
                                    myGraph.graph[i][k] = IS_EMPTY;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            case 2:
                for(int i = N ; i >= 2 ; i--){
                    for(int j = 1 ; j <= N ; j++){
                        if(myGraph.graph[i][j] == IS_EMPTY){
                            for(int k = i - 1 ; k >= 1 ; k--){
                                if(myGraph.graph[k][j] != IS_EMPTY){
                                    myGraph.graph[i][j] = myGraph.graph[k][j];
                                    myGraph.graph[k][j] = IS_EMPTY;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;

            case 3:
                for(int i = 1 ; i <= N ; i++){
                    for(int j = 1 ; j <= N -1 ; j++){
                        if(myGraph.graph[i][j] == IS_EMPTY){
                            for(int k = j + 1 ; k <= N; k++){
                                if(myGraph.graph[i][k] != IS_EMPTY){
                                    myGraph.graph[i][j] = myGraph.graph[i][k];
                                    myGraph.graph[i][k] = IS_EMPTY;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    // 그래프를 출력하기 위한 함수
    private static void printGraph(MyGraph myGraph){
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                System.out.print(myGraph.graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============");

    }

    private static class MyGraph{
        int cnt;
        int[][] graph;

        MyGraph(int[][] graph, int cnt){
            this.graph = new int[N+1][N+1];
            for(int i = 1 ; i <= N ; i++){
                this.graph[i] = Arrays.copyOf(graph[i], graph.length);
            }
            this.cnt = cnt;
        }
    }
}