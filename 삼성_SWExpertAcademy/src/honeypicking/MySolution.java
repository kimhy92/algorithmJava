/**
 * @ClassName : MySolution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 17.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package honeypicking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MySolution {
	static int N,M,C;
	static int[][] h;
	static int[][] p;	// 메모이제이션용 메모리. p[i][j]= h[i][j]를 기준점(가장 좌측 블록)으로 하였을 때, 얻을 수 있는 최대 수익
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc; i++) {
			String[] s=br.readLine().split(" ");
			N=Integer.parseInt(s[0]);
			M=Integer.parseInt(s[1]);
			C=Integer.parseInt(s[2]);
			h=new int[N][N];
			p=new int[N][N];
			for(int j=0; j<N; j++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++)
					h[j][k]=Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#"+i+" "+solve());
		}
	}
	
	public static int solve() {
		// 우선 각 점을 기준점으로 했을 때 얻을 수 있는 최대 수익을 구하여 p[i][j]에 저장한다.
		// 그리고 그 최대 수익을 max에 저장한다.
		int max=0;
		int maxR=0, maxC=0;
		int secMax=0;	// 최대 수익이 구해졌을 때, 다른 경우의 수 중 최대 수익을 구한다. 단, 이 때 겹치는지 체크해주어야 한다.
		
		// 예외 ex) 수익이 큰 순서대로 100,70,60,1이 있다고 했을 때,
		// 70이 100과 겹친다고 가정해보자.
		// 그리고 60이 70이 겹치지 않는다면, 답은 101이 아니라 70+60일 수 있지 않은가? (즉, 수익이 가장 큰 걸 포함하지 않을 수도 있지 않은가?)
		// 하지만 60이 70과 겹치지 않는다면 100과도 겹치지 않기 때문에 160일 것이다.
		// 왜냐하면 벌통의 길이 m은 일정하기 때문이다.
		// 그래서 이렇게 풀었는데, 해설의 경우에는 이렇게 안 풀었다.
		// 해설은 각 p[i][j]의 값들만 구하고 getMaxPair()를 호출해서
		// 다시 이중 for문으로 반복하여
		// 각 (i,j)를 기준으로 최대 수익의 합을 구했고, 그 중에서 최대 수익을 구했다. 해설이 더 안전한 듯
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				p[i][j]=getMaxPickingSum(0,i,j,0,0);
				if(max<p[i][j]) {
					max=p[i][j];
					maxR=i;
					maxC=j;
				}
			}
		}
		
		// 두 번째 큰 값을 찾는다.
		// p[][] 배열에 각 좌표를 기준점으로 했을 때 최대 수익을 저장해 놓았으므로 그 것을 이용한다.
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				if(secMax<p[i][j]) {	// 우선 최대값을 찾은 경우,
					// i와 maxR이 다르다면 max값 갱신(행이 다르면 무조건 겹치지 않음)
					if(i!=maxR)
						secMax=p[i][j];
					else if(j >= maxC+M)	// 같은 행이지만, 열이 겹치지 않는 경우 갱신
						secMax=p[i][j];
				}
			}
		}
		
		return max+secMax;
	}
	
	/*
	 m : 현재까지 포함할지 혹은 포함하지 않을지가 결정된 개수
	 row : 현재 행
	 col : 현재 열
	 sum : 현재까지의 벌꿀 채취량의 합
	 pSum : 현재까지의 벌꿀 채취했을 때 수익의 합
	 */
	public static int getMaxPickingSum(int m, int row, int col, int sum, int pSum) {
		int res=0;
		int comparePSum=0;
		if(m==M || col>=N)	// 하나의 부분집합이 끝난 경우, 현재까지의 수익의 합 리턴
			return pSum;
		
		if(sum+h[row][col]<=C) {	// 채취하는 경우. 이 때, 채취양 제한을 넘으면 안 되니까 체크 필요. 즉, 채취량이 제한을 넘지 않을 때만 호출
			comparePSum=getMaxPickingSum(m+1,row,col+1,sum+h[row][col],pSum+h[row][col]*h[row][col]);
			res=Math.max(res, comparePSum);
		}
		comparePSum=getMaxPickingSum(m+1,row,col+1,sum,pSum);	// 현재 좌표의 벌통을 채취하지 않는 경우
		res=Math.max(res, comparePSum);
		
		return res;
	}
}










