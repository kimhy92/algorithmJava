/**
 * @ClassName : DiamondSearch
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 19.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2805. ���۹� ��Ȯ ����(d3)
// Ȩ ��� ������ ������ Ž���� �����Ͽ���
public class DiamondSearch {
	static int N;
	static int[][] farm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc; i++) {
			N=Integer.parseInt(br.readLine());
			farm=new int[N][N];
			
			for(int j=0; j<N; j++) {
				String[] s=br.readLine().split("");
				for(int k=0; k<N; k++)
					farm[j][k]=Integer.parseInt(s[k]);
			}
			
			System.out.println("#"+i+" "+solve());
		}
	}
	
	/*
	7*7 �� �迭���� �Ʒ��� ���� ������� ������ Ž���ϴ� ���, Ž�� ������ �̿� ����. (���ڰ� Ž�� ����)
					1
				3	4	5
			9	10	11	12	13
		19	20	21	22	23	24	25
			14	15	16	17	18
				6	7	8
					2
	Ž�� ���� :
		������ ���� ������ �Ʒ��� ������ �� ���� for������ ���� �˻�.
		���� ������ �Ʒ��� ���� Ž���� ������ �������� ��� �� �˻�
	�ʿ����� :
	1. �������� �߰����� ���� row index, col index.
	2. ������ �� ���� ����
	�� 2���� ������ ����
	������ ���� �߰��� ���ߴ� ��
	1. upCheckRow		: ������ ���� ������ �˻��� row
	2. downCheckRow		: ������ �Ʒ��� ������ �˻��� row
	3. loopCnt			: �� ���� �˻��ؾ� �� ���� ���� (�̴� ���� ����, �Ʒ��� ������ ����. ���� Ž�� ���� ����)
	4. startCol			: �� ���� ���� ���� col�� index (���� ����, �Ʒ��� ������ ����. ���� Ž�� ���� ����)
	5. checkCol			: ���� �˻��ϴ� col�� index. �������col��index+j������ ���Ѵ�. 0<=j<loopCnt.
						  loopCnt�� �˻��ؾ� �� ���� ������ �ǹ��ϱ� ������ �� ȸ����ŭ �ݺ��ϸ鼭 j���� ���Ѵ�.
	 */
	public static int solve() {
		int sum=0;
		int k=N/2+1;			// ������ �� ���� ����
		int row=N/2, col=N/2;	// �������� �߾ӿ� �ִ� ĭ�� ��,��
		
		// �������� ��,�Ʒ� ���� �˻�
		for(int i=1; i<=k-1; i++) {	// ������ �� ���� ����-1 ��ŭ �ݺ�. �Ź� ��/�Ʒ����� �˻�
			int upCheckRow=row-(k-i);
			int downCheckRow=row+(k-i);
			
			int loopCnt=i*2-1;			// �� ���� �˻��ؾ� �� ���� ����
			int startCol=col-loopCnt/2;	// ù ��° ���� ���� col index�� (�߰�����col-0), 2��° ���� ���� col index�� (�߰�����col-1) , ....
			
			// �� ���� �˻��ؾ� �� ���� ������ i*2-1 �̴�. (1��:1��, 2��:3��, 3��:5��, ...)
			for(int j=0; j<loopCnt; j++) {
				int checkCol=startCol+j;
				if(checkRange(upCheckRow,checkCol))
					sum+=farm[upCheckRow][checkCol];
				if(checkRange(downCheckRow,checkCol))
					sum+=farm[downCheckRow][checkCol];
			}
		}
		
		// �������� ��� �� �˻�. �� �ٸ� �˻��ϴϱ� ��� ���� ���� �˻��ϸ� �� (��øfor�� ������� �ʾƵ� ��)
		for(int i=col-(k-1); i<=col+(k-1); i++)
			sum+=farm[row][i];	// ���� �߰� ������ �����̰� ���� ���� �ٲ�
		
		return sum;
	}
	
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=N)
			return false;
		return true;
	}
}













