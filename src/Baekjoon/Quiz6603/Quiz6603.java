package Baekjoon.Quiz6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz6603 {
    // 뽑기를 할 모든 숫자 목록을 저장하는 리스트
    private static List<Integer> numList;
    // 뽑은 숫자들을 저장하는 리스트
    private static List<Integer> pickList;
    // 출력 결과를 저장하는 리스트
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력을 분리하기 위한 변수
        StringTokenizer st;
        // 입력의 첫번째 값에 해당하는 변수로, 전체 숫자의 개수를 나타냄
        int cnt;

        // 전역 변수들을 초기화한다.
        sb = new StringBuilder();
        numList = new ArrayList<>();
        pickList = new ArrayList<>();

        while (true) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());

            // 숫자의 개수가 0인 경우 반복을 종료한다.
            if (cnt == 0) {
                break;
            }

            // 숫자의 개수만큼 입력을 받는다.
            while (cnt-- > 0) {
                numList.add(Integer.valueOf(st.nextToken()));
            }

            // index = 0부터 dfs로 로또 숫자를 뽑기 시작한다.
            dfs(0);

            // 뽑기가 종료되면 list를 초기화하고 공백 입력 한줄을 추가한다.
            pickList.clear();
            numList.clear();
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    private static void dfs(int index) {
        // 뽑은 숫자의 개수가 6개일 경우 재귀를 돌지 않고 뽑은 숫자들을 StringBuilder에 담는다.
        if (pickList.size() == 6) {
            buildNumberOfList();
        } else {
            for (int i = index; i < numList.size(); i++) {
                // 뽑은 숫자를 pickList에 추가한다.
                pickList.add(numList.get(i));
                // 현재보다 1 큰 index를 dfs 재귀를 돌린다.
                dfs(i + 1);
                // 해당 숫자는 재귀가 완료되었으므로 pickList에서 제거한다.
                pickList.remove(numList.get(i));
            }
        }
    }

    // 뽑은 숫자들을 1줄로 만들어 StringBuilder에 추가한다.
    private static void buildNumberOfList() {
        for (Integer num : pickList) {
            sb.append(num).append(" ");
        }
        sb.append("\n");
    }

}
