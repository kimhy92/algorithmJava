package test;

// 순열 : n개의 원소 중 r개를 순서 상관하며 골라낸 것 -> 1,2,3,4와 1,3,2,4는 순서가 다르기 때문에 다른 경우로 취급
public class Permutation {
	// 사전적 순서와 최소 변경을 통한 방법으로 구현 가능
	// 코드는 최소 변경을 통한 방법이다. -> 대표적으로 존슨-트로터 알고리즘이 있다. 이를 구현한
	// 원소 중 두가지 원소의 위치를 서로 바꾸며 하는 것
	
	// 1~4까지의 4개의 원소를 나열하는 모든 경우의 수를 출력
	static int[] arr={1,2,3,4};
	public static void main(String[] args) {
		int n=arr.length;
		
//		perm(n,0);		// 방식 1
		solve();		// 방식 2 (백트래킹)
	}
	
	// n: 원소의 개수, k: 현재까지 선택된 원소의 수
	public static void perm(int n, int k) {
		if(k==n) {	// 하나의 순열이 생성됨
			// 원하는 작업
			for(int i=0; i<n; i++)
				System.out.print(arr[i]);
			System.out.println();
		}
		else {
			for(int i=k; i<n; i++) {
				swap(k,i);		// 교환을 통한 선택
				perm(n,k+1);	// 재귀호출
				swap(k,i);		// 이전 상태로 복귀
			}
		}
	}
	
	public static void swap(int i, int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	// ==========================방식2===============================
	public static void solve() {
		permutation(new int[]{0,0,0,0}, 0, arr.length);
	}
	
	// k: 현재까지 선택된 원소의 수, n: 원소의 개수
	// bitmask 방식 대신 배열에 저장. 순서가 몇 번째인지 저장해야 하기 때문에 bitmask 불가능
	public static void permutation(int[] order, int k, int n) {
		if(k==n) {		// 하나의 순열 완성
			// order[]를 이용해서 순열 출력
			for(int i=0; i<order.length; i++) {
				System.out.print(order[i]+" ");
			}
			System.out.println();
			return;
		} else {
			boolean[] check=new boolean[arr.length];
			for(int i=0; i<k; i++) {
				check[order[i]]=true;
			}
			
			for(int i=0; i<n; i++) {
				if(check[i]==false) {
					order[k]=i;
					permutation(order,k+1,n);
				}
			}
			
		}
		
		
	}
}
