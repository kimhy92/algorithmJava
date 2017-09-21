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
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCnt = Integer.parseInt(br.readLine());	// 첫 줄 : 숫자 개수 입력 받음
		int[] ary = new int[numCnt];	// 숫자 개수에 맞춰서 배열 선언
		int maxSum=Integer.MIN_VALUE;	// 결과 값. 최대 합. 초기값은 Int의 최소값으로 해야 한다. 입력받은 값의 최대 연속합이 음수일 수도 있다.
		
		// 두 번째 줄의 숫자들을 입력받아서 int[]에 넣는다.
		String tmpNums = br.readLine();
		StringTokenizer st = new StringTokenizer(tmpNums);
		for(int i=0; i<numCnt; i++)
			ary[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<numCnt; i++) {
			int sum=0;
			for(int j=i; j<numCnt; j++) {
				sum+=ary[j];
				maxSum=(maxSum<sum)?sum:maxSum;
			}
		}
		System.out.println(maxSum);
		
//		아래의 코드는 내가 짰던 코드인데,
//		예시는 돌아가는데, "틀렸습니다" 나옴
//		각 반복문 마다 첫번째 값도 비교를 해줘야 하는데 안 해줘서 그런 것 같다.
//		그리고 배열의 마지막 값도 비교해줘야 하는데 , i<numCnt-1 이라서 배열의 마지막 값은 비교하지 않는 듯하다.		
//		for(int i=0; i<numCnt-1; i++) {
//			int tmpMax = ary[i];
//			for(int j=i+1; j<numCnt; j++) {
//				ary[i]+=ary[j];
//				if(tmpMax<ary[i])
//					tmpMax=ary[i];
//			}
//			if(maxSum<tmpMax)
//				maxSum=tmpMax;
//		}
//		System.out.println(maxSum);
	}
}
