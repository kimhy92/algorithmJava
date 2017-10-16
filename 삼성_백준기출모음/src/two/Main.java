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
import java.util.LinkedList;
import java.util.Queue;
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
5. ����� �����̴� ������ ������ �̵� / ���ʹ��� �̵��� ������ �Ѵ�. �Ʒ�����/�����ʹ����� ���� ����reverse, ����reverse�ؼ� ��/�¿� ���� �����Ѵ�.

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
		
		if(n==1) {
			System.out.println(Math.max(0, board[0][0]));
			return;
		}
		
		solve(0,board);
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
		for(int i=0; i<4; i++) {
			int[][] copyBoard=new int[n][n];
			// ������ board ���� ����
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					copyBoard[j][k]=board[j][k];
					
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
			
			// board�� �ٽ� ���� ���·� �����ش�.
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					board[j][k]=copyBoard[j][k];
		}
		
	}
	
	public static void moveBlock(int[][] board, int dir) {	// 0: �� �������� �̵�, 1:�� �������� �̵�
		Queue<Integer> q=new LinkedList<Integer>();
		switch(dir) {
		case 0:	// ��
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					if(board[j][i]!=0)
						q.add(board[j][i]);
					board[j][i]=0;
				}
	 
				int idx=0;
				int popData;
	 
				while(!q.isEmpty()){
					popData = q.poll();
	 
					if(board[idx][i]==0)
						board[idx][i]=popData;
					else if(board[idx][i]==popData){
						board[idx][i]*=2;
						idx++;
					}
					else
						board[++idx][i]=popData;
				}
			}
			break;
		case 1:	// ��
			for (int i=0; i<n; i++){
				for (int j=0; j<n; j++){
					if (board[i][j]!=0)
						q.add(board[i][j]);
					board[i][j]=0;
				}
	 
				int idx=0;
				int popData;
	 
				while(!q.isEmpty()){
					popData=q.poll();
	                
					if(board[i][idx]==0)
						board[i][idx]=popData;
					else if(board[i][idx]==popData){
						board[i][idx]*=2;
						idx++;
					}
					else
						board[i][++idx]=popData;
				}
			}
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


/*
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int max = 0;
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		int[][] map = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, map);

		System.out.println(max);

	}

	private static void dfs(int index, int[][] map) {
		
		if (index == 5) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					max = Math.max(max, map[i][j]);
				}
			}
			return;
		}

		int[][] saved = new int[n][n];
		// left
		for (int i = 0; i < n; i++) {
			int cur = 0;
			int temp = 0;
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0) {
					if(temp==0){
						temp = map[i][j];
					}else{
						if(temp==map[i][j]){
							saved[i][cur++] = 2*temp;
							temp = 0;
						}else{
							saved[i][cur++] = temp;
							temp = map[i][j];
						}
					}
					
				}
			}
			if(temp!=0){
				saved[i][cur++] = temp;
			}

		}
		
		dfs(index + 1, saved);

		saved = new int[n][n];
		//right
		for (int i = 0; i < n; i++) {
			int cur = n-1;
			int temp = 0;
			for (int j = n-1; j >=0; j--) {
				if (map[i][j] != 0) {
					if(temp==0){
						temp = map[i][j];
					}else{
						if(temp==map[i][j]){
							saved[i][cur--] = 2*temp;
							temp = 0;
						}else{
							saved[i][cur--] = temp;
							temp = map[i][j];
						}
					}
					
				}
			}
			if(temp!=0){
				saved[i][cur] = temp;
			}

		}
	
		dfs(index + 1, saved);

		saved = new int[n][n];
		// down
		for (int i = 0; i < n; i++) {
			int cur = n-1;
			int temp = 0;
			for (int j = n-1; j >=0; j--) {
				if (map[j][i] != 0) {
					if(temp==0){
						temp = map[j][i];
					}else{
						if(temp==map[j][i]){
							saved[cur--][i] = 2*temp;
							temp = 0;
						}else{
							saved[cur--][i] = temp;
							temp = map[j][i];
						}
					}
					
				}
			}
			if(temp!=0){
				saved[cur][i] = temp;
			}

		}
		
		dfs(index + 1, saved);

		saved = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			int cur = 0;
			int temp = 0;
			for (int j = 0; j < n; j++) {
				if (map[j][i] != 0) {
					if(temp==0){
						temp = map[j][i];
					}else{
						if(temp==map[j][i]){
							saved[cur++][i] = 2*temp;
							temp = 0;
						}else{
							saved[cur++][i] = temp;
							temp = map[j][i];
						}
					}
					
				}
			}
			if(temp!=0){
				saved[cur++][i] = temp;
			}

		}

		dfs(index + 1, saved);

	}

}

 * 
 */

