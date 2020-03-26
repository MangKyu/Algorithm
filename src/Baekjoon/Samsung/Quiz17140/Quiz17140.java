package Baekjoon.Samsung.Quiz17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz17140 {
    // 1~100까지의 인덱스를 활용하기 위해 선언된 101 크기의 2차원 배열 A
    private static int[][] A;
    // 사용할 행의 크기
    private static int rowSize;
    // 사용할 열의 크기
    private static int colSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        A = new int[101][101];
        rowSize = 3;
        colSize = 3;

        for (int i = 1; i <= rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= colSize; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        while (A[r][c] != k) {
            // cnt가 100 보다 커지면 -1로 바꾸고 종료한다.
            if(cnt > 100){
                cnt = -1;
                break;
            }
            // 행의개수 < 열의개수
            if (rowSize < colSize) {
                C();
            } else {
                R();
            }
            cnt++;
            //printList();
        }
        System.out.println(cnt);
    }

    // A 배열을 (수, 수의 횟수)를 갖는 리스트로 반환한다.
    private static List<Map<Integer, Integer>> C_findNumberAndAmount() {
        List<Map<Integer, Integer>> mapList = new ArrayList<>();
        Integer value;
        for (int i = 1; i <= colSize; i++) {
            Map<Integer, Integer> map = new LinkedHashMap<>();
            for (int j = 1; j <= rowSize; j++) {
                // 해당 값이 0이 아니고
                if(A[j][i]!= 0){
                    value = map.get(A[j][i]);
                    // 해시맵에 없으면 해당 숫자가 1번 존재한다고 넣어준다.
                    if (value == null) {
                        map.put(A[j][i], 1);
                    } else {
                        // 해당 숫자의 개수를 1개 늘려준다.
                        map.put(A[j][i], value + 1);
                    }
                }
            }
            mapList.add(map);
        }
        return mapList;
    }

    // R연산을 수행하는 함수
    private static void R() {
        List<Map<Integer, List<Integer>>> inversedMapList = R_inverseNumberAndAmount(R_findNumberAndAmount());
        int cnt;
        init();
        colSize = 0;
        for (int i = 0; i < inversedMapList.size(); i++) {
            cnt = 1;
            List<Integer> keyList = new ArrayList<>(inversedMapList.get(i).keySet());
            Collections.sort(keyList);
            for (Integer key : keyList) {
                for (Integer value : inversedMapList.get(i).get(key)) {
                    A[i + 1][cnt++] = value;
                    A[i + 1][cnt++] = key;
                }
            }
            colSize = Math.max(cnt - 1, colSize);
            colSize = Math.min(100, colSize);
        }

    }

    // (수, 수의 횟수)를 갖는 리스트를 (수의 횟수, 숫자리스트)의 리스트로 변환한다.
    private static List<Map<Integer, List<Integer>>> R_inverseNumberAndAmount(List<Map<Integer, Integer>> mapList) {
        List<Map<Integer, List<Integer>>> inversedMapList = new ArrayList<>();
        for (Map<Integer, Integer> map : mapList) {
            Map<Integer, List<Integer>> inversedMap = new LinkedHashMap<>();
            for (Integer key : map.keySet()) {
                Integer value = map.get(key);
                if (inversedMap.get(value) == null) {
                    inversedMap.put(value, new ArrayList<>());
                }
                inversedMap.get(value).add(key);
            }
            for (Integer key : inversedMap.keySet()) {
                Collections.sort(inversedMap.get(key));
            }
            inversedMapList.add(inversedMap);
        }

        return inversedMapList;
    }

    // A 배열을 (수, 수의 횟수)를 갖는 리스트로 반환한다.
    private static List<Map<Integer, Integer>> R_findNumberAndAmount() {
        List<Map<Integer, Integer>> mapList = new ArrayList<>();
        Integer value;
        for (int i = 1; i <= rowSize; i++) {
            Map<Integer, Integer> map = new LinkedHashMap<>();
            for (int j = 1; j <= colSize; j++) {
                // 해당 배열의 값이 0이 아니고
                if(A[i][j] != 0){
                    value = map.get(A[i][j]);
                    // 해시맵에 없으면 해당 숫자가 1번 존재한다고 넣어준다.
                    if (value == null) {
                        map.put(A[i][j], 1);
                    } else {
                        // 해당 숫자의 개수를 1개 늘려준다.
                        map.put(A[i][j], value + 1);
                    }
                }
            }
            mapList.add(map);
        }
        return mapList;
    }

    // C연산을 수행하는 함수
    private static void C() {
        List<Map<Integer, List<Integer>>> inversedMapList = R_inverseNumberAndAmount(C_findNumberAndAmount());
        int cnt;
        init();
        rowSize = 0;
        // inversedMapList에는 (갯수, 숫자의 리스트가 저장되어 있으므로 순차적으로 조회하여 values... , key 순으로 저장한다)
        for (int i = 0; i < inversedMapList.size(); i++) {
            cnt = 1;
            List<Integer> keyList = new ArrayList<>(inversedMapList.get(i).keySet());
            Collections.sort(keyList);
            for (Integer key : keyList) {
                for (Integer value : inversedMapList.get(i).get(key)) {
                    A[cnt++][i + 1] = value;
                    A[cnt++][i + 1] = key;
                }
            }
            rowSize = Math.max(cnt - 1, rowSize);
            rowSize = Math.min(100, rowSize);
        }

    }

    // A 배열을 초기화한다.
    private static void init(){
        for (int i = 1; i < A.length; i++) {
           Arrays.fill(A[i], 0);
        }
    }

    // 리스트의 내용을 스트링으로 반환한다.
    private static void printList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= rowSize; i++) {
            for (int j = 1; j <= colSize; j++) {
                sb.append(A[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
