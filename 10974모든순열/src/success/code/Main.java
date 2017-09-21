/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 12.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package success.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ��� ������� ��� ���� ���� �ذ�
// ��� ��� -> �⺻ �κ�, ���� �κ����� ����.
// �⺻ �κ� -> ��� �˰����� ������ ���� �κ�
// ���� �κ� -> ���ο� ������ ���Ҹ� �����ϱ� ���� ����ϴ� �κ�
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 1. ���� üũ
		if (n < 1 || n > 8)
			return;
		// 2. �Է°� n�� ����, 1~n������ ���� �迭�� �ִ´�.
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i + 1;

		// �迭�� �ִ� ������ ���� ������ ���缭 ������ �ٲ㰡�鼭 ����Ѵ�.
		do {
			for (int i = 0; i < n; i++)
				System.out.printf("%d ", arr[i]);
			System.out.println();
		} while (nextPermutation(arr));
	}

	public static boolean nextPermutation(int[] arr) {
		// 1. A[i-1] < A[i] (0<=i<n) �� �����ϴ� i �� ���� ū i ã��
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i -= 1;
		}
		
		// �������� -> ���޹��� �迭 arr�� ���ҵ���, ������ ������������ ������ �Ǿ� ������ ��� ���� ����� ���̱� ������ ����
		if (i <= 0)
			return false;
		
		// 2. A[i-1] < A[j] (i<=j<n)�� �����ϴ� j �� ���� ū j ã��
		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1])
			j--;
		
		// 3. A[i-1] �� A[j] SWAP
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		j = arr.length - 1;
		
		// 4. A[i]~A[j-1] ���� �������� ����
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}

		return true;
	}
}
