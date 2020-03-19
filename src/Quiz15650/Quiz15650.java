package Quiz15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Quiz15650 {

    private static int N, M;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        // 1부터 시작하여 숫자를 선택한다.
        select(1);
    }

    // 해당 숫자를 선택한다.
    private static void select(int index) {
        if (list.size() < M) {
            for (int i = index; i <= N; i++) {
                list.add(i);
                select(i + 1);
                list.remove(list.indexOf(i));
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
