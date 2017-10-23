/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 21.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package fail.protectfilm;

import java.util.Arrays;
import java.util.Comparator;

// implements Comparable�� �� ��� Ư�� �ν��Ͻ� ������ �������� "�⺻" ���Ŀ� ���� �����ϰ� �ȴ�.
// ���⼭ �� ���, Integer�� ������ compareTo�� ��������(���� ������ ū ��) �� ������ ������
// Person Ÿ������ Arrays.sort Ȥ�� Collections.sort�� �ϸ� ���̿� ���� ������������ ���ĵȴ�.
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
		Person[] p={new Person("ȫ�浿12",40), new Person("�ֹμ�123",35), new Person("������1",26)};
		
		for(int i=0; i<3; i++)
			System.out.println(p[i]);

		System.out.println("================");
		Arrays.sort(p);
		
		for(int i=0; i<3; i++)
			System.out.println(p[i]);
		
		// �̷��� Comparator�� �ϴ� ��쿡�� ���� ���Ƿ� ���Ĺ��� �� �� �ִ�. (������ ��� �⺻�� ���������� �ƴ϶�
		// �������� Ȥ�� String �񱳿����� ���ڿ� ���̸� ������ �Ѵٴ���)
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
