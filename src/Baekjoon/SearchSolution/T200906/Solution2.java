package Baekjoon.SearchSolution.T200906;

public class Solution2 {

    private boolean[] isVisited;
    private char[] arr, temp;
    private int sum;
    private String answer = "";

    public String solution(int A, int B, int C) {
        isVisited = new boolean[A + B + C];
        arr = new char[A + B + C];
        temp = new char[A + B + C];
        sum = A + B + C;

        for (int i = 0; i < sum; i++) {
            if (i < A) {
                arr[i] = 'a';
            } else if (A <= i && i < A + B) {
                arr[i] = 'b';
            } else {
                arr[i] = 'c';
            }
        }

        dfs(0, 0);

        return answer;
    }

    private void dfs(int index, int size) {
        String str = String.valueOf(temp).trim();
        if (!(str.contains("aaa") || str.contains("bbb") || str.contains("ccc"))) {
            if (answer.length() < str.length()) {
                answer = str;
            }
        }

        for (int i = 0; i < sum; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                temp[index] = arr[i];

                dfs(index + 1, size + 1);

                temp[index] = '\0';
                isVisited[i] = false;
            }
        }
    }

}
