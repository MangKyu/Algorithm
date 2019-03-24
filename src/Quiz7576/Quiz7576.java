package Quiz7576;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz7576 {
    // 해당 토마토가 익은 토마토인지, 익지 않은 토마토인지를 정의하는 변수
    private static final int RIPE_TOMATO = 1;
    private static final int RAW_TOMATO = 0;

    // 토마토가 저장된 창고와 익은 날을 저장하는 그래프
    private static int[][] map;
    private static int[][] ripeDay;

    // 상하좌우 탐색을 할 토마토를 저장하는 큐
    private static Queue<Point> queue;
    private static int N, M;

    // 상하좌우 검사를 위한 dx, dy
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 배열의 인덱스를 1~N, 1~M까지 하기 위해 배열 선언
        map = new int[N + 1][M + 1];
        ripeDay = new int[N + 1][M + 1];

        //탐색을 할 토마토를 저장
        queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                map[i][j] = tomato;

                // 해당 토마토가 처음부터 익은 토마토라면 Queue에 넣어 검사를 진행한다.
                if (tomato == RIPE_TOMATO) {
                    queue.offer(new Point(j, i));
                }
            }
        }

        bfs();

        System.out.println(countMaxDay());

    }

    // 익어있는 토마토들이 저장된 Queue에서 주변 토마토들을 검사한다.
    private static void bfs() {
        while (!queue.isEmpty()) {
            Point tomato = queue.poll();
            // 익은 토마토의 상하좌우를 검사한다.
            for (int i = 0; i < 4; i++) {
                // 그 범위가 경계 안에 있는지, 익지 않은 토마토가 있는지 검사한다.
                if (withinBoundaries(tomato.y + dy[i], tomato.x + dx[i]) && map[tomato.y + dy[i]][tomato.x + dx[i]] == RAW_TOMATO) {
                    // 해당 토마토를 익은 상태로 바꾸고, 원래 상태의 토마토보다 하루 지났으므로 날짜를 +1 해주고, 큐에 넣어준다.
                    map[tomato.y + dy[i]][tomato.x + dx[i]] = RIPE_TOMATO;
                    ripeDay[tomato.y + dy[i]][tomato.x + dx[i]] = ripeDay[tomato.y][tomato.x] + 1;
                    queue.offer(new Point(tomato.x + dx[i], tomato.y + dy[i]));
                }
            }
        }
    }

    // 해당 y, x가 범위 안에 포함되는지 검사한다.
    private static boolean withinBoundaries(int y, int x) {
        return y >= 1 && x >= 1 && y <= N && x <= M;
    }

    // 최대 날짜를 계산하는데, 안익은 토마토가 있으면 -1 없으면 최댓값을 반환한다.
    private static int countMaxDay(){
        int maxDay = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == RAW_TOMATO) {
                    return -1;
                }

                if(ripeDay[i][j] > maxDay){
                    maxDay = ripeDay[i][j];
                }
            }
        }

        return maxDay;
    }

}