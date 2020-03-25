package Baekjoon.Samsung.Quiz14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Quiz14503 {
    private static int N;
    private static int M;
    private static int graph[][];
    private static Robot robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        st = new StringTokenizer(br.readLine());
        robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 문제에 따르면 0이 북, 1이 동, 2가 남, 3이 서 이므로 주의한다!
        int dir = Integer.parseInt(st.nextToken());
        robot.direction = (dir ==1 || dir ==3) ? 4 - dir : dir;

        //robot.direction = dir;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(cleaning());
    }

    private static int cleaning() {
        int cnt = 0;

        while (true) {

            // 현재 위치를 청소한다.
            if (graph[robot.y][robot.x] == 0) {
                //System.out.println("####CLEANING ("+robot.y + ", " + robot.x + ", " + robot.direction + ")");
                graph[robot.y][robot.x] = 2;
                cnt++;
            }

            int direction = searchCleanDirection();
            switch (direction){
                case 0:
                    robot.direction = direction;
                    robot.y -= 1;
                    break;
                case 1:
                    robot.direction = direction;
                    robot.x -= 1;
                    break;
                case 2:
                    robot.direction = direction;
                    robot.y += 1;
                    break;
                case 3:
                    robot.direction = direction;
                    robot.x += 1;
                    break;
                case 4:
                    // 현재 위치에서 탐색이 불가능한 경우 but 후진이 가능한 경우
                    if(robot.direction == 0 && robot.y + 1 < N && graph[robot.y+1][robot.x] != 1 ){
                        robot.y ++;
                    }else if(robot.direction == 1 && robot.x + 1 < M && graph[robot.y][robot.x+1] != 1){
                        robot.x ++;
                    }else if(robot.direction == 2 && robot.y -1 >= 0 && graph[robot.y-1][robot.x] != 1){
                        robot.y --;
                    }else if(robot.direction == 3 && robot.x - 1 >= 0 && graph[robot.y][robot.x-1] != 1){
                        robot.x --;
                    }else{
                        // 현재 위치에서 탐색이 불가능한 경우 and 후진도 불가능한 경우
                        return cnt;
                    }
                    break;
                default:
                    System.out.println("#####################");
                    break;
            }


        }

    }

    // 로봇의 현재 탐색위치를 바탕으로 좌측부터 4번 탐색하여, 탐색이 가능한 경우 탐색하는 방향의 index를 반환하고 불가능하면 4를 반환한다.
    private static int searchCleanDirection() {
        int searchCnt = 0;

        while (searchCnt != 4) {
            if (robot.direction == 0) {
                if (robot.x - 1 >= 0 && graph[robot.y][robot.x-1] == 0) {
                    return 1;
                } else {
                    searchCnt++;
                    robot.direction++;
                }
            } else if (robot.direction == 1) {
                if (robot.y + 1 < N && graph[robot.y + 1][robot.x] == 0) {
                    return 2;
                } else {
                    searchCnt++;
                    robot.direction++;
                }
            } else if (robot.direction == 2) {
                if (robot.x + 1 < M && graph[robot.y][robot.x+1] == 0) {
                    return 3;
                } else {
                    searchCnt++;
                    robot.direction++;
                }
            } else if (robot.direction == 3) {
                if (robot.y - 1 >= 0 && graph[robot.y-1][robot.x] == 0) {
                    return 0;
                } else {
                    searchCnt++;
                    robot.direction = 0;
                }
            }else{
                System.out.println("#####################");
            }
        }

        return searchCnt;

    }

}

// 로봇의 x좌표, y좌표, 현재 방향을 갖는 클래스
class Robot {
    int x;
    int y;
    int direction;

    Robot(int y, int x) {
        this.y = y;
        this.x = x;
    }

}

/* TEST CASE

10 10
3 3 1
1 1 1 1 1 1 1 1 1 1
1 0 0 1 0 1 0 1 0 1
1 1 0 0 1 1 0 0 1 1
1 0 0 0 0 0 0 1 0 1
1 0 1 1 1 0 0 1 1 1
1 1 1 0 0 0 0 0 0 1
1 0 0 0 0 0 1 1 1 1
1 0 1 1 0 0 1 1 0 1
1 0 0 0 0 0 0 0 0 1
1 1 0 0 1 0 0 0 1 1


 */