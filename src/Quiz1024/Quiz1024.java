package Quiz1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1024 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        for(int i = L ; i <= 100 ; i++){
            // https://danco.tistory.com/30 를 통해 시작 함수를 찾는다
            int startNum = (N - getSequenceSum(1, i-1)) / i;

            // 시작번호가 0보다 크거나 같으며, 합이 같을 경우 출력 및 종료
            if(startNum >= 0 && N == getSequenceSum(startNum, i)){
                printSequence(startNum, i);
                return;
            }
        }

        System.out.println(-1);

    }

    // n부터 시작하여 length 개를 출력한다.
    private static void printSequence(int n, int length){
        for(int i = 0 ; i < length ; i++){
            System.out.print(i + n + " ");
        }

    }

    // n부터 시작하여 length 개의 합을 구한다.
    private static int getSequenceSum(int n, int length){
        return (2*n + length-1) * (length) / 2;
    }

}