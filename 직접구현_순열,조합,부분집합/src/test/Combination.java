package test;

// 조합 : n개의 원소 중 r개를 순서 없이 골라낸 것 -> 1,2,3,4 와 1,3,2,4는 같은 것이기 때문에 1가지 경우로 취급
public class Combination {
	// an[]: n개의 원소를 가지고 있는 배열
	// tr[]: r개의 크기의 배열, 조합이 임시 저장될 배열
	
	// 5C3을 구하는 것
	static int[] an={1,2,3,4,5};
	static int[] tr=new int[3];
	
	public static void main(String[] args) {
		int n=an.length;
		
		comb(n,3);
	}
	
	// n: 전체 원소의 개수
	// r: 선택해야 할 원소의 개수
	public static void comb(int n, int r) {
		if(r==0) {	// 하나의 조합이 됨
			for(int i=0; i<3; i++)
				System.out.print(tr[i]);
			System.out.println();
			return;
		}
		else if(n<r)
			return;
		else {
			tr[r-1]=n-1;
			comb(n-1,r-1);	// 선택하는 경우
			comb(n-1,r);	// 선택하지 않은 경우
		}
	}
}
