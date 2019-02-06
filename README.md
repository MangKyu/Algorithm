# BaekJoon Algorithm Start  

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
