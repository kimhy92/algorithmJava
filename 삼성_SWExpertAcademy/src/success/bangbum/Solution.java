/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package success.bangbum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// k�� ������ �߸������ߴ�. 1<=k<=N�� �ƴ϶�, ���� ��ü�� ���� �ϴ� ���� �ִ��� ����̴�.
// ���� 1<=k<=N+1�� �ȴ�.
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
	// ���ظ� ���� �����鼭 Ȩ��� ���񽺸� ���� ���� ���� ���� ������ �� ���� ���� ����
	public static int solve() {
		int max=1;	// ���� ���� �ּ� 1���̸�, M�� 1�̻��̹Ƿ� ������ 1���� ������ ������ �� �ִ�.
		
		// �������� �� ���̸� �ٲ㰡�� �ݺ�Ž���Ѵ�.
		// k=1�� ������ �ִ� �� ������ 1���̸�, ���� �ּ� 1���̱� ������ k=1�� ������ �˻��� �ʿ䰡 ����.
		// �� ��ü�� Ȩ ��� ���񽺸� �Ϸ��� k�� N+1�̾�� �ϱ� ������ k�� N+1���� ������Ų��.
		for(int k=2; k<=N+1; k++) {
			// �������� �� ���� ���̰� �־����� ��, map[][]�� ��� ���� ���������� �Ͽ� �˻縦 �ϰ� max���� ���Ѵ�.
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// �׸��� ������� ������ ��� ���̿� ���ؼ� Ž���ϰ� max�� ���ؾ� �ϹǷ� ���ؼ� max������ �ִ´�.
					max=Math.max(max, getHouseCnt(k,i,j));
				}
			}
		}
		
		
		return max;
	}
	
	/*
	�������� �� ���� ���̿� ������ �������� �־����� ��, ���񽺸� ������ �� �ִ� ���� ���� ����
	k : �������� ����
	row : �������� ��
	col : �������� ��
	�������� ������ �߰����� �Ǿ��Ѵ�.
	return : �ϳ��� ������ �������� ���ϰų�, ���ظ� ���� ��쿡�� 0�� ����. �̿��� ��쿡�� ���񽺸� ������ �� �ִ� ���� ���� ����
	������ Ž���� �ڼ��� ������ �� ������Ʈ�� d3 ��Ű���� DiamondSearch �ּ� ����
	 */
	public static int getHouseCnt(int k, int row, int col) {
		int cnt=0;
		int nexty;	// �� ���� ���� ���� ĭ�� col��
		int endp;	// �� ���� ���� ����
		
		// ���� ������ ���Ѵ�.
		
		// �������� ���Ʒ����� �˻�
		// ������ �� ���Ǳ���-1 ����ŭ �ݺ�
		for(int i=1; i<=k-1; i++) {
			int up_nextx=row-k+i;		// ���� ������ row index
			int down_nextx=row+k-i;		// �Ʒ��� ������ row index
			
			endp=i*2-1;				// �� ���� ���� ���� (�˻縦 �ݺ��� ȸ��)
			nexty=col-endp/2;		// 
			
			for(int j=0; j<endp; j++) {	//�� �� �˻� + �Ʒ��� �˻�
				if(checkRange(up_nextx,nexty+j) && map[up_nextx][nexty+j]==1)
					cnt++;
				if(checkRange(down_nextx,nexty+j) && map[down_nextx][nexty+j]==1)
					cnt++;
			}
		}
		
		// �������� �߰��� �˻�
		endp=k*2-1;
		nexty=col-endp/2;
		for(int i=0; i<endp; i++)
			if(checkRange(row,nexty+i) && map[row][nexty+i]==1)
				cnt++;
		
//		// ������ ���η� �� �ɰ��� �� ����(�� ������ �κ� ����) : �Ʒ������� ������ ���� ������ ����
//		for(int i=row-k+1; i<=row; i++)
//			for(int j=col-(i+k-1); j<=col+i+k-1; j++)
//				if(checkRange(i,j) && map[i][j]==1)
//					cnt++;
//		
//		// ������ ���η� �� �ɰ��� �� �Ʒ��� : �Ʒ������� ������ ���� ������ ����
//		for(int i=row+k-1; i>row; i--)
//			for(int j=col+(i-k+1); j<=col-(i-k+1); j++)
//				if(checkRange(i,j) && map[i][j]==1)
//					cnt++;
		
		// ������ ���ؼ� ������ ��� cnt=0���� �����Ѵ�.
		if( (cnt*M) - (k*k) - ((k-1)*(k-1)) <0 )
			cnt=0;
		
		return cnt;
	}
	
	// �迭 ���� index���� üũ. �迭 ���� index�� �ƴϸ� return false
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=N)
			return false;
		return true;
	}
}
