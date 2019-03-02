package Quiz7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz7569 {
    // 인접한 칸들을 계산하기 위한 변수들을 선언한다. for문을 각각 돌으며 수행한다.
    private static int[] dx = {1, 0, -1, 0, 0, 0};
    private static int[] dy = {0, 1, 0, -1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, 1, -1};
    private static int M, N, H;
    private static int[][][] graph;

    // 토마토의 상태가 1인 것만 큐에 추가하여 bfs를 돌리기 위해 외부에 선언하였다.
    private static Queue<Tomato> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new int[M][N][H];
        queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    graph[k][j][i] = Integer.parseInt(st.nextToken());
                    // 토마토가 현재 익은상태이면 bfs를 돌리기 위해 큐에 추가한다.
                    if (graph[k][j][i] == 1) {
                        queue.offer(new Tomato(k, j, i));
                    }
                }
            }
        }

        // bfs를 통해 돌려 나온 토마토가 최대로 숙성한 날이다.
        int cnt = bfs();

        // bfs를 돌렸는데도 토마토의 상태가 0인 것은 해당 토마토를 익게 할 수 없다는 뜻이므로 -1
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (graph[k][j][i] == 0) {
                        cnt = -1;
                        break;
                    }
                }
            }
        }

        System.out.println(cnt);
    }

    private static int bfs() {
        int cnt = 0;

        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();

            for (int i = 0; i < 6; i++) {

                // 먼저 검사할 토마토의 위치가 범위 안에 해당하는지 검사한다.
                if (tomato.x + dx[i] >= 0 && tomato.x + dx[i] < M && tomato.y + dy[i] >= 0 && tomato.y + dy[i] < N && tomato.z + dz[i] >= 0 && tomato.z + dz[i] < H) {

                    // 현재 큐에 들어있는 모든 토마토의 상태는 1이므로 새로운 토마토가 0인지만 검사하면 된다.
                    if (graph[tomato.x + dx[i]][tomato.y + dy[i]][tomato.z + dz[i]] == 0) {
                        // 0이였던 토마토가 익었으므로 큐에 넣어준다.
                        queue.offer(new Tomato(tomato.x + dx[i], tomato.y + dy[i], tomato.z + dz[i], tomato.day + 1));
                        graph[tomato.x + dx[i]][tomato.y + dy[i]][tomato.z + dz[i]] = 1;

                        // 토마토가 익은 날의 최대값을 검사하여 갱신한다.
                        if(cnt < tomato.day + 1){
                            cnt = tomato.day + 1;
                        }
                    }

                }
            }

            /* FIXME 코드의 가독성을 높이기 위해 new Tomato를 사용했더니 위 코드보다 시간은 80ms, 메모리는 2배 증가함 ㅠㅠ
            // 위 코드=> 메모리: 132324KB, 시간: 668ms
            // 아래 코드=> 메모리: 286188KB, 시간: 740ms

            for (int i = 0; i < 6; i++) {
                Tomato newTomato = new Tomato(tomato.x + dx[i], tomato.y + dy[i], tomato.z + dz[i], tomato.day + 1);
                // 먼저 검사할 토마토의 위치가 범위 안에 해당하는지 검사한다.
                if ( newTomato.x >= 0 && newTomato.x < M && newTomato.y >= 0 && newTomato.y < N && newTomato.z >= 0 && newTomato.z < H) {

                    // 현재 큐에 들어있는 모든 토마토의 상태는 1이므로 새로운 토마토가 0인지만 검사하면 된다.
                    if (graph[newTomato.x][newTomato.y][newTomato.z] == 0) {
                        // 0이였던 토마토가 익었으므로 큐에 넣어준다.
                        queue.offer(newTomato);
                        graph[newTomato.x][newTomato.y][newTomato.z] = 1;

                        // 토마토가 익은 날의 최대값을 검사하여 갱신한다.
                        if (cnt < newTomato.day) {
                            cnt = newTomato.day;
                        }
                    }

                }
            }
            */
        }
        return cnt;
    }
}

// 토마토의 위치와, 숙성된 날짜를 갖는 클래스
class Tomato {
    int x, y, z, day;

    Tomato(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.day = 0;
    }

    Tomato(int x, int y, int z, int day) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.day = day;
    }
}