package Baekjoon.Samsung.Quiz15683;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz15683 {
    private static int N, M;
    private static int[][] map;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    // cctv의 좌표를 저장하는 변수
    private static List<Point> cctvList;
    // 최소 사각지대의 개수를 저장하는 변수
    private static int answer;
    // 현재 사각지대인 방의 개수를 저장하는 변수
    private static int temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvList = new ArrayList<>();
        map = new int[N][M];
        answer = N * M;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && map[i][j] > 0) {
                    answer--;
                }

                // 해당 좌표가 5인 경우는 미리 4방향을 감시중으로 변경한다.
                // 그리고 값을 100으로 넘겨 CCTV는 최대 8개이므로 현재 감시중인 상태와 중복되지 않게 한다.
                // 만약 1로 넘길 경우 실제 dfs의 cnt가 1인 경우와 겹쳐서 중복처리될 수 있음
                if (map[i][j] == 5) {
                    for (int k = 0; k < 4; k++) {
                        // 5번 CCTV로 감시중으로 바뀐 개수를 빼서 사각지대의 개수를 갱신한다.
                        answer -= monitor(i, j, k, 100);
                    }

                    // 만약 좌표가 1이상 4 이하인 경우 CCTV이므로 리스트에 추가한다.
                } else if (map[i][j] < 5 && map[i][j] > 0) {
                    cctvList.add(new Point(j, i));
                }
            }
        }

        temp = answer;
        dfs(0);

        System.out.println(answer);

    }

    // 재귀함수를 이용해 순차적으로 cctv의 방향을 회전한다.
    // 파라미터로 현재 사용중인 (cctv의 개수 + 1)를 넘긴다.
    // cctv의 개수 + 1 을 넘기는 이유는 감시중인 cctv가 0개인 경우에는 그대로 0이 되기 때문이다.
    private static void dfs(int cnt) {
        // 모든 cctv를 사용중인 경우 결과를 비교해 최소값을 선택한다.
        if (cnt == cctvList.size()) {
            answer = Math.min(answer, temp);
        } else {
            Point point = cctvList.get(cnt);

            // 좌표가 1인 경우는 0, 1, 2, 3를 순차적으로 감시한다.
            if (map[point.y][point.x] == 1) {
                for (int i = 0; i < 4; i++) {
                    temp -= monitor(point.y, point.x, i, cnt + 1);
                    dfs(cnt + 1);
                    temp += rollback(point.y, point.x, i, cnt + 1);
                }

                // 좌표가 2인 경우는 [{0,2}, {1,3}] 를 순차적으로 감시한다.
            } else if (map[point.y][point.x] == 2) {
                for (int i = 0; i < 2; i++) {
                    temp -= monitor(point.y, point.x, i, cnt + 1);
                    temp -= monitor(point.y, point.x, i + 2, cnt + 1);
                    dfs(cnt + 1);
                    temp += rollback(point.y, point.x, i, cnt + 1);
                    temp += rollback(point.y, point.x, i + 2, cnt + 1);
                }

                // 좌표가 3인 경우는 [{0,1} {1,2} {2,3} {3,0}] 를 순차적으로 감시한다.
            } else if (map[point.y][point.x] == 3) {
                for (int i = 0; i < 4; i++) {
                    temp -= monitor(point.y, point.x, i, cnt + 1);
                    if (i == 3) {
                        temp -= monitor(point.y, point.x, i - 3, cnt + 1);
                    } else {
                        temp -= monitor(point.y, point.x, i + 1, cnt + 1);
                    }
                    dfs(cnt + 1);
                    temp += rollback(point.y, point.x, i, cnt + 1);
                    if (i == 3) {
                        temp += rollback(point.y, point.x, i - 3, cnt + 1);
                    } else {
                        temp += rollback(point.y, point.x, i + 1, cnt + 1);
                    }
                }

                // 좌표가 3인 경우는 [{0,1,2}, {1,2,3}, {2,3,0}, {3,0,1}] 를 순차적으로 감시한다.
            } else if (map[point.y][point.x] == 4) {
                for (int i = 0; i < 4; i++) {
                    temp -= monitor(point.y, point.x, i, cnt + 1);
                    if (i + 1 >= 4) {
                        temp -= monitor(point.y, point.x, i + 1 - 4, cnt + 1);
                    } else {
                        temp -= monitor(point.y, point.x, i + 1, cnt + 1);
                    }
                    if (i + 2 >= 4) {
                        temp -= monitor(point.y, point.x, i + 2 - 4, cnt + 1);
                    } else {
                        temp -= monitor(point.y, point.x, i + 2, cnt + 1);
                    }
                    dfs(cnt + 1);
                    temp += rollback(point.y, point.x, i, cnt + 1);
                    if (i + 1 >= 4) {
                        temp += rollback(point.y, point.x, i + 1 - 4, cnt + 1);
                    } else {
                        temp += rollback(point.y, point.x, i + 1, cnt + 1);
                    }
                    if (i + 2 >= 4) {
                        temp += rollback(point.y, point.x, i + 2 - 4, cnt + 1);
                    } else {
                        temp += rollback(point.y, point.x, i + 2, cnt + 1);
                    }
                }
            }
        }
    }

    // 현재의 좌표(y, x)를 가지고 해당 방향으로 이동시키고 감시 이전의 상태로 롤백시키는 함수
    // 만약 해당 좌표가 -num 인 경우는 해당 dfs에 의해 감시중인 상태이므로 좌표 상태=0으로 바꾼다.
    private static int rollback(int y, int x, int dir, int num) {
        int cnt = 0;
        int newX = x;
        int newY = y;
        while (true) {
            newY += dy[dir];
            newX += dx[dir];
            // 새로운 좌표가 경계 안에 있으며
            if (withinBoundaries(newY, newX)) {
                // 해당 좌표의 값이 -num인 경우(이전 dfs에 의해 감시중인 경우)
                if (map[newY][newX] == -num) {
                    // 감시하지 않는 상태(0)으로 바꾸어주고
                    map[newY][newX] = 0;
                    // 감시하지 않는 방의 숫자를 하나 늘린다.
                    cnt++;

                    // 만약 새로운 좌표가 벽인 경우 이동을 종료한다.
                } else if (map[newY][newX] == 6) {
                    break;
                }

                // 나약 새로운 좌표가 경계 밖인 경우 이동을 종료한다.
            } else {
                break;
            }
        }
        return cnt;
    }

    // 현재의 좌표(y, x)를 가지고 해당 방향으로 이동시키고 감시 상태로 바꾸는 함수
    // 파라미터로 y좌표, x좌표, 이동방향, 동원된 현재의 CCTV 숫자(재귀 함수의 파라미터 + 1)을 받는다.
    private static int monitor(int y, int x, int dir, int num) {
        int cnt = 0;
        int newX = x;
        int newY = y;
        while (true) {
            newY += dy[dir];
            newX += dx[dir];
            // 새로운 좌표가 경계 안에 있으며
            if (withinBoundaries(newY, newX)) {
                // 해당 좌표의 값이 0인 경우
                if (map[newY][newX] == 0) {
                    // 해당 좌표의 값을 -num으로 바꾸어주고
                    map[newY][newX] = -num;
                    // 감시하는 방의 숫자를 하나 늘린다.
                    cnt++;

                    // 만약 새로운 좌표가 벽인 경우 이동을 종료한다.
                } else if (map[newY][newX] == 6) {
                    break;
                }

                // 나약 새로운 좌표가 경계 밖인 경우 이동을 종료한다.
            } else {
                break;
            }
        }
        return cnt;
    }

    // 해당 좌표가 범위 안에 존재하는지를 검사하는 함수
    private static boolean withinBoundaries(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
