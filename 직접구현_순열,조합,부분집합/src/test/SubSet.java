package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 부분집합 : 최적의 답이 전체 값들의 부분 집합인 알고리즘들이 존재
// 대표적으로 배낭 문제 같은 경우, 완전 검색을 하기 위해 모든 부분집합을 생성함으로써 해를 찾을 수 있는 문제
// 여기에 탐욕 기법과 동적 계획법을 추가하여 계산 횟수를 줄일 수 있다.
public class SubSet {
	static int[] arr=new int[]{3,6,7,1};
	static int n=4;
	static int[] bit=new int[n];
	
	public static void main(String[] args) throws IOException {
		// num=0 to 15(2^n-1)
//		for(int num=0; num<(1<<n); num++) {
//			// i=0 to 3
//			System.out.print("{");
//			for(int i=0; i<n; i++) {
//				if((num&(1<<i))==(1<<i))	
//					System.out.print(""+arr[i]+",");
//			}
//			System.out.println("}");
//		}
		
		printSubset();
		
		// 아래와 같은 방식으로 n의 개수에 따라 n중for문이 되야 함
		// 따라서 n이 어떤 값인지 알 수 없으면 아래와 같은 방식으로 구현할 수 없음
//		for(int i=0; i<2; i++) {
//			bit[0]=i;
//			for(int j=0; j<2; j++) {
//				bit[1]=j;
//				for(int k=0; k<2; k++) {
//					bit[2]=k;
//					for(int l=0; l<2; l++) {
//						bit[3]=l;
//						System.out.println(""+bit[0]+bit[1]+bit[2]+bit[3]);
////						System.out.print("{");
////						for(int x=0; x<n; x++) {
////							if(bit[x]==1)
////								System.out.print(arr[x]+",");
////						}
////						System.out.println("}");
//					}
//				}
//			}
//		}
	}
	
	public static void printSubset() {
		
	}
	
}