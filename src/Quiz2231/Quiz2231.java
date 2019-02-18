package Quiz2231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz2231 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int minConstructor = 0;

        for(int i = 1 ; i <= N; i++){
            int divideSum = getDivideSum(i);
            if(N == divideSum){
                if(minConstructor == 0 || minConstructor > i){
                    minConstructor = i;
                }

            }
        }
        System.out.println(minConstructor);

    }

    public static int getDivideSum(int num){
        int sum = num;
        while (num != 0) {
            int temp = num % 10;
            num = num / 10;
            sum += temp;
        }
        return sum;
    }

}
