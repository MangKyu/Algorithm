package Baekjoon.Quiz3048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz3048 {

    //개미 배열
    private static Ant[] antArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        //개미 집단1, 개미집단2를 입력으로 받고, 개미의 수에 해당하는 배열의 크기를 선언한다.
        char[] group1 = br.readLine().toCharArray();
        char[] group2 = br.readLine().toCharArray();
        antArr = new Ant[group1.length + group2.length];

        // 개미 집단1은 오른쪽을 바라보고 있다.
        for(int i = 0 ; i < group1.length; i++) {
            antArr[group1.length - 1 - i] = new Ant(group1[i], false);
        }

        // 개미 집단2는 왼쪽을 바라보고 있다.
        for(int i = 0 ; i < group2.length; i++){
            antArr[group1.length + i] = new Ant(group2[i], true);
        }

        // 몇초의 시간이 흐르는지 입력을 받는다.
        int time = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < time ; i++){
            jump();
        }

        for(int i = 0 ; i < antArr.length; i++){
            System.out.print(antArr[i].id);
        }
    }

    private static void jump() {
        for(int i = 0 ; i < antArr.length - 1 ; i++){

            //개미가 오른쪽을 바라보고 있고, 그 개미와 오른쪽에있는 개미의 방향이 다른 경우에 점프한다.
            if(! antArr[i].isLeft && antArr[i].isLeft != antArr[i+1].isLeft){
                Ant tempAnt = antArr[i+1];
                antArr[i+1] = antArr[i];
                antArr[i] = tempAnt;
                //점프하면 오른쪽에 있는 개미는 점프한 개미이므로 i++ 하여 그 다음 개미를 검사한다.
                i++;
            }
        }
    }

    // 개미의 고유한 id와 보고있는 방향을 갖는다.
    private static class Ant{
        char id;
        boolean isLeft;
        Ant(char id, boolean isLeft){
            this.id = id;
            this.isLeft = isLeft;
        }
    }
}
