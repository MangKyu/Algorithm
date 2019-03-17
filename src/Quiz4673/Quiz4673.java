package Quiz4673;

import java.util.LinkedList;
import java.util.Queue;

public class Quiz4673 {
    // 1 ~ 10000까지의 숫자가 생성되었는지를 저장하는 배열
    private static int[] numArr;
    // 최대 숫자가 정해진 문제이므로 static final 선언을 해준다.
    private static final int MAX_NUM = 10000;
    private static final int IS_NOT_GENERATED = 0;
    private static final int IS_GENERATED = 1;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        // index와 실제 값을 맞춰주기 위해 배열의 크기는 MAX_NUM + 1
        numArr = new int[MAX_NUM + 1];

        // 1부터 MAX_NUM 까지 d함수를 처리한다.
        for(int i = 1 ; i <= MAX_NUM; i++){
            d(i);
        }

        // 해당 숫자가 0이면 self-number라는 의미이므로 SB에 Append 해준다.
        for(int i = 1 ; i <= MAX_NUM; i++){
            if(numArr[i] == IS_NOT_GENERATED){
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    // 해당 숫자의 자릿수를 합한 굿자를 만들어낸다.
    private static int generateNum(int num){
        int sum = num;
        while(num != 0){
            sum += num %10;
            num /= 10;
        }
        return sum;
    }

    /*
    * d함수는 queue를 사용하도록 구현하였다. 해당 n에 해당하는 배열의 값이 0이 아니면 이미 해당 숫자가 호출이 된 상황이다.
    * 그럼에도 그 숫자를 queue에 넣는다면 중복으로 검사하는 무의미한 것이므로 queue에 넣지 않는다.
    * 또한 queue에서 꺼낸 숫자로 만들어낸 숫자의 배열값이 0이고(아직 한번도 호출 X), 범위에 포함되는 경우 호출이 되었다고 바꾸어주고,
    * 해당 수가 다시 만들어낸 수를 구하기 위해 Queue에 추가한다.
    * */
    private static void d(int n){
        Queue<Integer> numQueue = new LinkedList<>();

        if(numArr[n] == IS_NOT_GENERATED){
            numQueue.offer(n);
        }

        while(!numQueue.isEmpty()){
            int num = numQueue.poll();
            int generatedNum = generateNum(num);
            if(generatedNum <= MAX_NUM && numArr[generatedNum] == IS_NOT_GENERATED){
                numQueue.offer(generatedNum);
                numArr[generatedNum] = IS_GENERATED;
            }
        }
    }

}
