/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 12.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package test.code;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i = 0; i < n; ++i) {
			ary[i] = i+1;
		}
		for (int k = 0; k < ary.length; ++k) {
			System.out.printf("%d ", ary[k]);
		}
		System.out.println();
		while(nextPermutation(ary));
	}

	static boolean nextPermutation(int[] ary) {
		// 1. A[i-1] < A[i] (0<=i<n) 을 만족하는 i 중 가장 큰 i 찾기
		int i = ary.length - 1; // 가장 우측 방 지정
		while (i > 0 && ary[i - 1] >= ary[i])
			--i;
		if (i <= 0)
			return false;

		// 2. A[i-1] < A[j] (i<=j<n)을 만족하는 j 중 가장 큰 j 찾기
		int j = ary.length - 1;
		while (ary[i - 1] >= ary[j])
			--j;

		// 3. A[i-1] 과 A[j] SWAP
		int tmp = ary[i - 1];
		ary[i - 1] = ary[j];
		ary[j] = tmp;

		// 4. A[i]~A[n-1] 까지 역순으로 정렬
		int n=ary.length-1;
		while (i < n) {
			int temp = ary[i];
			ary[i] = ary[n];
			ary[n] = temp;
			++i;
			--n;
		}
		for (int k = 0; k < ary.length; ++k) {
			System.out.printf("%d ", ary[k]);
		}
		System.out.println();
		return true;
	}
	static boolean prevPermutation(int[] ary) {
		// 1. A[i-1] > A[i] (0<=i<n) 을 만족하는 i 중 가장 큰 i 찾기
		int i = ary.length - 1; // 가장 우측 방 지정
		while (i > 0 && ary[i - 1] <= ary[i])
			--i;
		if (i <= 0)
			return false;

		// 2. A[i-1] > A[j] (i<=j<n)을 만족하는 j 중 가장 큰 j 찾기
		int j = ary.length - 1;
		while (ary[i - 1] <= ary[j])
			--j;

		// 3. A[i-1] 과 A[j] SWAP
		int tmp = ary[i - 1];
		ary[i - 1] = ary[j];
		ary[j] = tmp;

		// 4. A[i]~A[n-1] 까지 역순으로 정렬
		int n=ary.length-1;
		while (i < n) {
			int temp = ary[i];
			ary[i] = ary[n];
			ary[n] = temp;
			++i;
			--n;
		}
		return true;
	}
}