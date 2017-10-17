/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package honeypicking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int MAXN=10;
	static int MAXM=7;
	static int[][] h=new int[MAXN][MAXN];	// �����Ǿ� ���� �迭(�Է°� ����)
	static int[][] r=new int[MAXN][MAXN];	// �ִ���� ���� �迭
	static int N,M,C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int x=1; x<=tc; x++) {
			String[] st=br.readLine().split(" ");
			N=Integer.parseInt(st[0]);
			M=Integer.parseInt(st[1]);
			C=Integer.parseInt(st[2]);
			h=new int[N][N];
			for(int i=0;i<N;i++) {
				String[] tmp=br.readLine().split(" ");
				for(int j=0; j<N; j++)
					h[i][j]=Integer.parseInt(tmp[j]);
			}
			
			System.out.println("#"+x+" "+solve());
		}
	}
	
	static int solve() {
		int max=0;
		int sum=0;

		// r[i][j]�� ���� ���� ��ǥ�� ���� �� �ִ� ������ ���Ѵ�.
		for(int i=0; i<N; i++)
			for(int j=0; j<=N-M; j++)
				r[i][j]=getPSum(0,i,j,0,0);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				// (i,j)�� ���ÿ� ���� ������ ���� �߿��� �ִ� ������ �� �� �ִ� ������ ������ ���Ѵ�.
				sum=r[i][j]+getMaxPair(i,j);
				// �׸��� �� �߿��� �ִ밪�� ���Ѵ�.
				max=Math.max(max, sum);
			}
		}
		
		return max;
	}
	
	// �ʱⰪ : 0,row,col,0,0
	// m : ���������κ��� ���η� ������ ��ġ��. 0 <= m <= M
	// row : ���� �˻��ϴ� ������ ��, col: ���� �˻��ϴ� ������ ��
	// sum : ��
	// psum : ä���� ������ ��
	static int getPSum(int m, int row, int col, int sum, int psum) {
		// ���� ��ǥ�κ��� ä���ϴ� ������ ���� ���������� psum�� �����Ѵ�. (ä���� ���� ��)
		if(m>=M) return psum;
		
		int nextSum;
		int max=0;
		
		for(int i=m; i<M; i++) {
			if(sum+h[row][col+i]<=C) {	// ������� ä���� �ܹ��� �� + ����ä���� ���� ���� ���ؼ� C�����̸�, �� ä���� �� ������
				nextSum=getPSum(i+1,row,col,sum+h[row][col+i],psum+h[row][col+i]*h[row][col+i]);
				if(max<nextSum) max=nextSum;
			}
			nextSum=getPSum(i+1,row,col,sum,psum);
			if(max<nextSum) max=nextSum;
		}
		return max;
	}
	
	static int getMaxPair(int row, int col) {
		int maxR=0;
		
		// ���� �࿡�� ��ġ�� �ʴ� �ִ밪
		for(int j=col+M; j<=N-M; j++)
			if(maxR<r[row][j]) maxR=r[row][j];
		
		// �ٸ� �࿡�� �ִ밪. �ٸ� ���� ��ĥ ���� �����ϱ� ��ġ�� ���� ������� �ʾƵ� �ȴ�.
		for(int i=row+1; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				if(maxR<r[i][j]) maxR=r[i][j];
			}
		}
		return maxR;
	}
}

//int maxSum=0;
//List<Location> maxSumRc=new ArrayList<Location>();
//int[][] honeySum=new int[N][N];
//// �ִ�� ä���� �� �ִ� ������ǥ�� ���Ѵ�.
//for(int i=0; i<N; i++) {
//	for(int j=0; j<N; j++) {
//		int sum=0;
//		int[] tmp=new int[M];
//		for(int k=j; k<M&&k<N; k++) {
//			// ���� C�� ���� �����鼭 �ִ��� ��츦 ã�� �� ���� honeySum�� ����
//			sum=h[i][k];
//			for(int l=k+1; l<M&&l<N; l++) {		// �迭 ���� ��ǥ������ ���� Ȯ��
//				if(sum+h[i][l]<=C)	// ���� sum���� ���� c���� �۰ų� ������, sum�� ����
//					// �迭�� ��(���� ��)�� ������ 1~9�̹Ƿ� ���ϸ� ������ �� Ŀ����. ���� ���� sum���� �� ū���� ���� �ʿ� ����
//					sum+=h[i][l];
//				
//			}
//		}
//		
//		if(maxSum<honeySum[i][j]) {
//			maxSum=honeySum[i][j];
//			
//		}
//	}
//}
