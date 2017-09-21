/**
 * @ClassName : ProblemOne
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package problem;

public class Problem1 {
	public static void main(String[] args) {
		int n = 5;
		int[] arr1 = { 9, 20, 28, 18, 11 };
		int[] arr2 = { 30, 1, 21, 17, 28 };

		String[] res = solution(n, arr1, arr2);
		for (String tmp : res)
			System.out.println(tmp);
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		
		int compareNum = 1;
		int resBit = 0;
		String lineStr = "";
		
		for (int i=0; i<n; i++) {
			lineStr = "";
			compareNum = 1;
			
			arr1[i] = arr1[i] | arr2[i];
			
			for(int j=0; j<n; j++) {
				resBit = compareNum & arr1[i];
				if (resBit == compareNum)
					lineStr += "#";
				else
					lineStr += " ";
				compareNum *= 2;
			}
			StringBuffer sb = new StringBuffer(lineStr);
			answer[i] = sb.reverse().toString();
		}

		return answer;
	}
}
