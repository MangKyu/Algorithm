# BaekJoon Algorithm Start  

## Study LoadMap

* Intermediate와 Advanced를 도전한다.
* 코드는 항상 주석을 달고 진행하자!

### Intermediate
1. 큐
2. 스텍
3. 데큐

### Advanced
1. 구현/시뮬레이션      문제의 요구사항을 구현 또는 시뮬레이션 하여 해답을 찾을 수 있음
2. 그래프 이론      DFS/BFS와관련된 알고리즘으로 해결
3. 동적 계획법      동적 계획법으로 해결
5. 자료구조         다양한 자료구조와 관련된 기법들을 이용하여 해결
6. 부르트 포스       모든 경우를 탐색하여 해결


+ 수학/기하, 조합/완전 탐색, 탐욕법, 비정형(규칙찾기), 최대/최소 공약수

### Professional
Professional:다익스트라, 벨만포트, 플로이드와샬, 세그먼트 트리,펜윅트리,최소스패닝 트리, 에라토스테네스의 체,골드바흐의추측, 유니온-파인드,
 KMP,LIS, 유클리드 호제법,CCW, 라빈카프, 단절점(선),최대유량, 비트마스크, Disjoint-set, 강결합 알고리즘,인덱스트리, 위상정렬
네트워크 플로우,최대힙




## Tips for Optimization
1. 입력을 받기 위해서는 Scanner보다 BufferedReaader를 사용하는 것이 좋다.
2. 한줄 입력이 여러번 들어오는 경우에는 split보다 StringTokenizer를 사용하여 파싱하는 것이 좋다.
3. 입력을 만들기 위해서는 StringBuilder를 사용하는 것이 좋다.
4. Array를 사용하는 것보다 ArrayList를 사용하는 것이 좋다.

## Have to Refactor  
1. 위의 Tip을 참고하여 Quiz10871을 수정하라.

  

## Review Complete

### Quiz 1697
* boolean [][] visited과 Node 클래스를 따로 사용하지 않고, int[][] visited로 사용하여 0일 경우에는 방문 x, 1 이상일 경우에는 Depth를 저장한다고 선언한다.
=> http://ggmouse.tistory.com/361
* 그 외에 코드를 간략화 할 수 있음
=> https://zoonvivor.tistory.com/90
