package SimpleQuiz;

import java.io.*;

public class Quiz15552 {

    public static void main(String args[]) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int caseNum = Integer.parseInt(bf.readLine());
            for(int i = 0; i < caseNum; i++){
                String [] str = bf.readLine().split(" ");
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);
                bw.write(a+b +"\n");
            }

            bw.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
