/**
 * @ClassName : Problem5
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
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

	// 다중 집합이라는 것을 명심. 교집합/합집합이 중복이 허용이 된다. 교집합은 min(a,b), 합집합은 max(a,b) -> 문제설명 참조
	public static int solution(String str1, String str2) {
		int answer = 0;	// similarity*65536 에서 소수점을 버린 결과값
		double similarity=0.0;	// 유사도
		int interCnt=0;	// 교집합 개수
		int unionCnt=0;	// 합집합 개수
		
		// 대소문자 구분 없으므로 모두 소문자로 변환
		str1=str1.toLowerCase();
		str2=str2.toLowerCase();
		
		List<String> str1List = new ArrayList<String>();
		List<String> str2List = new ArrayList<String>();
		
		// 1. 문자열 2개의 다중 집합 원소 생성
		char tmp1=0;
		char tmp2=0;
		
		for(int i=0; i<str1.length()-1; i++) {
			tmp1 = str1.charAt(i);
			tmp2 = str1.charAt(i+1);
			if( !(tmp1>=97 && tmp1<=122 && tmp2>=97 && tmp2<=122) )	// 영문자가 아닌 경우 continue
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
		
		
		// 2. 우선 중복 없는 합집합을 만든다.
		Set<String> allStr=new HashSet<String>();
		allStr.addAll(str1List);
		allStr.addAll(str2List);
		
		// 3. 중복 없는 모든 원소에 대해서, str1List와 strList2 각각에 몇 개 씩 있는지 검사한다.
		int tmpCnt1=0;
		int tmpCnt2=0;
		for(String tmpStr1 : allStr) {
			tmpCnt1=0;
			tmpCnt2=0;
			// 3-1. str1List에 tmp가 몇 개 있는지 계산
			for(String tmpStr2 : str1List)
				if(tmpStr1.equals(tmpStr2))
					tmpCnt1++;
			
			// 3-2. str2List에 tmp가 몇 개 있는지 계산
			for(String tmpStr2 : str2List)
				if(tmpStr1.equals(tmpStr2))
					tmpCnt2++;
			
			// 3-3. 둘 다 1이라면, interCnt++, unionCnt++ 사용
			if(tmpCnt1==1 && tmpCnt2==1) {
				interCnt++;
				unionCnt++;
			}
			// 3-4. 아니라면, interCnt는 더 작은 값을 덧셈, unionCnt는 더 큰 값을 덧셈
			else {
				interCnt+=Math.min(tmpCnt1, tmpCnt2);
				unionCnt+=Math.max(tmpCnt1, tmpCnt2);
			}
		}
	
		// 4. 유사도 계산
		if(interCnt==0 && unionCnt==0)
			return 65536;
		else
			similarity = (double)interCnt/unionCnt;	
		
		answer = (int)(similarity*65536);
		return answer;
	}
}
