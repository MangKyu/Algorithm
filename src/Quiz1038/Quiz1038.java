package Quiz1038;

import java.io.*;
import java.util.*;

public class Quiz1038 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long num = -1;

        // 한자리수일때는 그대로 출력
        if(N < 10){
            num = N;

        }else if(N < 1023){
            // 최대로 감소하는 경우는 9876543210인데, 해당 경우의 N이 1022이다 그러므로 N이 1023 이상일때는 -1을 출력한다.

            // cnt는 해당 수가 몇번째로 감소하는 수인지를 저장하는 변수
            int cnt = 9;
            // 9876543210인 경우에는 오버플로우가 발생하므로 long을 사용한다.
            long i = 10;

            while(true){
                // 숫자들을 하나하나 분리한다.
                ArrayList<Long> numList = createNumList(i);

                //해당 숫자가 감소하는 숫자라면 cnt를 올려준다.
                if(isDecreased(numList)){
                    cnt++;
                    //System.out.println("Cnt: " + cnt + "    Num: " + i);
                }

                // N번째 감소 == cnt 가 되면 종료한다.
                if(cnt == N){
                    num = i;
                    break;
                }

                // 해당 숫자가 1을 간격으로 연속인지 확인한다. 연속이라면 점프한다.
                if(isSequential(numList)){
                    i = jumpNumber(numList);
                }else{
                    i++;
                }

            }
        }
        System.out.println(num);

    }

    //해당 숫자를 한자리수의 리스트로 분리한다.
    private static ArrayList<Long> createNumList(long num) {
        ArrayList<Long> numList = new ArrayList<>();

        while(num != 0){
            long temp = num % 10;
            num /= 10;
            numList.add(temp);
        }

        return numList;

    }

    //특정한 숫자로 점프한다.
    private static long jumpNumber(ArrayList<Long> numList){
        int size = numList.size();
        long num;

        // ex) 9876인 경우에 head는 가장 앞의 9이다.
        long head = numList.get(numList.size()-1);


        // Head가 9라면 자리수가 한자리 커져야 한다. 해당 로직은 98 => 210, 987 => 3210으로 점프되는 규칙을 따라 작성한다.
        if(head == 9) {
            num = size;
            // 987인 경우에는 크기에 해당하는 3부터 내림차순, 9876인 경우에는 크기에 해당하는 4부터 내림차순이므로 이를 구현한다.
            for (int i = 0; i < size; i++) {
                num = num * 10 + size - (i + 1);
            }
        }else{
            // Head가 9가 아닌 경우에는 자리가 늘어나는 경우가 아니다. => 4321 인 경우에는 5210으로, 5432인 경우에는 6210으로 점프한다.
            // 4321인 경우에는 먼저 head가 +1 되어 5가 되고, 크기 4에서 2를 뺀 2부터 내림차순이므로(5210) 이를 구현한다.
            // 54321인 경우에는 먼저 head가 +1 되어 6이 되고, 크기 5에서 2를 뺀 3부터 내림차순이므로(63210) 이를 구현한다.
            num = head+1;
            for(int i = 0 ; i < size - 1; i++){
                num = num * 10 + size - (i + 2);
            }
        }

        return num;
    }

    // 해당 숫자가 연속인지 판단한다.
    private static boolean isSequential(ArrayList<Long> numList) {

        Iterator iter = numList.iterator();
        long start = (long) iter.next();

        while (iter.hasNext()) {
            long next = (long) iter.next();
            if (next - start != 1) {
                return false;
            }
            start = next;
        }
        return true;
    }

    // 해당 숫자가 감소하는 수인지 판단한다.
    private static boolean isDecreased(ArrayList<Long> numList) {
        boolean isDecreased = true;

        Iterator iter = numList.iterator();
        long start = (long) iter.next();
        while(iter.hasNext()){
            long next = (long) iter.next();
            if(start >= next){
                isDecreased = false;
                break;
            }else{
                start = next;
            }
        }

        return isDecreased;
    }

}