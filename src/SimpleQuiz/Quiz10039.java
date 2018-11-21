package SimpleQuiz;

import java.util.Scanner;

public class Quiz10039 {
    //https://www.acmicpc.net/problem/10039

    public static void main(String args[]){
        int arr[] = new int[5];
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < arr.length; i++){
            arr[i] = s.nextInt();
        }
        int mean = getMeanScore(arr);
        System.out.println(mean);
    }

    public static int getMeanScore(int arr[]){
        int sum = 0;
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i] < 40){
                sum += 40;
            }else {
                sum += arr[i];
            }
        }
        return sum/arr.length;
    }



}
