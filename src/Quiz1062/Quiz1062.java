package Quiz1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1062 {

    // 입력으로 받는 값들을 저장하기 위한 변수
    private static int N, M;
    // 학습에 대한 단어들을 저장하는 리스트
    private static List<String> wordList;
    // 학습할 수 있는 최대값을 저장하는 변수
    private static int maxCnt;
    // a ~ z 까지 탐색을 해야 하므로, 시작점 알파벳인 a를 선언
    private static final char a = 97;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        wordList = new ArrayList<>();

        // 단어 목록을 입력으로 받아서 리스트에 추가한다.
        while(N-->0){
            wordList.add(br.readLine());
        }

        // 모든 단어는 기본적으로 a, c, i, n, t를 알아야 하는데 학습해야하는 단어가 5보다 작으면 학습가능한 단어가 0개이다.
        if (M < 5) {
            maxCnt = 0;
        } else {
            // a, c, i, n, t 를 학습하였다는 boolean 배열을 반환받는다.
            boolean[] learnArr = initLearnArr();
            backtracking(learnArr, 5, 0);
        }
        System.out.println(maxCnt);
    }

    // 학습 상태를 나타내는 boolean 배열, 학습한 단어의 수를 나타내는 값, 학습의 시작 값을 인자로 받아 dfs한다.
    private static void backtracking(boolean[] learnArr, int learnCnt, int start){
        // 학습한 단어의 수가 M일 경우에는 배울 수 있는 문자열의 개수를 검사하여 갱신한다.
        if(learnCnt == M){
            maxCnt = Math.max(maxCnt, retrieveReadableWord(learnArr));
        }else{
            // 단어의 학습을 계속 진행한다.
            for(int i = (a + start) ; i < 123 ; i++){
                // 어떤 단어가 학습되지 않은 경우 해당 단어를 학습하고, learnCnt+1과 i-97을 인자로 넘겨서 backtracking한다.
                if(!learnArr[i - 97]){
                    learnArr[i - 97] = true;
                    backtracking(learnArr, learnCnt + 1, i - 97);
                }
            }
        }
        // 학습이 진행된 후에는 다음 dfs를 위해 학습한 단어를 false상태로 바꾸어준다.
        learnArr[start] = false;
    }

    // 단어의 학습 상태를 나타내는 배열을 통해 학습가능한 문자열이 몇개인지 반환한다.
    private static int retrieveReadableWord(boolean[] learnArr){
        int cnt = 0;

        for(String word : wordList) {
            boolean success = true;
            for (int i = 0 ; i < word.length(); i++) {
                if (!learnArr[word.charAt(i)-97]) {
                    success = false;
                    break;
                }
            }

            if (success) {
                cnt++;
            }
        }
        return cnt;
    }

    // a, c, i, n, t 를 학습하였다는 boolean 배열을 초기화하여 반환받는다.
    private static boolean[] initLearnArr(){
        boolean[] learnArr = new boolean[26];
        learnArr[0] = true;
        learnArr[2] = true;
        learnArr[8] = true;
        learnArr[13] = true;
        learnArr[19] = true;
        return learnArr;
    }
}