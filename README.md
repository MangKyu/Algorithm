# BaekJoon Algorithm Start  

## Study LoadMap

* Intermediate와 Advanced를 도전한다.
* 코드는 항상 주석을 달고 진행하자!
* 문제를 푼 후에 성능이 좋지 않거나, 코드가 깔끔하지 않다면 다른 사람의 코드를 참고하자.

### Intermediate
1. 큐
2. 스텍
3. 데큐

### Advanced
1. 구현/시뮬레이션 : 문제의 요구사항을 구현 또는 시뮬레이션 하여 해답을 찾을 수 있음
2. 그래프 이론 : DFS/BFS와 관련된 알고리즘으로 해결
3. 동적 계획법 : 동적 계획법으로 해결
4. 부르트 포스 : 모든 경우를 탐색하여 해결


+ 수학/기하, 조합/완전 탐색, 탐욕법, 비정형(규칙찾기), 최대/최소 공약수

### Professional
Professional:다익스트라, 벨만포트, 플로이드와샬, 세그먼트 트리,펜윅트리,최소스패닝 트리, 에라토스테네스의 체,골드바흐의추측, 유니온-파인드,
 KMP,LIS, 유클리드 호제법,CCW, 라빈카프, 단절점(선),최대유량, 비트마스크, Disjoint-set, 강결합 알고리즘,인덱스트리, 위상정렬
네트워크 플로우,최대힙




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
    
