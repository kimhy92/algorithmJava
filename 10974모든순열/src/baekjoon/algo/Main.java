/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 8.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이렇게 짜면 되긴 하는데, 출력 양식인 [1 2 3 4]와 같이 띄워쓰기 중간에만 하는 것 못 맞춤
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		int num = 0;
		String str = "";

		br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		if (num < 1 || num > 8)
			return;
		for (int i = 1; i <= num; i++)
			str += i;
		permutation(str);
	}

	public static void permutation(String str) {
		permutation("", str);
	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			System.out.println(prefix);
			return;
		} else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i) + " ", str.substring(0, i) + str.substring(i + 1, n));
		}
	}
}
