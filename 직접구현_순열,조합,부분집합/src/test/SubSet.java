package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// �κ����� : ������ ���� ��ü ������ �κ� ������ �˰������ ����
// ��ǥ������ �賶 ���� ���� ���, ���� �˻��� �ϱ� ���� ��� �κ������� ���������ν� �ظ� ã�� �� �ִ� ����
// ���⿡ Ž�� ����� ���� ��ȹ���� �߰��Ͽ� ��� Ƚ���� ���� �� �ִ�.
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
		
		// �Ʒ��� ���� ������� n�� ������ ���� n��for���� �Ǿ� ��
		// ���� n�� � ������ �� �� ������ �Ʒ��� ���� ������� ������ �� ����
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