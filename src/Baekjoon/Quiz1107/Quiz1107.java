package Baekjoon.Quiz1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Quiz1107 {

    private static Set<Integer> errorSet;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        errorSet = new HashSet<>();

        int N = Integer.parseInt(br.readLine());
        int errCnt = Integer.parseInt(br.readLine());
        StringTokenizer st;
        //에러가 0인 경우에는 입력을 받지 않음
        if(errCnt!= 0){
            st = new StringTokenizer(br.readLine());
            // 에러 목록들을 초기화
            for (int i = 0; i < errCnt; i++) {
                errorSet.add(Integer.valueOf(st.nextToken()));
            }
        }

        /* 주어진 수중에서 왼쪽과 가장 가까운 leftNum, 오른쪽과 가장 가까운 rightNum
           그리고 +/-로만 이동하는 경우를 비교하는 알고리즘입니다. */

        int startNum = 100;
        int maxNum = 1000000;
        int leftNum = -1;
        int rightNum = maxNum;

        // +/- 버튼만으로 이동하는 경우로 cnt를 초기화해둡니다.
        int cnt = Math.abs(N - startNum);

        if (N != startNum) {
            for (int i = 0; i < maxNum; i++) {
                if (i <= N && !hasError(i)) {
                    leftNum = i;
                } else if (i > N && !hasError(i)) {
                    //오른쪽을 조회하는 경우는 더 커지면 조회하는 의미가 없으므로 반복문 종료
                    rightNum = i;
                    break;
                }
            }

            // leftNum==-1인경우 좌측 숫자는 고려 x, rightNum==maxNum인 경우 우측 숫자는 고려 x
            if (leftNum == -1 && rightNum != maxNum) {
                cnt = Math.min(cnt, getNumSize(rightNum) + rightNum - N);
            } else if (leftNum != -1 && rightNum == maxNum) {
                cnt = Math.min(cnt, getNumSize(leftNum) + N - leftNum);
            } else if (leftNum != -1 && rightNum != maxNum) {
                int tempCnt = Math.min(getNumSize(leftNum) + N - leftNum, getNumSize(rightNum) + rightNum - N);
                cnt = Math.min(cnt, tempCnt);
            }

        }
        System.out.println(cnt);

    }

    // 해당 크기의 숫자를 반환합니다. 0일 경우에는 숫자를 쪼개지 않으므로 1을 반환하게 예외처리합니다.
    private static int getNumSize(int num) {
        int cnt = 0;
        if (num == 0) {
            cnt = 1;
        } else {
            while (num != 0) {
                num /= 10;
                cnt++;
            }
        }
        return cnt;
    }

    // 해당 숫자가 에러가 있는지 판단합니다. 0일 경우에는 반복문을 돌지 않으므로, 따로 처리해줍니다.
    private static boolean hasError(int num) {
        if (num == 0) {
            return errorSet.contains(num);
        } else {
            while (num != 0) {
                int temp = num % 10;
                num /= 10;
                if (errorSet.contains(temp)) {
                    return true;
                }
            }
            return false;
        }

    }
}