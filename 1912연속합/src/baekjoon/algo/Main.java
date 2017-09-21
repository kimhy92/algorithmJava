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
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCnt = Integer.parseInt(br.readLine());	// ù �� : ���� ���� �Է� ����
		int[] ary = new int[numCnt];	// ���� ������ ���缭 �迭 ����
		int maxSum=Integer.MIN_VALUE;	// ��� ��. �ִ� ��. �ʱⰪ�� Int�� �ּҰ����� �ؾ� �Ѵ�. �Է¹��� ���� �ִ� �������� ������ ���� �ִ�.
		
		// �� ��° ���� ���ڵ��� �Է¹޾Ƽ� int[]�� �ִ´�.
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
		
//		�Ʒ��� �ڵ�� ���� ®�� �ڵ��ε�,
//		���ô� ���ư��µ�, "Ʋ�Ƚ��ϴ�" ����
//		�� �ݺ��� ���� ù��° ���� �񱳸� ����� �ϴµ� �� ���༭ �׷� �� ����.
//		�׸��� �迭�� ������ ���� ������� �ϴµ� , i<numCnt-1 �̶� �迭�� ������ ���� ������ �ʴ� ���ϴ�.		
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
