# 2019 Algorithm Review

## Review Quiz


* 1107번 리모컨 문제
    * 현재 좌측을 전체검사하여 시간이 144ms인데 최대 68ms 까지 줄일 수 있다고 합니다. 
    * 다른 코드를 참고해서 개선방안 찾아보자.
    * 해당 숫자를 중심으로 i=0 ~ 500000까지 좌우 탐색하는 아이디어가 떠오르긴 함.
 
 
* 1038번 감소하는수 문제
    * 현재 메모리/시간은 278312KB/1564ms 로 다른 코드들에 비해 압도적으로 낮은 성능
    * 다른 코드를 참고해서 개선방안을 찾아보자.


* 2667번 단지수 문제
    * 현재는 [N * N][N * N] 형태의 2차원 배열을 사용중인데 이를 [N][N] 으로 줄일 수 있다.
    * 입력을 upper와 lower로 나누는 부분 개선할 수 없을까?
    * bfs에서 검사할 때 반복문을 0부터 도는데, 이를 개선할 수 없을까? => 생각해봐야 함.
    * 예전에 2178 문제 풀이처럼 dx, dy를 사용하면 조금 더 개선될 듯

    
* 1890 점프 문제
    * 기존에는 그래프 문제였는데, 현재는 DP문제로 바뀜. 그러므로 DP를 이용해서 수정하도록 하자.

* 7569 토마토 문제
    * 소스코드 안에 2종류의 코드가 있는데, 객체를 새롭게 선언한 코드의 메모리가 2배 이상 큼
    * 큐에 미리 넣어두고 bfs를 돌리자!
    * 다른 사람 코드 시간되면 참고하자

* 14888 연산자 문제, 1062 가르침 문제
    * 문제를 풀면서 DFS를 사용하면 되겠다라는 아이디어는 떠올랐음!
    * 하지만 DFS를 사용하지 못하겠어서 다음에 DFS 익히고 풀어보자!

* 3190 뱀문제
    * ArrayList 대신 Queue를 사용한다.
    * checkCollapse를 반복문으로 전체 돌지 말고 그래프에 0은 아무 것도x, 1은 사과, 2는 몸의 위치를 저장하여 개선

## Review Complete
* 1527번 금만수 문제
    * BFS를 사용하여 4, 7로만 이루어진 수들만 검사하게 수정


## Tips for Optimization
1. 입력을 받기 위해서는 Scanner보다 BufferedReader를 사용하는 것이 좋다.
2. 한줄 입력이 여러번 들어오는 경우에는 split보다 StringTokenizer를 사용하여 파싱하는 것이 좋다.
3. 입력을 만들기 위해서는 StringBuilder를 사용하는 것이 좋다.
4. Array를 사용하는 것보다 ArrayList를 사용하는 것이 좋다.
5. ArrayList를 정렬하기 위해서는 Collections.sort()를 사용한다. 
6. 수학적 요소가 들어가는 문제는 수식을 만들어 해결한다. ex) Quiz1024
7. 배열을 초기화하기 위해서는 java.util.Arrays의 Arrays.fill(배열, 초기화값)을 사용한다.
8. 연속으로 있는 숫자를 입력받기 위해서는 1번 대신에 2번을 사용하자
    1. String[] inputs = br.readLine().split(""); 대신에 
    2. br.read() - '0' 그리고 다음 열에 받아야하는 연속된 숫자가 또 있다면 br.readline()으로 개행을 씹어주고, 반복한다.


=> 10871번 기준으로 1번코드와 2번코드의 성능은 아래와 같다. 이 코드는 StringBuilder의 이점과, Split의 단점을 보여준다.
* 1번 코드  
    * 코드 주소: https://github.com/MangKyu/BaekJoon/blob/master/src/Quiz10871/Quiz10871.java
    * 메모리: 17708KB and Time: 208ms
    
* 2번 코드  
    * 코드 주소: https://github.com/MangKyu/BaekJoon/blob/master/src/Quiz10871/Quiz10871_2.java
    * 메모리: 15616KB and Time: 128ms    
    



## Tips While Reviewing
1. ArrayList를 정렬하기 위해서는 Collections.sort()를 사용한다. 
2. 수학적 요소가 들어가는 문제는 수식을 만들어 해결한다. ex) Quiz1024
3. 배열을 초기화하기 위해서는 java.util.Arrays의 Arrays.fill(배열, 초기화값)을 사용한다.
4. ArrayList의 배열은 ArrayList<Integer[]> graph = new ArrayList<>(); 로 사용한다.