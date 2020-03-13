package Quiz8979;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz8979 {

    private static int K;
    private static Nation[] nations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 순위를 나타내는 변수
        int cnt = 1;
        nations = new Nation[N + 1];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            nations[num] = new Nation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= nations.length - 1; i++) {
            // 자기 자신이 아니면서 승리한 경우 순위를 올린다.
            if (i != K ) {
                if (isDefeat(i)) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }

    // 승리했는지를 검사하는 함수
    private static boolean isDefeat(int i) {
        if(nations[i].gold > nations[K].gold){
            return true;
        } else if (nations[i].gold == nations[K].gold && nations[i].silver > nations[K].silver){
            return true;
        } else if(nations[i].gold == nations[K].gold && nations[i].silver == nations[K].silver && nations[i].bronze > nations[K].bronze){
            return true;
        }
        return false;
    }

}

class Nation {
    int gold;
    int silver;
    int bronze;

    Nation(int gold, int silver, int bronze) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

}