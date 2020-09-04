package Baekjoon.Quiz16943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz16943 {

    private static int A, B, C;
    private static List<Integer> list;
    private static boolean[] isVisited;
    private static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        C = -1;
        list = new ArrayList<>();
        divide(A);
        temp = new int[list.size()];
        isVisited = new boolean[list.size()];

        dfs(0, 0);

        System.out.println(C);


    }

    // DFS를 사용해 가능한 숫자의 조합을 구한다
    private static void dfs(int index, int size) {
        // temp가 채워지면 숫자를 만든다.
        if(size == list.size()){
            // 숫자를 구한다.
            int num = makeNumber();
            //System.out.println(num);

            // 숫자가 B보다 작은 경우 큰 값을 C에 대입한다.
            if(num <= B) {
                C = Math.max(num, C);
            }
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if(!isVisited[i]){
                isVisited[i] = true;
                temp[index] = list.get(i);

                dfs(index + 1, size + 1);

                temp[index] = 0;
                //temp.remove(temp.indexOf(list.get(i)));
                isVisited[i] = false;
            }
        }

    }

    // 리스트에 있는 각각의 수를 자리수 계산하여 1개의 숫자로 만든다.
    private static int makeNumber() {
        int num = 0;
        if(temp[list.size() - 1] == 0){
            return -1;
        }

        for (int i = 0; i < temp.length; i++) {
            num += temp[i] * Math.pow(10, i);
        }
        return num;
    }

    // A를 리스트로 변환한다.
    private static void divide(int A){
        while(A != 0) {
            list.add(A%10);
            A /= 10;
        }
    }

}
