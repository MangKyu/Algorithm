package Baekjoon.Quiz1929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz1929_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNum = Integer.parseInt(st.nextToken());
        int endNum = Integer.parseInt(st.nextToken());

        // 배열은 1~ endNum까지 생성하고, 각 배열의 값은 해당 숫자가 배수가 되는 경우이다.
        // 예를 들어 1 6 인 경우 2부터 시작하여 2 4 6이 호출되므로 numArr[2]=1, numArr[4]=1, numArr[6]=1이 된다.
        // 이후에는 3이 호출되어 numArr[3]=1, numArr[6]=2 가 된다.(6은 2의배수이므로 +1, 3의배수이므로 +1)
        // 이런 과정을 통해 해당 값이 1이면 한번 호출되었으므로 소수이다.
        int[] numArr = new int[endNum + 1];
        Arrays.fill(numArr, 0);
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= endNum; i++) {
            //해당 숫자만큼 나가면서 더해주므로 j+=i로 계산을 한다.
            int j = i;
            while (j <= endNum) {
                numArr[j] ++;
                j += i;
            }
        }

        for(int i = startNum; i  < numArr.length; i++){
            if(numArr[i] == 1){
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}