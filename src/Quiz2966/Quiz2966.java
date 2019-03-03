package Quiz2966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Quiz2966 {

    private static ArrayList<Person> personList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        personList = new ArrayList<>();

        // 3명의 학생을 초기화한다.
        personList.add(new Person("Adrian", new char[]{'A', 'B', 'C'}));
        personList.add(new Person("Bruno", new char[]{'B', 'A', 'B', 'C'}));
        personList.add(new Person("Goran", new char[]{'C', 'C', 'A', 'A', 'B', 'B'}));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            char answer = ((char) br.read());
            //정답과 함께 해당 문제가 0번부터 몇번 문제인지 보낸다.
            isCorrect(i, answer);
        }

        printMaxInfo();
    }

    // 해당 문제의 정답이 맞으면 그 사람의 count를 높여준다. 문제의 index를 알아야 나누기 처리 가능
    private static void isCorrect(int index, char answer){
        for(Person person : personList){
            if(answer == person.answer[index % person.answer.length]){
                person.cnt++;
            }
        }
    }

    // 사람 목록을 순회하여 최댓값을 구한 후에, 일치한다면 그 사람의 이름을 출력한다.
    private static void printMaxInfo(){
        StringBuilder sb = new StringBuilder();
        int maxCount = 0;

        for(Person person: personList){
            maxCount = Math.max(maxCount, person.cnt);
        }
        sb.append(maxCount).append('\n');

        for(Person person: personList){
            if(person.cnt == maxCount){
                sb.append(person.name).append('\n');
            }
        }

        System.out.println(sb.toString());

    }

}

// 사람의 이름, 정답으로 찍을 문제, 맞춘 숫자를 갖는 클래스
class Person {
    int cnt;
    String name;
    char[] answer;

    Person(String name, char[] answer) {
        this.name = name;
        this.answer = answer;
        this.cnt = 0;
    }
}