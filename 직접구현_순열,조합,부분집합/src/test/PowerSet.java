package test;

import java.io.IOException;

// �κ����� : ������ ���� ��ü ������ �κ� ������ �˰������ ����
// ��ǥ������ �賶 ���� ���� ���, ���� �˻��� �ϱ� ���� ��� �κ������� ���������ν� �ظ� ã�� �� �ִ� ����
// ���⿡ Ž�� ����� ���� ��ȹ���� �߰��Ͽ� ��� Ƚ���� ���� �� �ִ�.
public class PowerSet {
	static int[] arr=new int[]{3,6,7,1};
	static int n=4;
	static int[] bitAry=new int[n];	// �迭�� bitüũ
	static int bit=1<<n;			// ��Ʈ����ũ (or ���̳ʸ� ī����)
	
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
		
		printSubset(0);
		
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
//	for(int num=0; num<(1<<n); num++) {
//	// i=0 to 3
//	System.out.print("{");
//	for(int i=0; i<n; i++) {
//		if((num&(1<<i))==(1<<i))
//			System.out.print(""+arr[i]+",");
//	}
//	System.out.println("}");
//}
	/*
	m : �������� ������ ������ Ƚ��
	
	 */
	public static void printSubset(int m) {
		if(m==n) {	// �κ����� �ϼ�
			StringBuilder sb=new StringBuilder("");
			sb.append("{");
			for(int i=0; i<n; i++) {
				if((bit&(1<<i))==1<<i) {
					sb.append(arr[i]+",");
				}
			}
			sb.replace(sb.toString().length()-1, sb.toString().length(), "}");
			System.out.println(sb.toString());
			return;
		}
		
		int tmp=bit;
		printSubset(m+1);
		bit=bit|(1<<m);
		printSubset(m+1);
		bit=tmp;
	}
	
}










