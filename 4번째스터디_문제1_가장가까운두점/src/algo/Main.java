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
		int min = Integer.MAX_VALUE;
		int[] res = { 0, 0 };
		int dist = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());

		// int 말고 long으로 해야 함!
		// 문제 조건에, 2이상 10자리 이하의 자연수. int는 max가 21억인데 10자리면 99억까지 가능해서!!
		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				// 두 점 간 거리 (절대값)
				dist = nums[i] > nums[j] ? nums[i] - nums[j] : nums[j] - nums[i];
				if (min > dist) {
					min = dist;
					res[0] = nums[i] > nums[j] ? nums[j] : nums[i]; // 더 작은 값
					res[1] = nums[i] > nums[j] ? nums[i] : nums[j]; // 더 큰 값
				} else if ((min == dist) && (res[0] + res[1] > nums[i] + nums[j])) {

					res[0] = nums[i] > nums[j] ? nums[j] : nums[i]; // 더 작은 값
					res[1] = nums[i] > nums[j] ? nums[i] : nums[j]; // 더 큰 값
				}
			}
		}

		System.out.println(res[0] + " " + res[1]);
	}
}
