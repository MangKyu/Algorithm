package SimpleQuiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Quiz2577 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> numMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int num = 1;
        for(int i = 0 ; i <3 ; i++) {
            num *= Integer.parseInt(br.readLine());
        }

        for(int i = 0 ; i < 10; i++){
            numMap.put(i, 0);
        }


        while(num != 0){
            int key = num % 10;
            num /= 10;
            numMap.put(key, numMap.get(key) + 1);
        }

        for(int i = 0 ; i < 10; i++){
            sb.append(numMap.get(i)).append("\n");
        }
        System.out.print(sb.toString());
    }
}
