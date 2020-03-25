package Baekjoon.Quiz1063;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1063 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ChessUnit king = new ChessUnit(st.nextToken());
        ChessUnit stone = new ChessUnit(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());

        while (cnt-- > 0) {
            String input = br.readLine();
            moveUnit(king, stone, input);
        }

        System.out.print((char) (king.row + 64));
        System.out.println((char) (king.col + 48));
        System.out.print((char) (stone.row + 64));
        System.out.println((char) (stone.col + 48));

    }

    private static void moveUnit(ChessUnit king, ChessUnit stone, String input) {
        // 새롭게 이동한 임시 왕
        ChessUnit tempKing = calculateNextPoint(input, king.row, king.col);
        // 새롭게 이동한 임시 돌
        ChessUnit tempStone = calculateNextPoint(input, stone.row, stone.col);

        // 1. 임시 왕의 좌표가 경계 안에 있고
        if (withinBoundary(tempKing.row, tempKing.col)) {
            // 2. 임시 왕의 좌표와 임시 돌의 좌표가 같으며, 임시 돌의 좌표가 경계 안에 있는 경우
            if (tempKing.row == stone.row && tempKing.col == stone.col && withinBoundary(tempStone.row, tempStone.col)) {
                // 3-1. 왕과 돌을 이동시킨다.
                moveUnit(king, tempKing);
                moveUnit(stone, tempStone);

                // 3-2. 임시 왕의 좌표와 현재 돌의 좌표가 다른 경우, 왕만 이동한다.
            } else if (tempKing.row != stone.row || tempKing.col != stone.col) {
                moveUnit(king, tempKing);
            }
        }
    }

    // 입력에 의한 다음 위치를 갖는 체스 유닛을 반환하는 함수
    private static ChessUnit calculateNextPoint(String input, int x, int y){
        ChessUnit unit = new ChessUnit(x, y);
        switch (input) {
            case "R":
                unit.row++;
                break;

            case "L":
                unit.row--;
                break;

            case "B":
                unit.col--;
                break;

            case "T":
                unit.col++;
                break;

            case "RT":
                unit.row++;
                unit.col++;
                break;

            case "LT":
                unit.row--;
                unit.col++;
                break;

            case "RB":
                unit.row++;
                unit.col--;
                break;

            case "LB":
                unit.row--;
                unit.col--;
                break;
        }

        return unit;
    }

    // 실제 유닛의 위치를 임시 유닛의 위치로 이동시키는 함수
    private static void moveUnit(ChessUnit unit, ChessUnit tempUnit){
        unit.row = tempUnit.row;
        unit.col = tempUnit.col;
    }

    // 해당 좌표가 8*8 안에 있는지 검사하는 함수
    private static boolean withinBoundary(int x, int y) {
        return x >= 1 && x <= 8 && y >= 1 && y <= 8;
    }

}

// X와 Y 좌표를 갖는 체스 말
class ChessUnit {
    int row;
    int col;

    ChessUnit(int x, int y){
        row = x;
        col = y;
    }

    ChessUnit(String input) {
        row = input.charAt(0) - 64;
        col = input.charAt(1) - 48;
    }

}