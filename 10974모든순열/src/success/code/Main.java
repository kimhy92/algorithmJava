/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 12.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package success.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀 방식으로 모든 순열 문제 해결
// 재귀 방식 -> 기본 부분, 유도 부분으로 구분.
// 기본 부분 -> 재귀 알고리즘을 끝내기 위한 부분
// 유도 부분 -> 새로운 집합의 원소를 생성하기 위해 재귀하는 부분
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 1. 예외 체크
		if (n < 1 || n > 8)
			return;
		// 2. 입력값 n을 통해, 1~n까지의 값을 배열에 넣는다.
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i + 1;

		// 배열에 있는 값들을 사전 순서에 맞춰서 순서를 바꿔가면서 출력한다.
		do {
			for (int i = 0; i < n; i++)
				System.out.printf("%d ", arr[i]);
			System.out.println();
		} while (nextPermutation(arr));
	}

	public static boolean nextPermutation(int[] arr) {
		// 1. A[i-1] < A[i] (0<=i<n) 을 만족하는 i 중 가장 큰 i 찾기
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) {
			i -= 1;
		}
		
		// 종료조건 -> 전달받은 배열 arr의 원소들이, 완전히 내림차순으로 정렬이 되어 있으면 모든 것을 출력한 것이기 때문에 종료
		if (i <= 0)
			return false;
		
		// 2. A[i-1] < A[j] (i<=j<n)을 만족하는 j 중 가장 큰 j 찾기
		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1])
			j--;
		
		// 3. A[i-1] 과 A[j] SWAP
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		j = arr.length - 1;
		
		// 4. A[i]~A[j-1] 까지 역순으로 정렬
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
