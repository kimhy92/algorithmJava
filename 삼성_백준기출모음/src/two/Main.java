/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 8.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */

package two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 12100 : 2048 (Easy) ����


	-���� Ǯ �� ������
1. ��ĭ���� ���ϴ� ���� �ƴ϶� ������ �� �� �ִ��� ���ؾ� �Ѵ�.
2. Ƚ���� �����Ҽ���, ���� ���ų� �� Ŀ����. ��, �۾����� ���� ���� ������ ���� Ƚ������ ��� ��츦 �˻��ϸ� �ȴ�.(4^5)
3. ���� �۾����� ��찡 �ִ� ������� ���� memoization�� �ʿ��� ���̴�.
4. dfs�� ����
�������� �׷����� ǥ������ ��,
				��				��				��				��
			��  ��  ��  ��		��  ��  ��  ��		 ��  ��  ��  ��		  ��  ��  ��  ��
		     ����......
		  ����....
		��....
�̷��� �Ǵµ�, ������ �� �� board�� �ִ밪, ������� �� �� board�� �ִ밪 ,.... �� ���ؼ� �� �߿���
�ִ밪�� ���ϴ� ����̱� ������
dfs�� Ž���ؾ� �Ѵ�.
*/

/*  http://baactree.tistory.com/12
1. ���̰� �ִ� 5�̰� �ϳ��� ���°������� �����¿� 4���� ���°������� ���� �����ϴ�. 
    ���� ���°����� ����µ� O(N2)O(N2)�� �ɸ��Ƿ�, O(N245)O(N245) �� �ذ� �����ϴ�.
2. �� ��(�Ǵ� ��)�� ���� 0�� �ƴ� ��ϵ��� ���� �� 2���� ��� ��ġ�°� �����ϸ� ��ģ��. 
    ����� �ٽ� �ش� ���� �����Ѵ�.
3. ���� �����δ� ��, �� // ��, ��� ���� ������ �ݴ�� ������ �ǰ� ���ο� ���δ� ��� ���� �ٲپ� ������ �ȴ�.

 * ������ ������ ��
- ��, ��, ��, �츦 ���� ���� �Ͽ��µ� ��Ģ�� �ٸ��� ���� �Ǿ�����.
- 2 0 2 ���� �߰��� 0�� ���� ��츦 �������� ����.
- dfs �� �Ŀ� �ٽ� ������ �� ���� ���·� �ǵ����� ����.
- max���� ã�� ������ �߸���. ( ���� depth�� 5�� �� ã�ƾ��� )
- (2 2 4) -> (4 4) �� ���� �̹� ���յ� ���� �� �Ͽ� �ٽ� ���� ���� �ʴ� ��Ģ�� ������� ����*/
public class Main {
	static int n;
	static int NONE=0;				// ��Ͽ� ���ڰ� ���� ���
	static int max=0;				// �����(�ִ� 5ȸ �̵����� ���� �� �ִ� �ִ밪)
	static int[][] move={{-1,0},{1,0},{0,-1},{0,1}};	//��,��,��,��
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		
		int[][] board=new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());;
				if(max<board[i][j])		// �ƹ��͵� �������� ���� ������ max�����ϱ� ����
					max=board[i][j];
			}
		}
		
		System.out.println(max);
	}
	
	public static void solve(int cnt, int[][] board) {
		if(cnt==5) {
			// max �� �񱳴� cnt�� 5�� ���� �ϸ� �ȴ�.
			// �� ������ ��� ���� �� �۾����� ���� ���� ������ 5ȸ �̵����� �� board�� �����ϴ� �� ��
			// �ִ밪�� ���ϸ� �ȴ�.
			// �׸��� ���⼭ ���ϴ� max�� ���� ����, ������ board[][]�� ���� ����� �� �ϳ��� ����� �� board�� �ִ밪�̴�.
			// ������ ��Ͱ� �ݺ��Ǹ鼭 ������ board���� ���� �ִ밪��, ���� board���� ���� �ִ밪�� ���ϰ� �ȴ�.
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					max=Math.max(max, board[i][j]);
			return;
		}
		
		// �� for������ ��/��/��/�쿡 �ش�
		// �̷��� �������ν� ������/ ������� / ������� / ������ �̷��� ��Ͱ� �ݺ��ȴ�.
		for(int i=0; i<move.length; i++) {
			// move[i]�� �°� ������ ������ �����δ�.(�� or �� or �� or ��)
			switch(i) {
			case 1:	// ��. board �迭�� ���η� reverse�� ���ְ�, case 0���� �Ѿ�� ��� ���� ���� ����
				reverse(board,0);
			case 0:	// ��
				moveBlock(board, 0);
				break;
			case 3:	// ��
				reverse(board,1);
			case 2:	// ��
				moveBlock(board, 1);
				break;
			}
			
			// ��,�� �� ��� reverse�� ���־ board�� �ٲ���� ������ �ٽ� ������� ���ش�.
			if(i==1)
				reverse(board,0);
			else if(i==3)
				reverse(board,1);
			
			// ������ board�� �Ѱ��־ ��� (���� ���·� dfs)
			solve(cnt+1, board);
		}
	}
	
	public static void moveBlock(int[][] board, int dir) {	// 0: �� �������� �̵�, 1:�� �������� �̵�
		switch(dir) {
		case 0:
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length; j++) {
					
				}
			}
			break;
		case 1:
			
			break;
		}
	}
	
	public static void reverse(int[][] board, int dir) {	// 0: ����reverse, 1:����reverse
		int tmp=0;
		switch(dir) {
		case 0:		// ���� reverse
			for(int i=0; i<board[0].length; i++) {
				for(int j=0; j<board.length/2; j++) {	// �迭�� ������ Ȧ���� ¦���� board.length/2 ��ŭ �����ϴϱ� �������
					tmp=board[j][i];
					board[j][i]=board[board.length-j-1][i];
					board[board.length-j-1][i]=tmp;
				}
			}
			break;
		case 1:		// ���� reverse
			for(int i=0; i<board.length; i++) {
				for(int j=0; j<board[i].length/2; j++) {
					tmp=board[i][j];
					board[i][j]=board[i][board[i].length-j-1];
					board[i][board[i].length-j-1]=tmp;
				}
			}
			break;
		}
	}	
}

