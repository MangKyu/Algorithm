package Quiz1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1764 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < x ; i++){
            set.add(br.readLine());
        }

        for(int i = 0; i < y ; i++){
            String input = br.readLine();
            if(set.contains(input)){
                list.add(input);
            }
        }

        Collections.sort(list);
        
        System.out.println(list.size());
        for (String s : list) {
            System.out.println(s);
        }


    }

}

