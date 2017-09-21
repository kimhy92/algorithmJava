/**
 * @ClassName : Problem6
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 16.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package problem;

public class Problem6 {

	
	// ä���ؼ� �׽�Ʈ���̽� 11�� �߿� 3���� ���� �߰� �������� ���
	public static void main(String[] args) {
		String str1[] = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String str2[] = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		
		System.out.println(solution(4,5,str1));
		System.out.println("==========================================");
//		System.out.println(solution(6,6,str2));
	}

	// ���̾�(R), ����(M), ����ġ(A), ���ε�(F), �׿�(N), Ʃ��(T), ������(J), ��(C)
	// m : ���� ����, n : ���� ��, board : ���� ��ġ ����
	// board�� ���� n�� ���ڿ� m���� �迭
	// board�� �빮�� A~Z�� ���δ�.
	public static int solution(int m, int n, String[] board) {
		int answer=0;
		int delCnt=0;	// �� �Ǹ��� ������ ����� ����
		boolean[][] isDel = new boolean[m][n];
		
		
		while(true) {
//		for(int x=0; x<2; x++) {
			delCnt=0;	// �� �Ǹ��� ������ ����� ������ 0���� �ʱ�ȭ
			// �̹� �����Ǿ����� üũ�ϴ� �迭�� ��� ���Ҹ� false�� �����.
			for(int i=0; i<m; i++)
				for(int j=0; j<n; j++)
					isDel[i][j]=false;
			
			// 1. ���� board�� ��������, �� ���� ���������� ���
			char refPoint=0;
			for(int i=0; i<m-1; i++) {
				for(int j=0; j<n-1; j++) {
					refPoint=board[i].charAt(j);
					if(refPoint!='d' && refPoint==board[i].charAt(j+1) && refPoint==board[i+1].charAt(j) && refPoint==board[i+1].charAt(j+1)) {
						// ������ ���ŵ��� ���� ����̸� ���� �߰�
						if(!isDel[i][j])
							delCnt++;
						if(!isDel[i][j+1])
							delCnt++;
						if(!isDel[i+1][j])
							delCnt++;
						if(!isDel[i+1][j+1])
							delCnt++;
						
						// ���� �����Ƿ� true�� ����
						isDel[i][j]=true;
						isDel[i][j+1]=true;
						isDel[i+1][j]=true;
						isDel[i+1][j+1]=true;
					}
							
				}
			}
			
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					System.out.print(isDel[i][j]+"\t");
				}
				System.out.println();
			}
			
			// 2. ������� ���� �ǿ��� ������ ������ ���Ѵ�.
			answer+=delCnt;	

			// 3. �̹� board���� �ϳ��� �������� �ʾҴٸ� ����
			if(delCnt==0)
				break;
			
			System.out.println(delCnt);
			
			// 4. ���� ���� �����ϱ� ���� ��ĭ�� ä���.
			// ����ִ� ����� d�� ǥ���Ѵ�.
			int falseCnt=0;
			for(int i=0; i<n-1; i++) {
				for(int j=m-1; j>=0; j--) {
					falseCnt=0;
					if(isDel[j][i]) {	// ������ ����� ���,
						falseCnt++;
						
						// true�� �������� ������ ������ ���
						while(j-falseCnt>=0) {
							if(isDel[j-falseCnt][i])
								falseCnt++;
							else
								break;
						}
						
						for(int z=j; z-falseCnt>=0; z--) {	// ������ ������.
							// ���ڸ� ������.
							StringBuilder sb = new StringBuilder(board[z]);
							sb.setCharAt(i, board[z-falseCnt].charAt(i));
							board[z]=sb.toString();
							
							// true�� ������.
							isDel[z][i]=isDel[z-falseCnt][i];
						}
						
						for(int z=0; z<falseCnt; z++) {	// ������ �����ͼ� ���� ���� ĭ�� ó��
							// ������ ���� �� ĭ�� d�� �ٲ۴�.
							StringBuilder sb = new StringBuilder(board[z]);
							sb.setCharAt(i, 'd');
							board[z]=sb.toString();
							
							// ������ ���� �� ĭ�� false�� �ٲ۴�.
							isDel[z][i]=false;
						}
					}
				}
			}
			
			for(int i=0; i<m; i++)
				System.out.println(board[i]);
		}
		
		return answer;
	}
}
