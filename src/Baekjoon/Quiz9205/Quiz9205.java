package Baekjoon.Quiz9205;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Quiz9205 {

    private static Point home, festival;
    private static List<Point> storeList;
    private static boolean[] isVisited;
    private static Queue<Point> queue;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        home = new Point();
        storeList = new ArrayList<>();
        festival = new Point();
        queue = new LinkedList<>();

        while(t --> 0 ){
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            home.y = Integer.parseInt(st.nextToken());
            home.x = Integer.parseInt(st.nextToken());

            while (n --> 0){
                st = new StringTokenizer(br.readLine());
                Point store = new Point();
                store.y = Integer.parseInt(st.nextToken());
                store.x = Integer.parseInt(st.nextToken());
                storeList.add(store);
            }

            // 만약 isVisited가 null이거나 이전에 선언한 배열의 크기가 부족한 경우, 새로 배열을 선언한다.
            if(isVisited == null || storeList.size() > isVisited.length){
                isVisited = new boolean[storeList.size()];
            }

            st = new StringTokenizer(br.readLine());
            festival.y = Integer.parseInt(st.nextToken());
            festival.x = Integer.parseInt(st.nextToken());

            System.out.println(bfs(home));

            // 변수들을 초기화한다.
            Arrays.fill(isVisited, false);
            storeList.clear();
            queue.clear();

        }


    }
    
    private static String bfs(Point start){
        queue.offer(start);
        
        while (!queue.isEmpty()){
            Point point = queue.poll();
            // 만약 도착 장소와의 거리 차가 1000 이하인 경우, happy를 출력한다.
            if(Math.abs(point.y - festival.y) + Math.abs(point.x - festival.x) <= 1000){
                return "happy";
            }

            // 상점을 순회하여
            for (Point store : storeList) {
                // 현재 위치가 상점과 1000m안에 있고, 아직 상점을 방문하지 않은 경우
                if(Math.abs(store.y - point.y) + Math.abs(store.x - point.x) <= 1000 && !isVisited[storeList.indexOf(store)]){
                    // 해당 상점의 방문을 true로 바꾸고
                    isVisited[storeList.indexOf(store)] = true;
                    // 큐에 해당 상점의 좌표를 새로 넣어준다.
                    queue.offer(new Point(store.x, store.y));

                }
            }
        }

        // 도착점에 도달하지 못한 경우 sad를 반환한다.
        return "sad";

    }

}
