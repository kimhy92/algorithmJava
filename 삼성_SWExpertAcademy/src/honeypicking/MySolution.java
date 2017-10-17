/**
 * @ClassName : MySolution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 17.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package honeypicking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MySolution {
	static int N,M,C;
	static int[][] h;
	static int[][] p;	// �޸������̼ǿ� �޸�. p[i][j]= h[i][j]�� ������(���� ���� ���)���� �Ͽ��� ��, ���� �� �ִ� �ִ� ����
	
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
		int res=0;	// �����
		// �켱 �� ���� ���������� ���� �� ���� �� �ִ� �ִ� ������ ���Ͽ� p[i][j]�� �����Ѵ�.
		// �׸��� �� �ִ� ������ maxSum�� �����Ѵ�.
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










