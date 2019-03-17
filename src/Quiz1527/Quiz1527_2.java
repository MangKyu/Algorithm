package Quiz1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz1527_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //금민수의 범위를 저장하는 시작, 끝번호
        long startNum = Long.parseLong(st.nextToken());
        long endNum = Long.parseLong(st.nextToken());

        //BFS로 문제를 풀기 위해 Queue를 이용한다.
        Queue<Long> numQueue = new LinkedList<>();
        // 금민수를 카운트하는 변수
        long cnt = 0;

        //금민수의 여부를 판단하기 위해 0을 큐에 넣는다. 그러면 4, 7부터 탐색을 시작할 것이다.
        numQueue.offer((long)4);
        numQueue.offer((long)7);

        while(!numQueue.isEmpty()){
            long num = numQueue.poll();
            if(num <= endNum){
                if(num >= startNum){
                    cnt++;
                }
                numQueue.offer(generateKeumMinNum(num, 4));
                numQueue.offer(generateKeumMinNum(num, 7));
            }
        }

        System.out.println(cnt);
    }

    //4 또는 7을 입력으로 받아 금민수를 만든다.
    private static long generateKeumMinNum(long num, int keumMinIndex){
        return num *10 + keumMinIndex;
    }

}
