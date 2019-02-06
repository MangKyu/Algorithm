package Quiz2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stepNum = Integer.parseInt(br.readLine());
        int[] arr = new int[stepNum + 1];
        for (int i = 1; i <= stepNum; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        retrieveMaxScore(arr);
    }

    private static void retrieveMaxScore(int[] scoreArr) {
        int[] rstArr = new int[scoreArr.length];

        rstArr[1] = scoreArr[1];
        rstArr[2] = Math.max(scoreArr[0]+scoreArr[2], scoreArr[1]+scoreArr[2]);
        for(int i = 3 ; i < scoreArr.length ; i ++){
            int temp1 = rstArr[i-2] + scoreArr[i];
            int temp2 = rstArr[i-3] + scoreArr[i-1] + scoreArr[i];
            rstArr[i] = Math.max(temp1, temp2);
            System.out.println(i + "    " + rstArr[i]);
        }

        System.out.println(rstArr[scoreArr.length - 1]);

        /*
        while (step < scoreArr.length - 1) {

            //int step1 = scoreArr[step+1];

            if (step + 2 < scoreArr.length - 1) {
                rstArr[step + 2] = scoreArr[step + 2] + rstArr[step];
            }

            if (count < 3){
                int temp = scoreArr[step + 1] + rstArr[step];
                if (step + 2 < scoreArr.length - 1 && rstArr[step + 1] + scoreArr[step + 2] < rstArr[step + 2]) {
                    step += 2;
                    count = 1;
                    rstArr[step + 2] = rstArr[step + 1] + scoreArr[step + 2];
                } else {
                    rstArr[step + 1] = temp;
                    step++;
                    count++;
                }

            }else{
                step += 2;
                count = 1;
                rstArr[step + 2] = rstArr[step + 1] + scoreArr[step + 2];
            }
         }
            */
    }
}