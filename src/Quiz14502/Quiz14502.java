package Quiz14502;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz14502 {

    // 빈칸, 벽, 바이러스를 나타내는 그래프와 방문여부를 나타내는 배열
    private static int[][] graph;
    private static boolean[][] isVisited;

    //상하좌우 이동을 위한 dx, dy 변수
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static int N, M;

    // 각 칸의 상태를 나타내는 상수
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int cnt = 0;
        graph = new int[N + 1][M + 1];

        // BFS를 시작하기 위한 virusQueue와 벽을 넣기 위한 빈칸을 저장하는 emptyQueue
        Queue<Point> virusQueue = new LinkedList<>();
        Queue<Point> emptyQueue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int status = Integer.parseInt(st.nextToken());
                graph[i][j] = status;
                if(status == VIRUS){
                    virusQueue.offer(new Point(j, i));
                }else if(status == EMPTY){
                    emptyQueue.offer(new Point(j, i));
                }
            }
        }

        // 하나의 점부터 시작해서 Brute Force로 남은 2개의 벽에 벽을 세워 BFS를 돌린다.
        while(!emptyQueue.isEmpty()){
            Point searchPoint1 = emptyQueue.poll();
            graph[searchPoint1.y][searchPoint1.x] = WALL;

            //하나의 좌표를 기준으로 emptyQueue에서 2개씩 Brute Force로 검사한다.
            for(Point p1: emptyQueue){
                graph[p1.y][p1.x] = WALL;
                for(Point p2: emptyQueue){
                    if(p2 != p1){
                        graph[p2.y][p2.x] = WALL;
                        // 벽을 3개 세우면 bfs를 돌리고, 개수를 count 한다.
                        int emptySize = bfs(new LinkedList<>(virusQueue));
                        if (cnt < emptySize) {
                            cnt = emptySize;
                        }

                        //벽을 세우고, 검사를 끝내면 빈 칸으로 바꾸어준다.
                        graph[p2.y][p2.x] = EMPTY;
                    }
                }

                //벽을 세우고, 검사를 끝내면 빈 칸으로 바꾸어준다.
                graph[p1.y][p1.x] = EMPTY;

            }

            //벽을 세우고, 검사를 끝내면 빈 칸으로 바꾸어준다.
            graph[searchPoint1.y][searchPoint1.x] = EMPTY;
        }

        System.out.println(cnt);

    }

    private static int bfs(Queue<Point> queue) {
        // 감염된 바이러스를 저장하여, 감염이 끝나면 다시 감염 전의 빈 칸으로 복구시키기 위한 점들을 저장한다.
        ArrayList<Point> virusList = new ArrayList<>();
        isVisited = new boolean[N + 1][M + 1];

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            //System.out.println("y: "+ point.y + "  x: " + point.x);
            isVisited[point.y][point.x] = true;
            for (int i = 0; i < 4; i++) {
                // 이동가능한 칸이라면 감염을 위한 queue와 복구를 위한 virusList에 넣고, 해당 칸을 VIRUS상태로 바꾸고, 방문여부=true로 바꾸어준다.
                if (isMovable(point.y + dy[i], point.x + dx[i])) {
                    isVisited[point.y + dy[i]][point.x + dx[i]] = true;
                    graph[point.y + dy[i]][point.x + dx[i]] = VIRUS;
                    queue.offer(new Point(point.x + dx[i], point.y + dy[i]));
                    virusList.add(new Point(point.x + dx[i], point.y + dy[i]));
                }
            }
        }

        int cnt = retrieveEmptySize();

        for(Point point: virusList){
            graph[point.y][point.x] = EMPTY;
        }

        return cnt;
    }

    // 빈 칸의 개수를 검사한다.
    private static int retrieveEmptySize() {
        int emptySize = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (graph[i][j] == EMPTY) {
                    emptySize++;
                }
            }
        }
        return emptySize;
    }

    // 해당 칸으로 이동가능한지 검사한다.
    private static boolean isMovable(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= M && !isVisited[y][x] && graph[y][x] == EMPTY;
    }

}
