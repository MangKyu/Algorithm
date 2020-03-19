package Quiz13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz13458 {

    // 입력 변수
    private static int N, B, C;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int [N + 1];

        int remains = 0;
        long cnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= N ; i++){
            // 먼저 총감독만큼의 감시 응시자를 빼고 총감독의 수(1)을 증가시킨다.
            A[i] -= B;
            cnt++;

            // 감시해야할 응시자가 남아있으면 필요한 부감독의 수를 계산한다.
            if(A[i] > 0){
                // 남은 응시자/부감독의 감시자수 만큼 수를 더해준다.
                cnt += A[i] / C;
                // 남은 응시자가 있다면 추가로 1명을 증가시킨다.
                remains = A[i] % C;
                if(remains != 0){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
