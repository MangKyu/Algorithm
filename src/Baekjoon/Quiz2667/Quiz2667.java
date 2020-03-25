package Baekjoon.Quiz2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz2667 {
    // 1010101 등 1에 대한 판별을 위해 상수용 EXIST 변수를 선언한다.
    private static final String EXIST = "1";
    // N은 배열의 크기에 해당한다.
    private static int N;

    // 연결관계를 나타내는 그래프의 배열과, 방문 여부를 나타내는 배열
    private static int[][] graph;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 그래프를 일렬로 표현하기위해 길이가 N*N인 배열을 선언한다.
        graph = new int[N * N][N * N];
        isVisited = new boolean[N * N];

        Arrays.fill(isVisited, false);

        // 단지의 개수를 저장하기 위한 리스트
        ArrayList<Integer> complexesNum = new ArrayList<>();

        // 관계는 바로 위아래의 줄에 의해 생기므로 2개의 Line을 기반으로 처리한다.
        String[] upperLine = br.readLine().split("");
        for (int i = 0; i < N - 1; i++) {
            String[] lowerLine = br.readLine().split("");
            //그래프의 연결상태를 그려준다.
            initGraph(i, upperLine, lowerLine);
            //이제 다음 Line으로 넘어가야 하므로, upperLine을 lowerLine으로 바꾸어주고, lowerLine은 새로 입력받는다.
            upperLine = lowerLine;
        }

        // initGraph 함수는 입력의 마지막 줄을 처리하지 않으므로 별도로 처리해준다.
        for (int i = 0; i < N - 1; i++) {
            if (upperLine[i].equals(EXIST)) {
                graph[(N - 1) * N + i][(N - 1) * N + i] = 1;
                if (upperLine[i + 1].equals(EXIST)) {
                    graph[(N-1) * N + i][(N-1) * N + (i + 1)] = graph[(N-1) * N + (i + 1)][(N-1) * N + i] = 1;
                }

            }
        }
        // 배열의 끝 N*N -1 역시 초기화해준다.
        if (upperLine[N - 1].equals(EXIST)) {
            graph[N * N - 1][N * N - 1] = 1;
        }

        // 너비우선 탐색을 통해 단지의 개수를 더한다.
        for (int i = 0; i < N * N; i++) {
            if (!isVisited[i] && graph[i][i] == 1) {
                complexesNum.add(bfs(i));
            }
        }

        // 단지의 개수를 오름차순으로 정렬한 후에 출력한다.
        Collections.sort(complexesNum);
        System.out.println(complexesNum.size());
        for (Integer index : complexesNum) {
            System.out.println(index);
        }

    }

    // 너비우선 탐색으로 단지의 개수를 찾는다. 자기자신만 연결된 경우부터 시작하므로 cnt=1로 시작한다.
    private static int bfs(int index) {
        int cnt = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);

        //System.out.println("Index: " + index + "    num: " + index + "     i: " + index);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            isVisited[num] = true;
            for (int i = 0 ; i < N * N; i++) {
                if (graph[num][i] == 1 && !isVisited[i]) {
                    //이미 큐에 들어있는 경우에는 예외처리를 하여 같은 수가 두번 들어가지 않게 한다.(중복처리)
                    if (!queue.contains(i)) {
                        cnt++;
                        queue.offer(i);
                    }
                    //System.out.println("Index: "+ index + "    num: " + num + "     i: " + i);
                }
            }
        }
        return cnt;
    }


    //그래프의 연결상태를 그려준다. 이 함수는 윗줄을 기반으로 그려주므로 가장 마지막 입력은 별도로 처리해주어야 한다.
    private static void initGraph(int lineNum, String[] upperLine, String[] lowerLine) {

        //0, 1 ,2, 3, 4 가 있다고 가정하면, 오른쪽과 아래를 검사하는데, 4에서 검사하려고 하면 IndexOutOfBounds가 발생하므로 4에 해당하는 배열의 끝은 따로 처리한다.
        for (int i = 0; i < N - 1; i++) {
            // 입력이 1인 경우에는 자기 자신을 먼저 연결해준다.
            if (upperLine[i].equals(EXIST)) {
                graph[lineNum * N + i][lineNum * N + i] = 1;

                // 바로 옆과 연결되어 있는 경우에는 연결상태를 1로 바꾸어준다.
                if (upperLine[i + 1].equals(EXIST)) {
                    graph[lineNum * N + i][lineNum * N + (i + 1)] = graph[lineNum * N + (i + 1)][lineNum * N + i] = 1;
                }

                // 바로 아래와 연결되어 있는 경우에는 연결상태를 1로 바꾸어준다.
                if (lowerLine[i].equals(EXIST)) {
                    graph[lineNum * N + i][(lineNum + 1) * N + i] = graph[(lineNum + 1) * N + i][lineNum * N + i] = 1;
                }
            }
        }

        //IndexOutOfBounds 방지를 위해 배열의 끝은 따로 처리한다.
        if (upperLine[N - 1].equals(EXIST)) {
            //배열의 끝이 1인 경우 먼저 자기 자신을 연결해준다.
            graph[lineNum * N + N - 1][lineNum * N + N - 1] = 1;


            //윗 배열의 끝과 아랫 배열이 둘다 1인 경우에는 두개를 연결해준다.
            if (lowerLine[N - 1].equals(EXIST)) {
                graph[lineNum * N + N - 1][(lineNum + 1) * N + N - 1] = graph[(lineNum + 1) * N + N - 1][lineNum * N + N - 1] = 1;
            }
        }
    }

}
