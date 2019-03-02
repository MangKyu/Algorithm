package Quiz1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1527 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 0;

        // A부터 B까지 탐색한다.
        for(int i = A ; i <= B ; i++){
            if(isKeumManNumber(i)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    // 4나 7을 포함하고 있으면 바로 false를 return시킨다.
    private static boolean isKeumManNumber(int num){
        while(num != 0 ){
            int temp = num % 10;
            if(temp != 4 && temp != 7){
                return false;
            }
            num /= 10;
        }
        return true;
    }

}
