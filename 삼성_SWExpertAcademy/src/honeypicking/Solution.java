/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 16.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package honeypicking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int MAXN=10;
	static int MAXM=7;
	static int[][] h=new int[MAXN][MAXN];	// 벌통의양 저장 배열(입력값 저장)
	static int[][] r=new int[MAXN][MAXN];	// 최대수익 저장 배열
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

		// r[i][j]를 가장 왼쪽 좌표로 했을 때 최대 수익을 구한다.
		for(int i=0; i<N; i++)
			for(int j=0; j<=N-M; j++)
				r[i][j]=getPSum(0,i,j,0,0);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				// (i,j)와 동시에 선택 가능한 벌통 중에서 최대 수익을 낼 수 있는 벌통의 수익을 구한다.
				sum=r[i][j]+getMaxPair(i,j);
				// 그리고 그 중에서 최대값을 구한다.
				max=Math.max(max, sum);
			}
		}
		
		return max;
	}
	
	// 초기값 : 0,row,col,0,0
	// m : 기준점으로부터 가로로 떨어진 위치값. 0 <= m <= M
	// row : 현재 검사하는 벌통의 행, col: 현재 검사하는 벌통의 열
	// sum : 합
	// psum : 채취한 벌꿀의 합
	static int getPSum(int m, int row, int col, int sum, int psum) {
		// 기준 좌표로부터 채취하는 벌통의 끝에 도달했으면 psum을 리턴한다. (채취한 꿀의 양)
		if(m>=M) return psum;
		
		int nextSum;
		int max=0;
		
		for(int i=m; i<M; i++) {
			if(sum+h[row][col+i]<=C) {	// 현재까지 채취한 꿀벌의 양 + 현재채취할 꿀의 양을 더해서 C이하이면, 즉 채취할 수 있으면
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
		
		// 같은 행에서 겹치지 않는 최대값
		for(int j=col+M; j<=N-M; j++)
			if(maxR<r[row][j]) maxR=r[row][j];
		
		// 다른 행에서 최대값. 다른 행은 겹칠 일이 없으니까 겹치는 경우는 고려하지 않아도 된다.
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
//// 최대로 채취할 수 있는 시작좌표를 구한다.
//for(int i=0; i<N; i++) {
//	for(int j=0; j<N; j++) {
//		int sum=0;
//		int[] tmp=new int[M];
//		for(int k=j; k<M&&k<N; k++) {
//			// 합이 C가 넘지 않으면서 최대인 경우를 찾고 그 합을 honeySum에 저장
//			sum=h[i][k];
//			for(int l=k+1; l<M&&l<N; l++) {		// 배열 안의 좌표인지도 같이 확인
//				if(sum+h[i][l]<=C)	// 기존 sum과의 합이 c보다 작거나 같으면, sum을 갱신
//					// 배열의 값(꿀의 양)은 무조건 1~9이므로 더하면 무조건 더 커진다. 따라서 기존 sum보다 더 큰지는 비교할 필요 없음
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
