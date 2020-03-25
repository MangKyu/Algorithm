package Baekjoon.Quiz11866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Quiz11866 {

    private static int N, K;
    // 사람들을 저장하고 있는 리스트
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        // 제거된 사람들을 순서대로 출력버퍼에 넣어두는 StringJoiner
        StringJoiner sj = new StringJoiner(", ", "<", ">");

        // 사람들을 순서대로 넣어준다.
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        // 제거할 사람의 index를 나타내는 변수
        int index = 0;

        // 모든 사람이 제거되었을 때 함수를 종료함
        while (!list.isEmpty()) {
            // 제거해야하는 사람의 순서를 계산한다.
            index = calculateIndex(index);
            // 제거된 사람의 순서를 sj에 추가한다.
            sj.add(Integer.toString(list.remove(index)));
        }

        // sj를 출력한다.
        System.out.println(sj.toString());
    }

    private static int calculateIndex(int index){
        // 인덱스는 기본적으로 현재 index에 k를 더하고 1을 빼준다(0부터 시작하므로)
        index = index + K - 1;

        // 인덱스가 사이즈보다 크거나 같은경우 사이즈만큼 계속 빼준다.
        while (index >= list.size()){
            index -= list.size();
        }
        return index;
    }

}
