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
			for(int j=0; j<N; j++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++)
					h[j][k]=Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#"+i+" "+solve());
		}
	}
	
	public static int solve() {
		int res=0;	// 결과값
		// 우선 각 점을 기준점으로 했을 때 얻을 수 있는 최대 수익을 구하여 p[i][j]에 저장한다.
		// 그리고 그 최대 수익을 maxSum에 저장한다.
		int maxSum=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				p[i][j]=getMaxPickingSum(i, j);
				maxSum=Math.max(maxSum, p[i][j]);
			}
		}
		
		return maxSum;
	}
	
	public static int getMaxPickingSum(int row, int col) {
		
		
		return -1;
	}
}










