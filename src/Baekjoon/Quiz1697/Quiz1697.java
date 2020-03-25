package Baekjoon.Quiz1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Quiz1697 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] strArr = br.readLine().split(" ");
        int x = Integer.parseInt(strArr[0]);
        int y = Integer.parseInt(strArr[1]);
        bfs(x, y);
    }

    private static void bfs(int start, int end){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int cnt = 0;

        q.add(new Node(start, 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            visited[node.num] = true;

            if(node.num == end){
                cnt = node.height;
                break;
            }

            if(node.num - 1 >= 0 && !visited[node.num - 1]){
                q.add(new Node(node.num-1,node.height+1));
            }

            if(node.num+1 <= 100000 && !visited[node.num+1]){
                q.add(new Node(node.num+1, node.height+1));
            }

            if(node.num*2 <= 100000 && !visited[node.num*2]){
                q.add(new Node(node.num*2, node.height+1));
            }

        }

        System.out.println(cnt);

    }

    private static class Node{
        public int num;
        public int height;
        public Node(int num, int height){
            this.num=num;
            this.height = height;
        }

    }
}
