/**
 * @ClassName : Solution2
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 17.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package swimpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 더 간결하게 푼 것 (내가 기존에 푼 것보다 훨씬 빠름)
public class Solution2 {
	static int[] fee=new int[4];		// 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권의 요금
	static int[] usePlan=new int[12];	// 이용 계획 (월별 이용일수)
	static int min;	// 이용권의 최대가격은 3천원이고, 따라서 최대 이용가격은 1년 모두 이용하면서 모두 1일 이용권으로 이용하는
						// 3000*365이다. 따라서 최대값보다 큰 수를 선언하기 위해 *2 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc; i++) {
			min=3000*365*2;
			String[] s=br.readLine().split(" ");
			for(int j=0; j<fee.length; j++)
				fee[j]=Integer.parseInt(s[j]);
			s=br.readLine().split(" ");
			for(int j=0; j<usePlan.length; j++) {
				usePlan[j]=Integer.parseInt(s[j]);;
			}
			min=fee[3];	// 1년 이용 요금을 기준 값
			solve(0,0);
			System.out.println("#"+i+" "+min);
		}
	}
	
	/*
	month : 계산한 월 (12월까지만 계산해야함)
	sum : 현재까지의 요금 합
	 */
	public static void solve(int month, int sum) {
		if(month>=12) {
			min=Math.min(min, sum);
			return;
		}
		
		if(usePlan[month]==0) {	// 현재 달에 이용일수가 0일이면 요금을 더하지 않는다.
			solve(month+1,sum);
		}
		else {
			solve(month+1,sum+usePlan[month]*fee[0]);	// 1일 이용권
			solve(month+1,sum+fee[1]);	// 1달 이용권
			solve(month+3,sum+fee[2]);
		}
	}
}
