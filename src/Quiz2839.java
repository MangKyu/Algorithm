import java.util.Scanner;

public class Quiz2839 {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int weight = 0;
        weight = s.nextInt();
        getMinCount(weight);
    }

    public static void getMinCount(int weight) {
        int count = -1;
        int x = weight / 5;
        int y = weight % 5;
        if(y == 0 ){
            count = x;
        }else{
            for(int i = 0; i < x+1 ; i++){
                int temp = weight - 5 * i;
                if(temp % 3 == 0){
                    count = i;
                    count += temp/3;
                }
            }
        }
        System.out.println(count);

    }


}