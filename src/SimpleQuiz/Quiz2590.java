package SimpleQuiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Quiz2590 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] temp = br.readLine().toCharArray();
        char[] inputs = new char[8];

        for(int i = 0 ; i < 16 ; i+=2){
            inputs[i/2] = temp[i];
        }

        if(Arrays.equals(inputs, new char[]{'1', '2', '3', '4', '5', '6', '7', '8'})){
            System.out.println("ascending");
        }else if(Arrays.equals(inputs, new char[]{'8', '7', '6', '5', '4', '3', '2', '1'})){
            System.out.println("descending");
        }else{
            System.out.println("mixed");
        }
    }
}
