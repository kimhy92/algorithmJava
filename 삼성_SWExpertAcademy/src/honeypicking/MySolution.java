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
		// �켱 �� ���� ���������� ���� �� ���� �� �ִ� �ִ� ������ ���Ͽ� p[i][j]�� �����Ѵ�.
		// �׸��� �� �ִ� ������ max�� �����Ѵ�.
		int max=0;
		int maxR=0, maxC=0;
		int secMax=0;	// �ִ� ������ �������� ��, �ٸ� ����� �� �� �ִ� ������ ���Ѵ�. ��, �� �� ��ġ���� üũ���־�� �Ѵ�.
		
		// ���� ex) ������ ū ������� 100,70,60,1�� �ִٰ� ���� ��,
		// 70�� 100�� ��ģ�ٰ� �����غ���.
		// �׸��� 60�� 70�� ��ġ�� �ʴ´ٸ�, ���� 101�� �ƴ϶� 70+60�� �� ���� ������? (��, ������ ���� ū �� �������� ���� ���� ���� ������?)
		// ������ 60�� 70�� ��ġ�� �ʴ´ٸ� 100���� ��ġ�� �ʱ� ������ 160�� ���̴�.
		// �ֳ��ϸ� ������ ���� m�� �����ϱ� �����̴�.
		// �׷��� �̷��� Ǯ���µ�, �ؼ��� ��쿡�� �̷��� �� Ǯ����.
		// �ؼ��� �� p[i][j]�� ���鸸 ���ϰ� getMaxPair()�� ȣ���ؼ�
		// �ٽ� ���� for������ �ݺ��Ͽ�
		// �� (i,j)�� �������� �ִ� ������ ���� ���߰�, �� �߿��� �ִ� ������ ���ߴ�. �ؼ��� �� ������ ��
		
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
		
		// �� ��° ū ���� ã�´�.
		// p[][] �迭�� �� ��ǥ�� ���������� ���� �� �ִ� ������ ������ �������Ƿ� �� ���� �̿��Ѵ�.
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				if(secMax<p[i][j]) {	// �켱 �ִ밪�� ã�� ���,
					// i�� maxR�� �ٸ��ٸ� max�� ����(���� �ٸ��� ������ ��ġ�� ����)
					if(i!=maxR)
						secMax=p[i][j];
					else if(j >= maxC+M)	// ���� ��������, ���� ��ġ�� �ʴ� ��� ����
						secMax=p[i][j];
				}
			}
		}
		
		return max+secMax;
	}
	
	/*
	 m : ������� �������� Ȥ�� �������� �������� ������ ����
	 row : ���� ��
	 col : ���� ��
	 sum : ��������� ���� ä�뷮�� ��
	 pSum : ��������� ���� ä������ �� ������ ��
	 */
	public static int getMaxPickingSum(int m, int row, int col, int sum, int pSum) {
		int res=0;
		int comparePSum=0;
		if(m==M || col>=N)	// �ϳ��� �κ������� ���� ���, ��������� ������ �� ����
			return pSum;
		
		if(sum+h[row][col]<=C) {	// ä���ϴ� ���. �� ��, ä��� ������ ������ �� �Ǵϱ� üũ �ʿ�. ��, ä�뷮�� ������ ���� ���� ���� ȣ��
			comparePSum=getMaxPickingSum(m+1,row,col+1,sum+h[row][col],pSum+h[row][col]*h[row][col]);
			res=Math.max(res, comparePSum);
		}
		comparePSum=getMaxPickingSum(m+1,row,col+1,sum,pSum);	// ���� ��ǥ�� ������ ä������ �ʴ� ���
		res=Math.max(res, comparePSum);
		
		return res;
	}
}










