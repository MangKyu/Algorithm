package Baekjoon.Quiz16939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz16939 {
    // 2*2*2 큐브
    private static int[] cube;
    // 돌려서 변화된 2*2*2 큐브
    private static int[] temp;
    // 종료 여부를 나타내는 변수
    private static int isFinish;
    // rotate를 위한 임시 저장용 변수
    private static int num1, num2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cube = new int[25];
        temp = new int[25];
        isFinish = 0;

        for (int i = 1; st.hasMoreTokens(); i++) {
            cube[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 6; i++) {
            // 큐브가 일치하면 종료한다.
            if(rotateCube(i) == 1){
                break;
            }
        }

        System.out.println(isFinish);

    }

    // 큐브를 회전시키는 함수
    private static int rotateCube(int index){
        // 회전시킬 temp 변수를 cube로부터 복사한다.
        System.arraycopy(cube, 1, temp, 1, 24);
        if(index == 0){
            num1 = temp[5];
            temp[5] =temp[9];
            temp[9] =temp[24];
            temp[24] =temp[1];
            temp[1] = num1;

            num2 = temp[7];
            temp[7] =temp[11];
            temp[11] =temp[22];
            temp[22] =temp[3];
            temp[3] = num2;
        } else if(index == 1){
            num1 = temp[5];
            temp[5] =temp[1];
            temp[1] =temp[24];
            temp[24] =temp[9];
            temp[9] = num1;

            num2 = temp[7];
            temp[7] =temp[3];
            temp[3] =temp[22];
            temp[22] =temp[11];
            temp[11] = num2;
        } else if(index == 2){
            num1 = temp[5];
            temp[5] =temp[17];
            temp[17] =temp[21];
            temp[21] =temp[13];
            temp[13] = num1;

            num2 = temp[6];
            temp[6] =temp[18];
            temp[18] =temp[22];
            temp[22] =temp[14];
            temp[14] = num2;
        } else if(index == 3){
            num1 = temp[5];
            temp[5] =temp[13];
            temp[13] =temp[21];
            temp[21] =temp[17];
            temp[17] = num1;

            num2 = temp[6];
            temp[6] =temp[14];
            temp[14] =temp[22];
            temp[22] =temp[18];
            temp[18] = num2;
        } else if(index == 4){
            num1 = temp[1];
            temp[1] =temp[18];
            temp[18] =temp[12];
            temp[12] =temp[15];
            temp[15] = num1;

            num2 = temp[2];
            temp[2] =temp[20];
            temp[20] =temp[11];
            temp[11] =temp[13];
            temp[13] = num2;
        } else if(index == 5){
            num1 = temp[1];
            temp[1] =temp[15];
            temp[15] =temp[12];
            temp[12] =temp[18];
            temp[18] = num1;

            num2 = temp[2];
            temp[2] =temp[13];
            temp[13] =temp[11];
            temp[11] =temp[20];
            temp[20] = num2;
        }
        return isSolved();
    }

    // 해당 큐브가 풀렸는지 검사하는 함수수
    private static int isSolved(){
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= 3; j++) {
                if (temp[1 + 4 * i] != temp[1 + 4 * i + j]) {
                    isFinish = 0;
                    return isFinish;
                }
            }
        }
        isFinish = 1;
        return isFinish;
    }

}
