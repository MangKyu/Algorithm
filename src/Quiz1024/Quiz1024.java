package Quiz1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1024 {
    //FIXME 왜 정답이 아닌지 모르겠음 ㅠㅠ
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        while (true){
            if(L>100){
                System.out.print(-1);
                break;
            }

            if(N%L==0 && L%2==1){
                int start = N/L - L/2;
                if(printArray(start)){
                    break;
                }else {
                    L++;
                }
            }else if (N%L != 0 && L%2!=1 && (N*10/L)%10 == 5){
                int start = Math.round((float)N/L - (float)L/2);
                if(printArray(start)){
                    break;
                }else {
                    L++;
                }

            }else{
                L++;
            }
        }

    }

    private static Boolean printArray(int start){
        if(start > 0){
            for(int i = 0 ; i < L ; i++){
                System.out.print(start+i + " ");
            }
            return true;
        }else{
            return false;
        }
    }

}

/*
public class Quiz1024 {

    //Todo https://www.acmicpc.net/problem/1021
    //Todo https://www.acmicpc.net/problem/1107
    //Todo https://www.acmicpc.net/problem/3163
    static int N;
    static int L;
    static int tempL;
    static ArrayList<Integer> arrayList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arrayList = new ArrayList<>();

        while(true){
            if(L > 100){
                System.out.println(-1);
                break;
            }


            if(L%2==0 && N%L!=0){
                addArray();
                if(isFinish()){
                    break;
                }else{
                    L++;
                    arrayList.clear();
                }
            }else if(L%2!=0 && N%L==0){
                addArray();
                if(isFinish()){
                    break;
                }else{
                    L++;
                    arrayList.clear();
                }
            }else{
                L++;
            }
        }

        // https://www.acmicpc.net/problem/1024
    }

    private static boolean isFinish() {
        if(arrayList.size()<L){
            for (Integer integer : arrayList) {
                System.out.print(integer + " ");
            }
            return true;
        }
        return false;
    }

    private static void addArray(){
        int start = Math.round(N/L - L/2);
        for(int i = 0 ; i < L ; i++){

            if(start <0 && Math.abs(start) < start+i){
                arrayList.add(start+i);
                //System.out.print(start+i + " ");
            }else if(start > 0){
                arrayList.add(start+i);
            }
        }
    }
}
*/