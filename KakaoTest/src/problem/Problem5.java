/**
 * @ClassName : Problem5
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem5 {
	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
	}

	// ���� �����̶�� ���� ���. ������/�������� �ߺ��� ����� �ȴ�. �������� min(a,b), �������� max(a,b) -> �������� ����
	public static int solution(String str1, String str2) {
		int answer = 0;	// similarity*65536 ���� �Ҽ����� ���� �����
		double similarity=0.0;	// ���絵
		int interCnt=0;	// ������ ����
		int unionCnt=0;	// ������ ����
		
		// ��ҹ��� ���� �����Ƿ� ��� �ҹ��ڷ� ��ȯ
		str1=str1.toLowerCase();
		str2=str2.toLowerCase();
		
		List<String> str1List = new ArrayList<String>();
		List<String> str2List = new ArrayList<String>();
		
		// 1. ���ڿ� 2���� ���� ���� ���� ����
		char tmp1=0;
		char tmp2=0;
		
		for(int i=0; i<str1.length()-1; i++) {
			tmp1 = str1.charAt(i);
			tmp2 = str1.charAt(i+1);
			if( !(tmp1>=97 && tmp1<=122 && tmp2>=97 && tmp2<=122) )	// �����ڰ� �ƴ� ��� continue
				continue;
			str1List.add(str1.substring(i, i+2));
		}
		
		for(int i=0; i<str2.length()-1; i++) {
			tmp1 = str2.charAt(i);
			tmp2 = str2.charAt(i+1);
			if( !(tmp1>=97 && tmp1<=122 && tmp2>=97 && tmp2<=122) )
				continue;
			str2List.add(str2.substring(i, i+2));
		}
		
		
		// 2. �켱 �ߺ� ���� �������� �����.
		Set<String> allStr=new HashSet<String>();
		allStr.addAll(str1List);
		allStr.addAll(str2List);
		
		// 3. �ߺ� ���� ��� ���ҿ� ���ؼ�, str1List�� strList2 ������ �� �� �� �ִ��� �˻��Ѵ�.
		int tmpCnt1=0;
		int tmpCnt2=0;
		for(String tmpStr1 : allStr) {
			tmpCnt1=0;
			tmpCnt2=0;
			// 3-1. str1List�� tmp�� �� �� �ִ��� ���
			for(String tmpStr2 : str1List)
				if(tmpStr1.equals(tmpStr2))
					tmpCnt1++;
			
			// 3-2. str2List�� tmp�� �� �� �ִ��� ���
			for(String tmpStr2 : str2List)
				if(tmpStr1.equals(tmpStr2))
					tmpCnt2++;
			
			// 3-3. �� �� 1�̶��, interCnt++, unionCnt++ ���
			if(tmpCnt1==1 && tmpCnt2==1) {
				interCnt++;
				unionCnt++;
			}
			// 3-4. �ƴ϶��, interCnt�� �� ���� ���� ����, unionCnt�� �� ū ���� ����
			else {
				interCnt+=Math.min(tmpCnt1, tmpCnt2);
				unionCnt+=Math.max(tmpCnt1, tmpCnt2);
			}
		}
	
		// 4. ���絵 ���
		if(interCnt==0 && unionCnt==0)
			return 65536;
		else
			similarity = (double)interCnt/unionCnt;	
		
		answer = (int)(similarity*65536);
		return answer;
	}
}
