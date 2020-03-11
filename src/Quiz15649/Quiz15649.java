package Quiz15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Quiz15649 {

    private static int N, M;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            list.add(i);
            dfs();
            list.remove(list.indexOf(i));
        }
    }

    // 재귀로 해당 리스트를 순회하는 함수
    private static void dfs() {
        if (list.size() < M) {
            for (int i = 1; i <= N; i++) {
                int index = list.indexOf(i);
                // i라는 숫자가 리스트에 없거나 포함되어있지 않을 경우에 추가 -> (4,4)와 같은 중복 방지
                if (index < 0 || !list.contains(list.get(index))) {
                    list.add(i);
                    dfs();
                    list.remove(list.indexOf(i));
                }
            }
        } else {
            System.out.println(printList());
        }
    }

    // 리스트의 내용을 스트링으로 반환한다.
    private static String printList() {
        StringJoiner sj = new StringJoiner(" ");
        list.forEach(e -> sj.add(String.valueOf(e)));
        return sj.toString();
    }

}
