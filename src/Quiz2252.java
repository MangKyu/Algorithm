import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Quiz2252 {

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> revArrayList = new ArrayList<>();
        for(int i = 0 ; i < N ; i++){
            arrayList.add(new ArrayList<>());
            revArrayList.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            arrayList.get(y-1).add(x-1);
            revArrayList.get(x-1).add(y-1);
        }

        topologicalSort(arrayList, revArrayList);

    }

    private static void topologicalSort(ArrayList<ArrayList<Integer>> arrayList, ArrayList<ArrayList<Integer>> revArrayList) {
        ArrayList<Integer> indegreeList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0 ; i < arrayList.size(); i++){
            if(arrayList.get(i).size() == 0){
                q.offer(i);
            }

        }

        while (!q.isEmpty()) {
            int index = q.poll();
            indegreeList.add(index+1);
            ArrayList<Integer> temp = revArrayList.get(index);
            for(int i = 0 ; i < temp.size(); i++){
                int tempIndex = temp.get(i);
                int arrIndex = arrayList.get(tempIndex).indexOf(index);
                arrayList.get(tempIndex).remove(arrIndex);
                if(arrayList.get(tempIndex).size()==0){
                    q.offer(tempIndex);
                }
            }
        }

        for(Integer index : indegreeList){
            System.out.print(index+ " ");
        }
    }
}
