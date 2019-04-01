package Quiz16236;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz16236 {

    // 현재 물고기의 위치에서 이동가능한 점까지의 거리를 나타내는 그래프
    private static int[][] graph;

    // 물고기들에 대한 정보를 담고 있는 그래프
    private static int[][] dist;

    // 해당 좌표에 대한 방문 여부를 나타내는 그래프
    private static boolean[][] isVisited;

    //현재 물고기의 크기와 먹은 먹이의 갯수
    private static int fishSize;
    private static int eatNum;

    // 상하좌우로 이동하기 위한 x, y 변수
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static int N;
    private static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        fishSize = 2;
        eatNum = 0;
        queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
                // 시작하는 점을 큐에 넣어준다.
                if (num == 9) {
                    queue.offer(new Point(j, i));
                }
            }
        }

        System.out.println(bfs());

    }


    /* 전체적으로 보면 2중 Queue를 사용하고 있는데, 물고기가 한번 이동하고 나면 큐가 비어있게 된다.
    * 하지만 아직 물고기가 이동가능한 상태라면 다시 그 물고기의 현재위치를 큐에 넣어 이동가능한 거리를 다시 계산해야 한다.
    * */

    private static int bfs() {
        // 이동한 거리를 나타내는 변수
        int cnt = 0;

        while (!queue.isEmpty()) {
            dist = new int[N + 1][N + 1];
            isVisited = new boolean[N + 1][N + 1];

            Point curPos = queue.poll();

            // 시작점을 기준으로 이동가능한 점들을 반환한다.
            ArrayList<Point> pointList = calculateGraphDistance(curPos.y, curPos.x);

            // 이동가능한 점들 중에서 이동할 점을 계산한다.
            Point nextPos = calculateNextPoint(pointList);

            // 이동가능한 점이 존재하면
            if(nextPos != null){
                // 현재 위치는 0으로(9->0) , 다음 위치(먹이 점수 -> 9) 로 바꾸어준다.
                graph[nextPos.y][nextPos.x] = 9;
                graph[curPos.y][curPos.x] = 0;

                // 이동한 거리는 dist 배열을 통해 계산한다.
                cnt += dist[nextPos.y][nextPos.x];

                // 물고기를 먹었으므로 먹은 횟수를 증가하고, 경우에 따라 사이즈를 늘려준다.
                eatNum++;
                if(eatNum == fishSize){
                    eatNum = 0;
                    fishSize++;
                }

                // 다음 위치를 큐에 넣어준다.
                queue.offer(nextPos);

            }
        }
        return cnt;
    }

    // 이동가능한 pointList 중에서 minPoint 값을 찾아 반환한다.
    private static Point calculateNextPoint(ArrayList<Point> pointList){
        Point nextPoint = null;
        int minDistance = Integer.MAX_VALUE;

        for(Point point : pointList){
            // 이동가능한 점이 null 상태라면 조회하는 point를 nextPoint로 지정하고 최소 거리를 계산한다.
            if(nextPoint == null){
                nextPoint = point;
                minDistance = dist[nextPoint.y][nextPoint.x];
            }else{
                // 이동 가능한 점이 있는 상태인데, 현재의 point가 더 거리가 짧다면 해당 point를 nextPoint로 지정한다,
                if(minDistance > dist[point.y][point.x]){
                    nextPoint = point;
                    minDistance = dist[nextPoint.y][nextPoint.x];
                }else if(minDistance == dist[point.y][point.x]){
                    // 거리가 같은 점이 여러 개라면, 상을 기준으로, 상에 위치하는게 여러개라면 좌를 기준으로 탐색한다.
                    if(nextPoint.y > point.y || nextPoint.y == point.y && nextPoint.x > point.x){
                        nextPoint = point;
                        minDistance = dist[nextPoint.y][nextPoint.x];
                    }
                }
            }
        }

        return nextPoint;

    }

    // bfs를 통해 현재 점에서부터 이동가능한 점까지의 거리를 계산한다.
    private static ArrayList<Point> calculateGraphDistance(int y, int x){
        // 먹이를 먹을 수 있는 점들의 집합
        ArrayList<Point> foodList = new ArrayList<>();
        // 시작점을 큐에 넣어둔다.
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point fishPos = queue.poll();
            isVisited[fishPos.y][fishPos.x] = true;

            for (int i = 0; i < 4; i++) {
                // 이동할 좌표가 경계 안에 있고 방문하지 않았는지 검사한다.
                if(withinBoundaries(fishPos.y+dy[i], fishPos.x+dx[i]) && !isVisited[fishPos.y+dy[i]][fishPos.x+dx[i]]){

                    // 이동하려는 좌표의 물고기의 크기가 현재 물고기의 크기보다 작으면 이동가능하므로 거리를 계산하고 큐에 넣어준다.
                    if(graph[fishPos.y+dy[i]][fishPos.x+dx[i]] < fishSize){
                        isVisited[fishPos.y+dy[i]][fishPos.x+dx[i]] = true;
                        queue.offer(new Point(fishPos.x+dx[i], fishPos.y+dy[i]));
                        dist[fishPos.y+dy[i]][fishPos.x+dx[i]] = dist[fishPos.y][fishPos.x] + 1;

                        // 해당 위치에 물고기가 있으면 먹을 수 있으므로 foodList에 넣어준다.
                        if(graph[fishPos.y+dy[i]][fishPos.x+dx[i]] > 0){
                            foodList.add(new Point(fishPos.x+dx[i], fishPos.y+dy[i]));
                        }

                        // 이동하려는 좌표의 물고기의 크기가 현재 물고기의 크기와 같으면 이동만 가능하므로 큐에 넣어준다.
                    }else if(graph[fishPos.y+dy[i]][fishPos.x+dx[i]] == fishSize){
                        dist[fishPos.y+dy[i]][fishPos.x+dx[i]] = dist[fishPos.y][fishPos.x] + 1;
                        isVisited[fishPos.y+dy[i]][fishPos.x+dx[i]] = true;
                        queue.offer(new Point(fishPos.x+dx[i], fishPos.y+dy[i]));
                    }

                }
            }
        }
        return foodList;
    }

    // 해당 좌표가 경계 안에 있는지 검사한다.
    private static boolean withinBoundaries(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }
}
