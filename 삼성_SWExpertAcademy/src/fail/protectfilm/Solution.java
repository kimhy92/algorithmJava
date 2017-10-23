/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 21.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package fail.protectfilm;

import java.util.Arrays;
import java.util.Comparator;

// implements Comparable을 한 경우 특정 인스턴스 변수를 기준으로 "기본" 정렬에 따라 정렬하게 된다.
// 여기서 한 경우, Integer에 구현된 compareTo는 오름차순(작은 값부터 큰 순) 을 따르기 때문에
// Person 타입으로 Arrays.sort 혹은 Collections.sort를 하면 나이에 맞춰 오름차순으로 정렬된다.
class Person implements Comparable<Person> {
	String name;
	Integer age;
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Person o) {
		return age.compareTo(o.age);
	}
	
	
}


public class Solution {
	public static void main(String[] args) {
		Person[] p={new Person("홍길동12",40), new Person("최민수123",35), new Person("김현용1",26)};
		
		for(int i=0; i<3; i++)
			System.out.println(p[i]);

		System.out.println("================");
		Arrays.sort(p);
		
		for(int i=0; i<3; i++)
			System.out.println(p[i]);
		
		// 이렇게 Comparator를 하는 경우에는 내가 임의로 정렬법을 할 수 있다. (숫자의 경우 기본인 오름차순이 아니라
		// 내림차순 혹은 String 비교에서는 문자열 길이를 가지고 한다던지)
		Arrays.sort(p, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				if(o1.name.length() > o2.name.length()) {
					return 1;
				} else if(o1.name.length() < o2.name.length()) {
					return -1;
				} else
					return 0;
			}
		});
		
		for(int i=0; i<3; i++)
			System.out.println(p[i]);
		
	}

}
