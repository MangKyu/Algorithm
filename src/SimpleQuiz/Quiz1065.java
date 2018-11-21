package SimpleQuiz;

import java.util.Scanner;

public class Quiz1065 {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        int count = 0;

        if(num < 100){
            count = num;
        }else{
            count = 99;
            for(int i = 100; i <= num ; i++){
                count += checkHanNumber(i);
            }

            if(num == 1000){
                count--;
            }

        }
        System.out.println(count);
    }

    private static int checkHanNumber(int num) {
        int a = num % 10;
        int b = num % 100 / 10;
        int c = num / 100;
        if(b * 2 == a + c){
            return 1;
        }
        return 0;
    }
}