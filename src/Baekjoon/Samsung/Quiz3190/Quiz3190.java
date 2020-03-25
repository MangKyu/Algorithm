package Baekjoon.Samsung.Quiz3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Quiz3190 {
    private static int N;
    // 뱀을 ArrayList 형태로 저장한다. 0이 Head에 해당한다.
    private static ArrayList<SnakePoint> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int time = 0;
        snake = new ArrayList<>();
        // 방향 전환을 위한 값들을 저장하는 해시맵
        HashMap<Integer, Character> turnHash = new HashMap<>();

        //(1, 1) 부터 시작하여 => 방향을 바라보고 있다. (0, 1, 2, 3) => (북, 동, 남, 서)
        snake.add(new SnakePoint(1, 1, 1));

        N = Integer.parseInt(br.readLine());
        // 0과 N+1을 경계로 처리하기 위해 배열의 크기는 N+2만큼 해준다.
        int[][] graph = new int[N + 2][N + 2];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            turnHash.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        while (!checkCollapse()) {
            // 먼저 시간을 1초 증가시키고, 머리를 1칸 방향에 맞춰 앞으로 당긴다.
            time++;
            moveHead();

            // 머리를 앞으로 했을 때, 충돌이 발생했는지 먼저 검사한다. 꼬리를 지운 후에 해당 검사를 하면
            // 머리와 꼬리가 부딪히는 경우에 예외가 발생한다.
            if(checkCollapse()){
                break;
            }

            // 머리가 이동한 위치에 사과가 있는 경우에는 사과를 먹어 없애고, 사과가 없는 경우에는 꼬리를 줄인다.
            if (graph[snake.get(0).y][snake.get(0).x] != 1) {
                moveTail();
            }else{
                graph[snake.get(0).y][snake.get(0).x] = 0;
            }

            // 현재시간에 방향을 전환해야 한다면 방향을 바꾸어준다.
            if(turnHash.containsKey(time)){
                changeDirection(turnHash.get(time));
            }
        }

        System.out.println(time);
    }

    // 충돌 여부를 검사하는 함수
    private static boolean checkCollapse() {
        SnakePoint head = snake.get(0);

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).x == head.x && snake.get(i).y == head.y) {
                return true;
            }
        }
        return head.x >= N + 1 || head.x <= 0 || head.y >= N + 1 || head.y <= 0;
    }

    // 헤드의 방향을 바꾸어주는 함수
    private static void changeDirection(char dir) {
        SnakePoint head = snake.get(0);
        switch (dir) {
            case 'L':
                head.dir = (head.dir + 3) % 4;
                break;
            case 'D':
                head.dir = (head.dir +1) % 4;
                break;
        }

    }

    // 꼬리를 제거하는 함수
    private static void moveTail() {
        if (snake.size() != 1) {
            snake.remove(snake.get(snake.size() - 1));
        }
    }

    // 헤드를 한칸 앞으로 당기는 함수
    private static void moveHead() {
        SnakePoint head = snake.get(0);
        switch (head.dir) {
            case 0:
                snake.add(0, new SnakePoint(head.y - 1, head.x, head.dir));
                break;
            case 1:
                snake.add(0, new SnakePoint(head.y, head.x + 1, head.dir));
                break;
            case 2:
                snake.add(0, new SnakePoint(head.y + 1, head.x, head.dir));
                break;
            case 3:
                snake.add(0, new SnakePoint(head.y, head.x - 1, head.dir));
                break;
        }
    }

    //좌표값과 방향값을 저장하는 클래스
    private static class SnakePoint {
        int y;
        int x;
        int dir;

        SnakePoint(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}