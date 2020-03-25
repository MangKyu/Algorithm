package Baekjoon.Samsung.Quiz14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Quiz14888 {
    private static ArrayList<NumBox> numBoxList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 연산자의 개수는 숫자의 개수 - 1이다.
        int totalCalNum = N-1;

        // 연산을 수행할 숫자들을 저장한다.
        ArrayList<Integer> numList = new ArrayList<>();

        //가지고 있는 숫자와 남은 연산자를 가지는 NumBox 목록을 지니는 리스트
        numBoxList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 연산을 수행할 숫자 목록을 초기화한다.
        while (N-- > 0) {
            numList.add(Integer.valueOf(st.nextToken()));
        }

        //연산을 수행할 첫번째 숫자로 NumBox를 초기화하고, 해당 숫자는 사용하였으므로 숫자 목록에서 제거한다.
        NumBox tempNumBox = new NumBox(numList.remove(0));

        // Map에서 0은 +, 1은 -, 2는 *, 3은 / 에 해당한다. (임의로 나만의 규칙 제정)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int calNum = Integer.parseInt(st.nextToken());
            tempNumBox.operationMap.put(i, calNum);
        }

        // 숫자 목록의 첫번째 숫자를 가지며, 연산자의 개수를 초기화한 numBox를 numBoxList에 추가한다.
        // 후에 연산을 거치다보면 numBoxList의 크기가 점점 증가한다.
        numBoxList.add(tempNumBox);

        // 연산을 할때마다 총 연산자의 수를 줄여 0보다 클때까지 수행한다.
        while (totalCalNum--> 0) {
            // 연산을 수행한 NumBox목록들을 추가하기 위한 리스트
            ArrayList<NumBox> tempList = new ArrayList<>();
            // 숫자 목록에서 첫번째 숫자를 꺼내 연산에 사용한다.
            int nextNum = numList.remove(0);

            // NumBox목록을 뒤져 계산을 한다.
            for(NumBox numBox : numBoxList){
                // 각 NumBox를 +, - , *, / 순으로 연산한다.
                for(int i = 0; i < 4 ; i++){
                    int calNum = numBox.operationMap.get(i);

                    //연산할 수 있는 연산자가 있는 경우에만 연산을 수행한다.
                    if(calNum > 0){
                        //연산을 수행하고, 사용한 연산자를 줄인 temp만 tempList에 추가한다.
                        NumBox temp = new NumBox(numBox.num, new HashMap<>(numBox.operationMap));
                        temp.calculateNum(i, nextNum);
                        tempList.add(temp);
                    }
                }
            }

            // numBoxList에 있는 NumBox들은 연산이 사용된 후이므로 필요가 없다. 그러므로 clear하고 tempList에 있는 항목들을 추가해준다.
            numBoxList.clear();
            numBoxList.addAll(tempList);
        }

        //최대값과 최솟값을 출력한다.
        printMaxAndMin();

    }

    // 연산을 통해 numBoxList에는 모든 연산자를 사용한 NumBox들만 남게된다. 그러므로 목록을 순차조회하여 최대/최솟값을 구한다.
    private static void printMaxAndMin() {
        int maxNum = -1000000000;
        int minNum = 1000000000;
        for(NumBox numBox : numBoxList){
            if(numBox.num > maxNum){
                maxNum = numBox.num;
            }

            if(numBox.num < minNum){
                minNum = numBox.num;
            }
        }

        System.out.println(new StringBuilder().append(maxNum).append("\n").append(minNum));
    }

    // 연산을 한 숫자와, 남은 연산자 목록을 지니는 클래스
    // 해시맵에서 HashMap은 아래와 같이 값을 지닌다. (0, + 연산자의 개수), (1, - 연산자의 개수), (2, *연산자의 개수), (3, /연산자의 개수)
    private static class NumBox {
        int num;
        HashMap<Integer, Integer> operationMap;

        NumBox(int num) {
            this.num = num;
            this.operationMap = new HashMap<>();
        }

        NumBox(int num, HashMap<Integer, Integer> operationMap) {
            this.num = num;
            this.operationMap = operationMap;
        }

        //연산을 수행한 후에 연산자의 수를 줄이고, 갖게되는 값을 갱신한다.
        void calculateNum(int mode, int target) {
            int opCnt = operationMap.get(mode);
            operationMap.put(mode, --opCnt);

            switch (mode) {
                case 0:
                    num += target;
                    break;
                case 1:
                    num -= target;
                    break;
                case 2:
                    num *= target;
                    break;
                case 3:
                    num /= target;
                    break;
            }
        }
    }
}
