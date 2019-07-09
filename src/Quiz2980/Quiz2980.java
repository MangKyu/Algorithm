package Quiz2980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz2980 {

    // 신호등 목록을 저장하기 위한 큐
    private static Queue<TrafficLight> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력으로 받는 값들을 저장하기 위한 변수
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        TrafficLight trafficLight;
        queue = new LinkedList<>();

        // 현재 위치와 현재 시간
        int currentLocation = 0;
        int currentTime = 0;

        while(N-->0){
            trafficLight = new TrafficLight();
            st = new StringTokenizer(br.readLine());

            trafficLight.location = Integer.parseInt(st.nextToken());
            trafficLight.redTime = Integer.parseInt(st.nextToken());
            trafficLight.greenTime = Integer.parseInt(st.nextToken());

            queue.offer(trafficLight);
        }

        trafficLight = null;

        // 현재 위치가 목표지점에 도달하기 전까지 반복한다.
        while(currentLocation != L){
            // 현재 위치와 시간을 1씩 늘려준다.
            currentTime++;
            currentLocation++;

            // 처리해야 하는 신호등이 없지만, 큐가 비지 않은 경우 처리해야 하는 신호등을 꺼낸다.
            if(trafficLight == null && !queue.isEmpty()){
                trafficLight = queue.poll();
            }

            // 처리해야 하는 신호등이 있으며, 현재 신호등과 같은 위치에 있는 경우 별도로 처리해준다.
            if(trafficLight != null && (currentLocation == trafficLight.location)){
                int rst = currentTime % (trafficLight.redTime + trafficLight.greenTime);

                // 신호의 총 시간은 빨간색 + 파란색인데, 현재 시간으로 나눈 값이 빨간색의 범위이면 남은 빨간 신호만큼 시간을 보낸다.
                if(rst <= trafficLight.redTime){
                    currentTime += (trafficLight.redTime - rst);
                }

                // 해당 신호등이 처리되고 나면 null로 바꾸어준다.
                trafficLight = null;
            }
        }

        System.out.println(currentTime);
    }
}

class TrafficLight{
    int location;
    int redTime;
    int greenTime;
}