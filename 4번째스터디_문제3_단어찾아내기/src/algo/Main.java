/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 가까운 두 점
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int wordCnt=st.countTokens();
		String[] words = new String[wordCnt];
		
		// 조건식에 i<st.countTokens() 하면 안됨
		// st.nextToken() 하면 토큰이 제거된다. 그래서 st.countTokens()도 줄어든다.
		// 주의!
		for(int i=0; i<wordCnt; i++)
			words[i] = st.nextToken();
		
		solve(words);
	}
	
	public static void solve(String[] words) {
		int[] res={0,0};
		
		for(int i=0; i<words.length; i++) {
			if(checkRuleOne(words[i]))
				res[0]++;
			if(checkRuleTwo(words[i]))
				res[1]++;
		}
		
		for(int tmp : res)
			System.out.println(tmp);
	}
	
	public static boolean checkRuleOne(String word) {
		for(int i=0; i<word.length()-1; i++)
			if(checkVowels(word.charAt(i)) && checkVowels(word.charAt(i+1)))
				return true;
		return false;
	}
	
	public static boolean checkRuleTwo(String word) {
		for(int i=0; i<word.length()-2; i++)
			if(!checkVowels(word.charAt(i)) && !checkVowels(word.charAt(i+1)) && !checkVowels(word.charAt(i+2)))
				return true;
		return false;
	}
	
	public static boolean checkVowels(char ch) {
		if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
			return true;
		return false;
	}
}















