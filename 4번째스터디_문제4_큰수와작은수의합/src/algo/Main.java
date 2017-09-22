/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 22.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ���� ����� �� ��
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
		
		// 1. ����
		Arrays.sort(nums);
		
		// max -> ū ���ں��� �տ��� �������
		for(int i=n-1; i>=0; i--)
			maxTmp+=nums[i];
		// min -> ���� ���ں��� �տ��� ���ʴ��
		for(int i=0; i<n; i++)
			minTmp+=nums[i];
		System.out.println(maxTmp);
		System.out.println(minTmp);
		
		System.out.println(Integer.parseInt(maxTmp)+Integer.parseInt(minTmp));
	}
}















