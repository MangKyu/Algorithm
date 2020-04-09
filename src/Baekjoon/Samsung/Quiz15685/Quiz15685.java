package Baekjoon.Samsung.Quiz15685;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz15685 {

    // 드래곤 커브 배열
    private static DragonCurve[] curves;
    // 상하좌우 이동을 위한 변수
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    // 드래곤커브 좌표를  표시하는 변수
    private static boolean[][] isDragonCurve;
    // 임시 좌표값을 저장하는 변수와 리스트
    private static Point temp;
    private static List<Point> tempList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        curves = new DragonCurve[N];
        tempList = new ArrayList<>();
        isDragonCurve = new boolean[100][100];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            DragonCurve dragonCurve = new DragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            curves[i] = dragonCurve;
            // 드래곤 커브의 입력을 받은 후에 tail 값을 계산한다.
            dragonCurve.t_x = dragonCurve.h_x + dx[dragonCurve.d];
            dragonCurve.t_y = dragonCurve.h_y + dy[dragonCurve.d];
        }

        for (int i = 0; i < N; i++) {
            DragonCurve dragonCurve = curves[i];
            // 해당 드래곤커브의 차수까지 커브시킨다.
            for (int j = 1; j <= dragonCurve.g; j++) {
                curves(dragonCurve);
            }
        }

        // 드래곤 커브를 탐색하여 해당 좌표를 true로 바꾸어준다.
        for (int i = 0; i < N; i++) {
            DragonCurve dragonCurve = curves[i];
            isDragonCurve[dragonCurve.h_y][dragonCurve.h_x] = true;
            isDragonCurve[dragonCurve.t_y][dragonCurve.t_x] = true;
            for (Point point : dragonCurve.pointList) {
                isDragonCurve[point.y][point.x] = true;
            }
        }

        System.out.println(checkCurves());

    }

    // 해당 좌표롤 계산하여 드래곤 커브에 포함된 정사각형의 갯수를 파악한다.
    private static int checkCurves() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isDragonCurve[i][j] && isDragonCurve[i + 1][j + 1] && isDragonCurve[i][j + 1] && isDragonCurve[i + 1][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 해당 드래곤커브의 좌표를 회전시킨다.
    // 회전은 꼬리를 0, 0으로 맞춘 다음에 회전을 시키고 다시 복원시키는 원리를 이용한다.
    private static Point curvePoint(int x, int y, int t_x, int t_y) {
        Point point = new Point();

        if (x >= 0 && y >= 0) {
            point.x = -y;
            point.y = x;
        } else if (x <= 0 && y >= 0) {
            point.x = -y;
            point.y = x;
        } else if (x <= 0 && y <= 0) {
            point.x = -y;
            point.y = x;
        } else {
            point.x = -y;
            point.y = x;
        }

        point.x += t_x;
        point.y += t_y;

        return point;
    }

    // 해당 드래곤커브를 회전시킨다.
    private static void curves(DragonCurve dragonCurve) {
        tempList.clear();

        // 1. 꼬리좌표를 포함시킨다.
        tempList.add(new Point(dragonCurve.t_x, dragonCurve.t_y));
        // 2. 좌표 리스트를 회전시킨 좌표들을 포함시킨다.
        for (Point point : dragonCurve.pointList) {
            tempList.add(curvePoint(point.x - dragonCurve.t_x, point.y - dragonCurve.t_y, dragonCurve.t_x, dragonCurve.t_y));
        }
        // 3. 기존의 좌표들도 포함시킨다.
        tempList.addAll(dragonCurve.pointList);

        dragonCurve.pointList.clear();
        dragonCurve.pointList.addAll(tempList);

        // 4. 머리를 이동시킨 좌표를 꼬리로 바꾸어준다.
        temp = curvePoint(dragonCurve.h_x - dragonCurve.t_x, dragonCurve.h_y - dragonCurve.t_y, dragonCurve.t_x, dragonCurve.t_y);
        dragonCurve.t_x = temp.x;
        dragonCurve.t_y = temp.y;
    }


}

class DragonCurve {
    // 머리의 x, y 좌표
    int h_x;
    int h_y;
    int d;
    int g;
    // 꼬리의 x, y 좌표
    int t_x;
    int t_y;
    List<Point> pointList;

    public DragonCurve(int x, int y, int d, int g) {
        this.h_x = x;
        this.h_y = y;
        this.d = d;
        this.g = g;
        pointList = new ArrayList<>();
    }

}
