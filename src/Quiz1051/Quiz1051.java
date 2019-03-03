package Quiz1051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1051 {
    // 각 꼭지점의 값들을 저장하는 변수
    private static int[][] square;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        square = new int [N][M];

        for(int i = 0 ; i < N ; i++){
            String[] inputs = br.readLine().split("");
            for(int j = 0 ; j < M ; j++){
                square[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        System.out.println(getMaxArea());

    }

    private static int getMaxArea() {
        // 가로와 세로의 길이 중 최솟값을 구한다. 3 x 5인 직사각형에서 5를 검사하면 IndexOutOfBounds가 발생하므로
        int length = Math.min(N, M);

        // 현재의 length부터 검사해보고 꼭지점이 동일한 정사각형이 존재하면 검사하는 길이를 줄이기 위해 do while을 사용하였다.
        do{
            for(int i = 0 ; i <= N - length; i++){
                for(int j = 0 ; j <= M - length; j++){
                    // 꼭지점의 값 4개가 같다면 현재의 길이를 반환한다.
                    if(isSame(square[i][j], square[i][j + length - 1], square[i + length - 1 ][j], square[i + length - 1][j + length - 1])){
                        return (length) * (length);
                    }
                }
            }
        }while(length-->1);

        return 1;
    }

    // 4개의 꼭지점의 값들이 같은지 검사하는 함수
    private static boolean isSame(int x, int y, int z, int w){
        //System.out.println("X:" + x +"  Y:" +y + "   z: "+ z + "   W :" + w);
        return x==y && z==w && x==z;
    }

}