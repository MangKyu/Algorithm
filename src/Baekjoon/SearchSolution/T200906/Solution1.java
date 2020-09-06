package Baekjoon.SearchSolution.T200906;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution1 {

    private int answer = 0;
    private int absValue = 0;
    
    public int solution(int[] A){
        Arrays.sort(A);
        Set<Integer> set = Arrays.stream(A).boxed().filter(a -> a > 0).collect(Collectors.toSet());
        for (int i = 0; i < A.length; i++) {
            if(A[i] > 0 ){
                return answer;
            } else {
                absValue = Math.abs(A[i]);
                if(set.contains(absValue)){
                    return absValue;
                }

            }
        }

        return answer;
    }

}
