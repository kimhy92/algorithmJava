/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 8.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
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
		
		// �� index���� �������� �Ͽ���, �ִ����� ���Ѵ�.
		for(int i=0; i<numCnt; i++) {
			
		}
		
		// �� index�� �������� ���� ���� �ִ��� �߿����� ���� ū ���� ���Ѵ�.
		max=sum[0];
		for(int i=1; i<numCnt; i++)
			if(max<sum[i])
				max=sum[i];
		
		System.out.println(max);
	}
}