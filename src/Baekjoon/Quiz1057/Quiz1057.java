package Baekjoon.Quiz1057;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1057 {
    public static int MAX_STAGE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        MAX_STAGE = log2(Integer.parseInt(st.nextToken()));
        int count = 1;
        int n = (int)Math.pow(2, MAX_STAGE);
        int x = Integer.parseInt(st.nextToken())- 1;
        int y = Integer.parseInt(st.nextToken()) -1;

        // 큰 토너먼트를 반으로 나눠서 같은 방향에 존재하는지 아닌지를 검사하는 방법으로 풀음

        for(int i = 0 ; i < MAX_STAGE; i++){
            System.out.println("x:"+x +  "y:"+y + "n:"+ n);
            if(isSameArea(x, y, n)){
                n = n/2;
            }else{
                count += log2(n);
                break;
            }

        }

        System.out.println(count);


    }

    public static int log2(int n) {
        return (int) Math.round(Math.log(n) / Math.log(2));
    }

    public static boolean isSameArea(int x, int y, int n){
        return x/n==y/n;

    }


}

