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
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 가까운 두 점
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			nums[i]=Integer.parseInt(st.nextToken());
		
		solve(n, nums);
	}
	
	public static void solve(int n, int[] nums) {
		String maxTmp="", minTmp="";
		
		// 1. 정렬
		Arrays.sort(nums);
		
		// max -> 큰 숫자부터 앞에서 차레대로
		for(int i=n-1; i>=0; i--)
			maxTmp+=nums[i];
		// min -> 작은 숫자부터 앞에서 차례대로
		for(int i=0; i<n; i++)
			minTmp+=nums[i];
		System.out.println(maxTmp);
		System.out.println(minTmp);
		
		System.out.println(Integer.parseInt(maxTmp)+Integer.parseInt(minTmp));
	}
}















