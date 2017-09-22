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
		int n = Integer.parseInt(br.readLine());
		System.out.println(solve(n));
	}
	
	public static int solve(int n) {
		if(n==reverseNum(n))	// 입력받은 숫자가 펠린드롬 넘버인 경우 그 숫자 그대로 리턴
			return n;
		
		for(int i=0; i<3; i++) {	// 3번까지 연산해서 펠린드롬 넘버가 나와야 한다.
			// F(n) 계산. 현재 숫자에 대해 F(n) 계산
			n+=reverseNum(n);
			
			if(n==reverseNum(n))	// 펠린드롬 넘버이면 그 숫자 리턴
				return n;
			
			if(n>=10000)	// 연산 과정에서 결과가 10000이상이 되면 불가능
				return -1;
		}
		
		return -1;	// 만들 수 없는 경우
	}
	
	// Integer에 reverse 메소드 있을 거니 찾아보기
	public static int reverseNum(int num) {
		String res="";
		
		while(num!=0) {
			res += num%10;
			num/=10;
		}
		
		return Integer.parseInt(res);
	}
}















