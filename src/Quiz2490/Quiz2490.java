package Quiz2490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Quiz2490 {

    public static void main(String[] args) throws IOException {
        int N = 3;
        int M = 4;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> statusHash = new HashMap<>();
        statusHash.put(3, "A");         //도
        statusHash.put(2, "B");         //개
        statusHash.put(1, "C");         //걸
        statusHash.put(0, "D");         //윷
        statusHash.put(4, "E");         //모

        for(int i = 0 ; i < N ; i++){
            int cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                cnt += Integer.parseInt(st.nextToken());
            }
            System.out.println(statusHash.get(cnt));
        }

    }
}
