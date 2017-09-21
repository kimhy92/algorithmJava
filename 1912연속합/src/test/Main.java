/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 8.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br=null;
		int numCnt=0;
		String numLine=null;
		int max=0;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		numCnt=Integer.parseInt(br.readLine());
		
		if(numCnt<1 || numCnt>100000)
			return;
		
		int[] arr = new int[numCnt];
		int[] sum = new int[numCnt];
		
		
		numLine=br.readLine();
		StringTokenizer st = new StringTokenizer(numLine," ");
		
		for(int i=0; i<numCnt; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		// 각 index별로 기점으로 하여서, 최대합을 구한다.
		for(int i=0; i<numCnt; i++) {
			
		}
		
		// 각 index를 기점으로 했을 때의 최대합 중에서도 가장 큰 값을 구한다.
		max=sum[0];
		for(int i=1; i<numCnt; i++)
			if(max<sum[i])
				max=sum[i];
		
		System.out.println(max);
	}
}