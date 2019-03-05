package Quiz6581;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz6581 {

    // br 태그 ,hr 태그의 키와 값
    private static final String BR_TAG = "<br>";
    private static final String BR_VALUE = "\n";
    private static final String HR_TAG = "<hr>";
    private static final String HR_VALUE = "--------------------------------------------------------------------------------";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        int length = 0;

        // 이전에 사용한 값이 BR인지, HR인지 저장하는 변수
        boolean isBr = false;
        boolean isHr = false;

        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                switch (word) {
                    // 입력받은 단어가 BR태그인 경우
                    case BR_TAG:
                        // 이전에 입력받은 단어가 HR인 경우 개행을 2번한다.
                        if (length != 0 && isHr) {
                            sb.append(BR_VALUE);
                            sb.append(BR_VALUE);
                            isBr = true;

                            //저장된 단어의 길이가 0이 아닌 경우 & 이전 입력이 BR이 아닌경우
                        } else if (length !=0 && !isBr) {
                            sb.append(BR_VALUE);
                            isBr = false;

                            //입력이 연속해서 BR로 들어온 경우
                        }else if(length == 0 && !isBr){
                            sb.append(BR_VALUE);
                            isBr = true;
                        }
                        length = 0;
                        break;

                    // 입력받은 단어가 HR태그인 경우
                    case HR_TAG:
                        // 저장된 단어가 존재하는 경우에 개행처리
                        if (length != 0) {
                            sb.append(BR_VALUE);
                            //System.out.println(sb.toString());
                        }
                        // --- 값을 출력에 추가한다.
                        sb.append(HR_VALUE);
                        length = 80;
                        isBr = false;
                        isHr = true;
                        //System.out.println(HR_VALUE);
                        break;


                    // 입력받은 단어가 태그가 아닌 경우
                    default:
                        // 길이가 80이 넘는 경우에는 저장된 단어를 모두 출력하고 새로운 단어를 저장한다.
                        if (length + word.length() >= 80) {
                            sb.append(BR_VALUE);
                            length = 0;
                        }
                        length += word.length() + 1;
                        sb.append(word).append(" ");
                        isBr = false;
                        isHr = false;
                        break;
                }
            }
        }

        System.out.println(sb.toString());

    }
}

/* Test Case for Test2
Hallo, dies ist eine
ziemlich lange Zeile, die in Html
aber nicht umgebrochen wird.
<br>
Zwei <br> <br> produzieren zwei Newlines. <br> <br> <br>
Es gibt auch noch das tag <hr> was einen Trenner darstellt. <br> <hr> <br>
Zwei <hr> <hr> produzieren zwei Horizontal Rulers. <hr> <br> <hr>
Achtung       mehrere Leerzeichen irritieren

Html genauso wenig wie


mehrere Leerzeilen.
CCCCCCC

\n ----

 */