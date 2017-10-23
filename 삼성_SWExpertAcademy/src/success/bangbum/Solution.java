/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package success.bangbum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// k의 범위를 잘못생각했다. 1<=k<=N이 아니라, 도시 전체에 서비스 하는 것이 최대의 경우이다.
// 따라서 1<=k<=N+1이 된다.
public class Solution {
	static int N,M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc; i++) {
			String[] s=br.readLine().split(" ");
			N=Integer.parseInt(s[0]);
			M=Integer.parseInt(s[1]);
			map=new int[N][N];
			
			for(int j=0; j<N; j++) {
				s=br.readLine().split(" ");
				for(int k=0; k<N; k++)
					map[j][k]=Integer.parseInt(s[k]);
			}
			
			System.out.println("#"+i+" "+solve());
		}
	}
	// 손해를 보지 않으면서 홈방범 서비스를 가장 많은 수의 집에 제공할 때 집의 수를 리턴
	public static int solve() {
		int max=1;	// 집의 수는 최소 1개이며, M이 1이상이므로 무조건 1개의 집에는 제공할 수 있다.
		
		// 마름모의 변 길이를 바꿔가며 반복탐색한다.
		// k=1일 때에는 최대 집 개수가 1개이며, 집은 최소 1개이기 때문에 k=1일 때에는 검사할 필요가 없다.
		// 맵 전체에 홈 방범 서비스를 하려면 k가 N+1이어야 하기 때문에 k는 N+1까지 증가시킨다.
		for(int k=2; k<=N+1; k++) {
			// 마름모의 한 변의 길이가 주어졌을 때, map[][]의 모든 점을 기준점으로 하여 검사를 하고 max값을 구한다.
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 그리고 마름모로 가능한 모든 길이에 대해서 탐색하고 max를 구해야 하므로 비교해서 max변수에 넣는다.
					max=Math.max(max, getHouseCnt(k,i,j));
				}
			}
		}
		
		
		return max;
	}
	
	/*
	마름모의 한 변의 길이와 현재의 기준점이 주어졌을 때, 서비스를 제공할 수 있는 집의 수를 리턴
	k : 마름모의 길이
	row : 기준점의 행
	col : 기준점의 열
	기준점은 도형의 중간점이 되야한다.
	return : 하나의 집에도 제공하지 못하거나, 손해를 보는 경우에는 0을 리턴. 이외의 경우에는 서비스를 제공할 수 있는 집의 수를 리턴
	마름모 탐색의 자세한 설명은 이 프로젝트의 d3 패키지의 DiamondSearch 주석 참조
	 */
	public static int getHouseCnt(int k, int row, int col) {
		int cnt=0;
		int nexty;	// 각 행의 가장 왼쪽 칸의 col값
		int endp;	// 각 행의 열의 개수
		
		// 집의 개수를 구한다.
		
		// 마름모의 위아래영역 검사
		// 마름모 한 변의길이-1 번만큼 반복
		for(int i=1; i<=k-1; i++) {
			int up_nextx=row-k+i;		// 위쪽 영역의 row index
			int down_nextx=row+k-i;		// 아래쪽 영역의 row index
			
			endp=i*2-1;				// 각 행의 열의 개수 (검사를 반복할 회수)
			nexty=col-endp/2;		// 
			
			for(int j=0; j<endp; j++) {	//위 쪽 검사 + 아래쪽 검사
				if(checkRange(up_nextx,nexty+j) && map[up_nextx][nexty+j]==1)
					cnt++;
				if(checkRange(down_nextx,nexty+j) && map[down_nextx][nexty+j]==1)
					cnt++;
			}
		}
		
		// 마름모의 중간줄 검사
		endp=k*2-1;
		nexty=col-endp/2;
		for(int i=0; i<endp; i++)
			if(checkRange(row,nexty+i) && map[row][nexty+i]==1)
				cnt++;
		
//		// 마름모를 가로로 반 쪼갰을 때 위쪽(딱 절반인 부분 포함) : 아래쪽으로 갈수록 열의 개수가 증가
//		for(int i=row-k+1; i<=row; i++)
//			for(int j=col-(i+k-1); j<=col+i+k-1; j++)
//				if(checkRange(i,j) && map[i][j]==1)
//					cnt++;
//		
//		// 마름모를 가로로 반 쪼갰을 때 아래쪽 : 아래쪽으로 갈수록 열의 개수가 감소
//		for(int i=row+k-1; i>row; i--)
//			for(int j=col+(i-k+1); j<=col-(i-k+1); j++)
//				if(checkRange(i,j) && map[i][j]==1)
//					cnt++;
		
		// 손익을 구해서 손해인 경우 cnt=0으로 선언한다.
		if( (cnt*M) - (k*k) - ((k-1)*(k-1)) <0 )
			cnt=0;
		
		return cnt;
	}
	
	// 배열 안의 index인지 체크. 배열 안의 index가 아니면 return false
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=N)
			return false;
		return true;
	}
}
