/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 8.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// �̷��� ¥�� �Ǳ� �ϴµ�, ��� ����� [1 2 3 4]�� ���� ������� �߰����� �ϴ� �� �� ����
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
