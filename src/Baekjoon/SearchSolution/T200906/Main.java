package Baekjoon.SearchSolution.T200906;

public class Main {

    public static void main(String[] args) {

        System.out.println(new Solution1().solution(new int[]{3, 2, -2, 5, 3}));

        int[][] A = {{5, 4, 4},{4, 3, 4},{3, 2, 4},{2, 2, 2},{3, 3, 4},{1, 4, 4},{4, 1, 1}};
        System.out.println(new Solution3().solution(A));

        System.out.println(new Solution2().solution(6, 1, 1));
        System.out.println(new Solution2().solution(1, 3, 1));
        System.out.println(new Solution2().solution(0, 1, 8));

    }

}
