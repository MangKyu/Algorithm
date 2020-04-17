package Baekjoon.Samsung.Quiz17142;

import Utils.PrintUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Quiz17142 {

    private static int N, M;
    // 색을 나타내는 변수와 바이러스가 퍼진 그래프를 나타내는 변수
    private static int[][] map, spreadMap;
    // 방문 여부를 나타내는 변수
    private static boolean[][] isVisited;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    // 전체 바이러스 리스트를 저장하는 변수
    private static List<Point> virus;
    // M개의 선택된 바이러스를 저장하는 변수
    private static Queue<Point> selected;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        spreadMap = new int[N + 1][N + 1];
        isVisited = new boolean[N + 1][N + 1];
        virus = new ArrayList<>();
        selected = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Point(j, i));
                }
            }
        }

        answer = -1;

        // 총 M개의 바이러스를 선택한다.
        for (int i = 0; i < virus.size(); i++) {
            selected.add(virus.get(i));
            dfs(i);
            selected.remove(virus.get(i));
        }

        System.out.println(answer);

    }

    // 총 M개의 바이러스를 선택하는 DFS함수, M개를 선택했을 경우, bfs로 바이러스를 확산시킨다.
    private static void dfs(int index) {
        if (selected.size() == M) {
            /*
            for (Point p: selected) {
                System.out.print(virus.indexOf(p) + " ");
            }
            System.out.println();
            */
            bfs(new LinkedList<>(selected));
            int time = findTime();
            //System.out.println(time);
            //PrintUtils.print2ArrayFrom1(spreadMap);

            // 모든 지역을 감염시켰으며
            if (time > -1) {
                // 현재 답이 없는 경우 해당 시간을 답으로 하고, 답이 있는 경우 최소값을 선택한다.
                if(answer == -1){
                    answer = time;
                }else {
                    answer = Math.min(answer, time);
                }
            }
        } else {
            for (int i = index + 1; i < virus.size(); i++) {
                selected.add(virus.get(i));
                dfs(i);
                selected.remove(virus.get(i));
            }
        }
    }

    // bfs로 바이러스를 확산시키는 함수
    private static void bfs(Queue<Point> queue) {
        init();
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int newY, newX;

            for (int i = 0; i < 4; i++) {
                newY = point.y + dy[i];
                newX = point.x + dx[i];
                // 새로운 좌표가 경계안에 존재하고, 빈칸이거나 비활성바이러스인 경우
                if (withinBoundaries(newY, newX) && (map[newY][newX] == 0 || map[newY][newX] == 2)) {
                    // 만약 방문을 했었다면 최소값을 선택하고
                    if (isVisited[newY][newX]) {
                        spreadMap[newY][newX] = Math.min(spreadMap[newY][newX], spreadMap[point.y][point.x] + 1);
                    } else {
                        // 방문하지 않았다면 이전 시간에 1을 증가시킨다.
                        spreadMap[newY][newX] = spreadMap[point.y][point.x] + 1;
                        isVisited[newY][newX] = true;
                        queue.offer(new Point(newX, newY));
                    }
                }
            }
        }
    }

    // 바이러스가 확산된 시간을 계산하는 함수
    private static int findTime() {
        int time = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 빈칸인 경우에 대해서만 계산을 한다.
                if (map[i][j] == 0) {
                    // 방문하지 않은 빈칸이 존재하는 경우 -1을 반환한다.
                    if (!isVisited[i][j]) {
                        return -1;
                    } else {
                        time = Math.max(time, spreadMap[i][j]);
                    }
                }
            }
        }
        return time;
    }

    // 변수들을 초기화하는 함수
    private static void init() {
        for (int i = 1; i <= N; i++) {
            Arrays.fill(isVisited[i], false);
            Arrays.fill(spreadMap[i], 0);
        }

        for (Point point : selected) {
            isVisited[point.y][point.x] = true;
            //System.out.print(virus.indexOf(point) + " ");
        }

    }

    // 해당 좌표가 경계 안에 있는지 검사한다.
    private static boolean withinBoundaries(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }

}
