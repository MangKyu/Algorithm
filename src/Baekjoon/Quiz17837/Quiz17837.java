package Baekjoon.Quiz17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz17837 {

    private static int N;
    private static int[][] map;
    private static List<Unit>[][] unitMap;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        unitMap = new ArrayList[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                unitMap[i][j] = new ArrayList<>();
            }
        }

        List<Unit> units = new ArrayList<>();

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            Unit unit = new Unit(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
            units.add(unit);
            unitMap[unit.y][unit.x].add(unit);
        }

        int turn = 1;

        while (true) {
            // 턴을 실행하고, 4개 이상 쌓이면 종료한다.
            if (playTurn(units)) {
                break;
            }
            turn++;
            // 턴의 횟수가 1000을 초과하면 -1을 출력하고 종료한다.
            if (turn > 1000) {
                turn = -1;
                break;
            }
        }

        System.out.println(turn);

    }

    private static boolean playTurn(List<Unit> units) {
        boolean isFinish = false;
        for (Unit unit : units) {
            System.out.println(unit.dir);
            print2ArrayFrom1();
            int newY = unit.y + dy[unit.dir];
            int newX = unit.x + dx[unit.dir];

            if (!withinBoundaries(newY, newX) || map[newY][newX] == 2) {
                int revDir = reverseDir(unit.dir);
                isFinish = moveBlue(unit, revDir);
            } else if (map[newY][newX] == 1) {
                isFinish = moveRed(unit, newY, newX);
            } else if (map[newY][newX] == 0) {
                isFinish = moveWhite(unit, newY, newX);
            }

            if (isFinish) {
                return true;
            }

        }
        //System.out.println("===========================");
        return false;
    }

    // 이동할 칸이 빨간색인 경우에 호출하는 함수
    private static boolean moveRed(Unit unit, int newY, int newX) {
        int y = unit.y;
        int x = unit.x;
        int size = unitMap[y][x].size() - 1;
        int index = unitMap[y][x].indexOf(unit);
        for (int i = size; i >= index; i--) {
            Unit temp = unitMap[y][x].remove(i);
            temp.y = newY;
            temp.x = newX;
            unitMap[newY][newX].add(temp);
        }
        return unitMap[newY][newX].size() >= 4;
    }

    // 이동할 칸이 파란색인 경우에 호출하는 함수
    private static boolean moveBlue(Unit unit, int revDir) {
        boolean isFinish = false;
        int revY = unit.y + dy[revDir];
        int revX = unit.x + dx[revDir];
        // 반대 방향의 좌표로 이동하여 해당 칸이 빨간색, 흰색인 경우 해당 칸의 이동을 진행한다.
        if (withinBoundaries(revY, revX)) {
            if (map[revY][revX] == 1) {
                unit.dir = revDir;
                isFinish = moveRed(unit, revY, revX);
            } else if (map[revY][revX] == 0) {
                unit.dir = revDir;
                isFinish = moveWhite(unit, revY, revX);
            }
        }
        return isFinish;
    }

    // 이동할 칸이 흰색인 경우에 호출하는 함수
    private static boolean moveWhite(Unit unit, int newY, int newX) {
        //int size = unitMap[unit.y][unit.x].size();
        int y = unit.y;
        int x = unit.x;
        int index = unitMap[y][x].indexOf(unit);
        for (int i = index; i < unitMap[y][x].size(); ) {
            Unit temp = unitMap[y][x].remove(i);
            temp.y = newY;
            temp.x = newX;
            unitMap[newY][newX].add(temp);
        }
        return unitMap[newY][newX].size() >= 4;
    }

    // 방향을 반대로 뒤집어주는 함수
    private static int reverseDir(int dir) {
        switch (dir) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return 0;
        }
    }

    // 해당 좌표가 경계 안에 있는지 검사한다.
    private static boolean withinBoundaries(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N;
    }

    public static void print2ArrayFrom1() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(unitMap[i][j].size()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}

class Unit {
    int y;
    int x;
    int dir;

    Unit(int y, int x, int dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }

}