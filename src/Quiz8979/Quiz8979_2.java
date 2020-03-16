package Quiz8979;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Quiz8979_2 {

    private static Nation myNation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Nation> nationList = new ArrayList<>();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            Nation nation = new Nation(num, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            nationList.add(nation);
            if(num == k){
                myNation = nation;
            }
        }

        // Stream API를 사용하여 나보다 높은 상태의 
        System.out.println(nationList.stream().filter(nation -> isDefeat(nation)).collect(Collectors.toList()).size() + 1);

    }

    // 승리했는지를 검사하는 함수
    private static boolean isDefeat(Nation nation) {
        if(nation.gold > myNation.gold){
            return true;
        } else if (nation.gold == myNation.gold && nation.silver > myNation.silver){
            return true;
        } else if(nation.gold == myNation.gold && nation.silver == myNation.silver && nation.bronze > myNation.bronze){
            return true;
        }
        return false;
    }

}

