package Quiz10798;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Quiz10798 {

    private static final int MAX_INPUT_LENGTH = 15;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        List<char[]> charsList = new ArrayList<>();
        int N = 5;

        while (N-- > 0) {
            charsList.add(br.readLine().toCharArray());
        }

        for (int i = 0; i < MAX_INPUT_LENGTH; i++) {
            for (char[] input : charsList) {
                if (i < input.length) {
                    sb.append(input[i]);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
