package test;

// ���� : n���� ���� �� r���� ���� ���� ��� �� -> 1,2,3,4 �� 1,3,2,4�� ���� ���̱� ������ 1���� ���� ���
public class Combination {
	// an[]: n���� ���Ҹ� ������ �ִ� �迭
	// tr[]: r���� ũ���� �迭, ������ �ӽ� ����� �迭
	
	// 5C3�� ���ϴ� ��
	static int[] an={1,2,3,4,5};
	static int[] tr=new int[3];
	
	public static void main(String[] args) {
		int n=an.length;
		
		comb(n,3);
	}
	
	// n: ��ü ������ ����
	// r: �����ؾ� �� ������ ����
	public static void comb(int n, int r) {
		if(r==0) {	// �ϳ��� ������ ��
			for(int i=0; i<3; i++)
				System.out.print(tr[i]);
			System.out.println();
			return;
		}
		else if(n<r)
			return;
		else {
			tr[r-1]=n-1;
			comb(n-1,r-1);	// �����ϴ� ���
			comb(n-1,r);	// �������� ���� ���
		}
	}
}
