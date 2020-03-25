package Baekjoon.SimpleQuiz;

import java.util.Scanner;

public class Quiz1110 {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        int count = 0;
        int temp = num;

        if (num >= 0) {
            while (true) {
                int a = temp / 10;
                int b = temp % 10;
                temp = a + b;
                temp = temp%10 + 10 * b;
                count++;
                if (temp == num) {
                    break;
                }

            }
        } else {
            count = 1;
        }

        System.out.println(count);

    }


}
