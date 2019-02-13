package Quiz1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Quiz1021 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> arrayList = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            arrayList.add(i+1);
        }

        int rotateCount = 0;
        int start = 1;

        for(int i = 0 ; i < M ; i++){
            int target = Integer.parseInt(st.nextToken());
            int targetIndex = arrayList.indexOf(target);
            if(target != start){
                int startIndex = arrayList.indexOf(start);
                int left;
                int right;
                if(startIndex > targetIndex){
                    left = startIndex - targetIndex;
                    right = arrayList.size() + targetIndex - startIndex;
                }else {
                    left = arrayList.size() + startIndex - targetIndex;
                    right = targetIndex - startIndex;
                }
                rotateCount += Math.min(left, right);

            }


            arrayList.remove(targetIndex);
            if(arrayList.size()==0){
                break;
            }
            if(targetIndex == arrayList.size()){
                start = arrayList.get(0);
            }else{
                start = arrayList.get(targetIndex);
            }

        }
        System.out.println(rotateCount);

    }

}