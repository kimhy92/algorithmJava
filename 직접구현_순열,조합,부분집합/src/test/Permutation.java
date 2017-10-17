package test;

// ���� : n���� ���� �� r���� ���� ����ϸ� ��� �� -> 1,2,3,4�� 1,3,2,4�� ������ �ٸ��� ������ �ٸ� ���� ���
public class Permutation {
	// ������ ������ �ּ� ������ ���� ������� ���� ����
	// �ڵ�� �ּ� ������ ���� ����̴�. -> ��ǥ������ ����-Ʈ���� �˰����� �ִ�. �̸� ������
	// ���� �� �ΰ��� ������ ��ġ�� ���� �ٲٸ� �ϴ� ��
	
	// 1~4������ 4���� ���Ҹ� �����ϴ� ��� ����� ���� ���
	static int[] arr={1,2,3,4};
	
	public static void main(String[] args) {
		int n=arr.length;
		
		perm(n,0);
	}
	
	// n: ������ ����, k: ������� ���õ� ������ ��
	public static void perm(int n, int k) {
		if(k==n) {	// �ϳ��� ������ ������
			// ���ϴ� �۾�
			for(int i=0; i<n; i++)
				System.out.print(arr[i]);
			System.out.println();
		}
		else {
			for(int i=k; i<n; i++) {
				swap(k,i);		// ��ȯ�� ���� ����
				perm(n,k+1);	// ���ȣ��
				swap(k,i);		// ���� ���·� ����
			}
		}
	}
	
	public static void swap(int i, int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
}
