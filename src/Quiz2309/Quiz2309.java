package Quiz2309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz2309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> heightList = new ArrayList<>();

        // 모든 난쟁이들의 키의 총합을 구한다.
        int sum = 0;
        for(int i = 0 ; i < 9 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            heightList.add(height);
            sum += height;
        }

        // 9명의 키와 실제 키 100의 차이를 구한다.
        int diff = sum - 100;

        // 2명의 난쟁이의 키가 diff와 같은 경우에 종료한다.
        for(int i = 0 ; i < heightList.size(); i++){
            for(int j = i + 1; j < heightList.size() ; j++){
                if(heightList.get(i) + heightList.get(j) == diff){
                    //i index부터 제거하면 j의 index가 줄어들으므로 j부터 제거한다.
                    heightList.remove(j);
                    heightList.remove(i);
                    break;
                }
            }
        }

        // 난쟁이들의 키를 오름차순으로 정렬한다.
        Collections.sort(heightList);

        for(int i = 0 ; i < heightList.size(); i++){
            System.out.println(heightList.get(i));
        }
    }



}
