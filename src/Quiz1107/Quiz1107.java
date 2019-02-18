package Quiz1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Quiz1107 {

    //FIXME Quiz 1107 is not completed
    private static Set<Integer> errorSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int errorNum = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        errorSet = new HashSet<>();
        for (int i = 0; i < errorNum; i++) {
            errorSet.add(Integer.parseInt(st.nextToken()));
        }

        Stack<Integer> numList = new Stack<>();

        int tempN = N;
        int startNum = 100;
        int count = 0;

        if (startNum != tempN) {
            while (tempN != 0) {
                int temp = tempN % 10;
                tempN = tempN / 10;
                numList.push(temp);
            }

            if (errorSet.size() != 10) {
                count += numList.size();
                int leftNum = 0;
                int rightNum = 0;
                boolean isEqual = true;

                while (!numList.empty()) {
                    int num = numList.pop();
                    if (isEqual) {
                        if (!errorSet.contains(num)) {
                            leftNum = leftNum * 10 + num;
                            rightNum = rightNum* 10 + num;
                        } else {
                            isEqual = false;

                            int leftTemp = retrieveLeft(num);
                            if (leftTemp == -1) {
                                while(!isValid(++leftNum));
                                leftTemp = retrieveLeft(9);
                            }
                            leftNum = leftNum * 10 + leftTemp;

                            int rightTemp = retrieveRight(num);
                            if(rightTemp == -1){

                                while(!isValid(++rightNum));
                                rightTemp = retrieveRight(0);
                            }
                            rightNum = rightNum * 10 + rightTemp;

                        }
                    }else{
                        int leftTemp = retrieveLeft(9);
                        leftNum = leftNum * 10 + leftTemp;

                        int rightTemp = retrieveRight(0);
                        rightNum = rightNum * 10 + rightTemp;

                    }

                    //System.out.println("Left: " + leftNum +  " Right: " + rightNum);
                }

                count += Math.min(N - leftNum , rightNum - N);
                count = Math.min(Math.abs(N - 100), count);
            }
        }

        System.out.println(count);

    }

    private static boolean isValid(int num){
        Set<Integer> numSet = new HashSet<>();

        while (num != 0) {
            int temp = num % 10;
            num = num / 10;
            numSet.add(temp);
        }
        for (Integer integer : errorSet) {
            if (numSet.contains(integer)) {
                return false;
            }
        }
        return true;

    }
    
    private static int retrieveLeft ( int num){
        for (int i = num; i >= 0; i--) {
            if (!errorSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    private static int retrieveRight ( int num){
        for (int i = num; i < 10; i++) {
            if (!errorSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

}



/*
public class Quiz1107 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int errorNum  = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> errorList = new ArrayList<>();
        for(int i = 0 ; i < errorNum ; i++){
            errorList.add(Integer.parseInt(st.nextToken()));
        }

        Stack<Integer> numList = new Stack<>();


        int tempN = N;
        int startNum = 100;
        int count = 0;

        if(startNum!=tempN){
            while(tempN != 0){
                int temp = tempN % 10;
                tempN = tempN / 10;
                numList.push(temp);
            }

            count += numList.size();
            int leftNum = 0;
            int rightNum = 0;


            while(!numList.empty()){
                int num = numList.pop();
                boolean left = true;
                boolean right = true;
                for(int i = 0 ; i < 10 ; i++){

                    if(!left && !right){
                        System.out.println("Left: " + leftNum +  " Right: " + rightNum);
                        break;
                    }

                    if(left){
                        if(num - i >= 0 && !errorList.contains(num-i)){
                            left = false;
                            leftNum = leftNum * 10 + num - i;
                            if(right && !(num + i <= 9 && !errorList.contains(num-i))){
                                rightNum = rightNum * 10 + leftNum % 10 ;
                                right = false;
                            }
                        }else if (num -i < 0){
                            //left = false;
                            if(!right){
                                leftNum = leftNum * 10 + rightNum % 10 ;
                            }
                        }
                    }

                    if(right){
                        if(num + i <= 9 && !errorList.contains(num-i)){
                            right = false;
                            rightNum = rightNum * 10 + num + i;
                            if(left){
                                leftNum = leftNum * 10 + rightNum % 10 ;
                                left = false;
                            }
                        }else if (num + i > 9){
                            //right = false;
                            if(!left){
                                rightNum = rightNum * 10 + leftNum % 10 ;
                            }
                        }
                    }
                }
            }

            if(N - leftNum <= 0 && rightNum - N >= 0){
                count += rightNum - N;
            }else if ( rightNum - N <= 0 && N-leftNum >= 0){
                count += N - leftNum;
            }else{
                count += Math.min(N - leftNum, rightNum - N);
            }

        }

        System.out.println(count);


    }

}
*/