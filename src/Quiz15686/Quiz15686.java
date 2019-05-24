package Quiz15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Quiz15686 {

    // 문제에서 주어진 값들을 저장하기 위한 변수
    private static int N, M;
    // 최단거리를 저장하는 변수
    private static int minimumDistance;

    // 일반 집들과 치킨 집들을 저장하기 위한 어레이리스트
    private static ArrayList<MyPoint> houseList;
    private static ArrayList<MyPoint> chickenList;

    //일반 집과 치킨 집을 나타내는 상수 변수
    private static final int HOUSE = 1;
    private static final int CHICKEN_HOUSE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 최종의 최소 거리값을 가장 큰 Integer값으로 초기화한다.
        minimumDistance = Integer.MAX_VALUE;
        // 각 어레이리스트들을 초기화한다.
        chickenList = new ArrayList<>();
        houseList = new ArrayList<>();
        ArrayList<MyPoint> graph = new ArrayList<>();

        // 입력을 받아 chickenList와 houseList를 초기화한다.
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == HOUSE) {
                    houseList.add(new MyPoint(j, i));
                } else if (value == CHICKEN_HOUSE) {
                    chickenList.add(new MyPoint(j, i));
                }
            }
        }

        // dfs를 통해 그래프를 검사한다.
        dfs(graph, 0);

        // dfs를 통해 구한 최소 거리를 출력한다.
        System.out.println(minimumDistance);
    }

    // dfs를 통해 그래프의 크기가 M인 경우에는 거리를 계산하고, 그렇지 않은 경우에는 포함되지 않은 치킨 가게를 포함시켜 dfs를 반복한다.
    // 무작위 반복을 피하기 위해 탐색을 시작할 index를 두번째 파라미터로 넣어준다.
    private static void dfs(ArrayList<MyPoint> graph, int index) {
        if (graph.size() == M) {
            minimumDistance = Math.min(minimumDistance, retrieveChickenDistance(graph));
        } else {
            for(int i = index; i < chickenList.size() ; i++){
                if (!graph.contains(chickenList.get(i))) {
                    ArrayList<MyPoint> tempGraph = new ArrayList<>(graph);
                    tempGraph.add(chickenList.get(i));
                    dfs(tempGraph, i);
                }
            }
        }
    }

    // M개의 치킨 가게에서 치킨 거리를 계산한다.
    private static int retrieveChickenDistance(ArrayList<MyPoint> graph) {
        int distance = 0;

        for (MyPoint house : houseList) {
            int minDist = Integer.MAX_VALUE;

            for (MyPoint chicken : graph) {
                minDist = Math.min(minDist, calculateDistance(house.x, house.y, chicken.x, chicken.y));
            }
            distance += minDist;
        }

        return distance;
    }

    // 두 좌표의 치킨 거리를 계산한다.
    private static int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static class MyPoint {
        int x;
        int y;

        MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
