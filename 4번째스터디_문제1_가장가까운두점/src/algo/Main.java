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
import java.util.StringTokenizer;

// ���� ����� �� ��
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int min = Integer.MAX_VALUE;
		int[] res = { 0, 0 };
		int dist = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());

		// int ���� long���� �ؾ� ��!
		// ���� ���ǿ�, 2�̻� 10�ڸ� ������ �ڿ���. int�� max�� 21���ε� 10�ڸ��� 99����� �����ؼ�!!
		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				// �� �� �� �Ÿ� (���밪)
				dist = nums[i] > nums[j] ? nums[i] - nums[j] : nums[j] - nums[i];
				if (min > dist) {
					min = dist;
					res[0] = nums[i] > nums[j] ? nums[j] : nums[i]; // �� ���� ��
					res[1] = nums[i] > nums[j] ? nums[i] : nums[j]; // �� ū ��
				} else if ((min == dist) && (res[0] + res[1] > nums[i] + nums[j])) {

					res[0] = nums[i] > nums[j] ? nums[j] : nums[i]; // �� ���� ��
					res[1] = nums[i] > nums[j] ? nums[i] : nums[j]; // �� ū ��
				}
			}
		}

		System.out.println(res[0] + " " + res[1]);
	}
}
