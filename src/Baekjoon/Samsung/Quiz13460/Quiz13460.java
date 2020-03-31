package Baekjoon.Samsung.Quiz13460;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz13460 {

    // 배열의 크기 N, M
    private static int N, M;
    // 상하좌우 이동을 위한 dx, dy 변수
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    // 출구 좌표를 저장하는 변수
    private static Point exit;
    // 빈 좌표, 출구 좌표 문자를 상수화
    private static char EMPTY = '.';
    private static char EXIT = 'O';
    // 욺겨야 할 구슬을 저장하기 위한 queue
    private static Queue<Marbles> queue;
    // [red.y][red.x][blue.y][blue.x]의 형태로 해당 (빨강,파랑)의 좌표를 방문한 여부를 저장하는 변수
    private static boolean[][][][] isVisited;
    // 입력으로 받는 그래프를 저장하는 함수
    private static char[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 사용하기 위한 변수들을 선언한다.
        Marbles marbles = new Marbles();
        isVisited = new boolean[N][M][N][M];
        graph = new char[N][M];

        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 'R') {
                    marbles.red = new Point(j, i);
                } else if (graph[i][j] == 'B') {
                    marbles.blue = new Point(j, i);
                } else if (graph[i][j] == EXIT) {
                    exit = new Point(j, i);
                }
            }
        }
        // 초기 시작 좌표의 방문=true로 바꾸어준다.
        isVisited[marbles.red.y][marbles.red.x][marbles.blue.y][marbles.blue.x] = true;

        queue = new LinkedList<>();
        queue.offer(marbles);

        System.out.println(bfs());

    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            // 큐로부터 Marbles 객체를 꺼내서 bfs를 진행한다.
            Marbles marbles = queue.poll();

            if (marbles.cnt < 10) {
                for (int i = 0; i < 4; i++) {
                    boolean isRedFirst = true;
                    Marbles newMarbles = new Marbles(marbles, marbles.cnt + 1);

                    // 이동하는 방향과 현재 빨강, 파랑의 좌표를 고려하여 어느 구슬부터 움직일지 순서를 정한다.
                    if (i == 0 && newMarbles.red.x == newMarbles.blue.x && newMarbles.red.y > newMarbles.blue.y) {
                        isRedFirst = false;
                    } else if (i == 1 && newMarbles.red.x < newMarbles.blue.x && newMarbles.red.y == newMarbles.blue.y) {
                        isRedFirst = false;
                    } else if (i == 2 && newMarbles.red.x == newMarbles.blue.x && newMarbles.red.y < newMarbles.blue.y) {
                        isRedFirst = false;
                    } else if (i == 3 && newMarbles.red.x > newMarbles.blue.x && newMarbles.red.y == newMarbles.blue.y) {
                        isRedFirst = false;
                    }

                    if (isRedFirst) {
                        moveMarble(newMarbles, i, true);
                        moveMarble(newMarbles, i, false);
                    } else {
                        moveMarble(newMarbles, i, false);
                        moveMarble(newMarbles, i, true);

                    }

                    // 빨강 구슬이 출구에 위치하며
                    if (newMarbles.red.y == exit.y && newMarbles.red.x == exit.x) {
                        // 파랑 구슬이 출구가 아닐 경우 결과값을 반환
                        if (!(newMarbles.blue.y == exit.y && newMarbles.blue.x == exit.x)) {
                            return newMarbles.cnt;
                        }
                    } else {
                        // 해당 새로운 파랑색의 좌표가 출구 좌표가 아니고, [빨강색 좌표][파랑색 좌표]를 방문한 적이 없으면
                        if (!(newMarbles.blue.y == exit.y && newMarbles.blue.x == exit.x) && !isVisited[newMarbles.red.y][newMarbles.red.x][newMarbles.blue.y][newMarbles.blue.x]) {
                             // 방문 여부를 true로 바꾸고 Queue에 넣어준다.
                            isVisited[newMarbles.red.y][newMarbles.red.x][newMarbles.blue.y][newMarbles.blue.x] = true;
                            queue.offer(newMarbles);
                            //System.out.printf("Red:[%d, %d] Blue:[%d, %d] => dir: %d, cnt: %d\n", newMarbles.red.y, newMarbles.red.x, newMarbles.blue.y, newMarbles.blue.x, i, newMarbles.cnt);
                        }

                    }
                }
            }
        }

        return -1;
    }

    // Marbles 객체, 빨/파 구슬, 이동 방향, 빨강/파랑 구슬 여부를 인자로 받아 해당 구슬을 이동시킨다.
    private static void moveMarble(Marbles marbles, int dir, boolean isRed) {
        int newX;
        int newY;

        while (true) {
            // 움직이는 구슬이 빨강색일 경우
            if (isRed) {
                newX = marbles.red.x + dx[dir];
                newY = marbles.red.y + dy[dir];
            } else {
                // 움직이는 구슬이 파랑색일 경우
                newX = marbles.blue.x + dx[dir];
                newY = marbles.blue.y + dy[dir];
            }

            // 새로운 좌표가 비어있으며
            if (graph[newY][newX] == EMPTY) {
                // 이동하는 구슬이 빨강색이며, 파랑색과 같은 위치가 아니라면
                if (isRed && !(marbles.blue.y == newY && marbles.blue.x == newX)) {
                    // 이전 좌표의 graph를 empty로 바꾸고, 현재의 좌표를 새로운 좌표로 바꾼다.
                    graph[marbles.red.y][marbles.red.x] = EMPTY;
                    marbles.red.x = newX;
                    marbles.red.y = newY;
                    //System.out.println(newY + " " + newX);

                    // 이동하는 구슬이 파랑색이며, 빨강색과 같은 위치가 아니라면
                } else if (!isRed && !(marbles.red.y == newY && marbles.red.x == newX)) {
                    // 해당 [빨강색 좌표][새로운 좌표] = true, 이전 좌표의 graph를 empty로 바꾸고, 현재의 좌표를 새로운 좌표로 바꾼다.
                    graph[marbles.blue.y][marbles.blue.x] = EMPTY;
                    marbles.blue.x = newX;
                    marbles.blue.y = newY;
                    //System.out.println(newY + " " + newX);
                } else {
                    break;
                }

                // 새로 이동한 좌표가 출구인 경우, 이동시키고 이동을 종류한다.
            } else if (graph[newY][newX] == EXIT) {
                if (isRed) {
                    graph[marbles.red.y][marbles.red.x] = EMPTY;
                    marbles.red.y = newY;
                    marbles.red.x = newX;
                } else {
                    graph[marbles.blue.y][marbles.blue.x] = EMPTY;
                    marbles.blue.y = newY;
                    marbles.blue.x = newX;
                }
                break;
            } else {
                break;
            }
        }

    }

}

// (빨강, 파랑, 움직인 횟수)를 세트로 저장하기 위한 Marbles 클래스
class Marbles {
    Point red;
    Point blue;
    int cnt;

    public Marbles() {

    }

    public Marbles(Marbles marbles, int cnt) {
        this.red = new Point(marbles.red.x, marbles.red.y);
        this.blue = new Point(marbles.blue.x, marbles.blue.y);
        this.cnt = cnt;
    }

}

