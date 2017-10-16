/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 14.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2048(Easy) ����
public class Main {
	static int n;
	static int[][] board;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		board=new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());
				max=Math.max(max, board[i][j]);
			}
		}
		
		if(n==1) {
			System.out.println(Math.max(0, board[0][0]));
			return;
		}
		
		dfs(0);
		System.out.println(max);
	}
	
	static void dfs(int loopCnt) {
		if(loopCnt==5) {
			// tree depth�� 5�̸� ���� board�� block �� �ִ밪�� ã�Ƽ� ������ �ִ밪�� ���Ѵ�.
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					max=Math.max(max, board[i][j]);
			return;
		}
		
		for(int i=0; i<4; i++) {
			// ������ ���� ���� ����
			int[][] copyBoard=new int[n][n];
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					copyBoard[j][k]=board[j][k];
			
			switch(i) {
			case 1:	// ��
				reverse(0);
				
			case 0:	// ��
				moveBlock(0);
				break;
			case 3:	// ��
				reverse(1);
			case 2:	// ��
				moveBlock(1);
				break;
			}
			// ��/�� �� ��� ����/���� reverse������ �ٽ� ������� �����ش�.
			if(i==1)
				reverse(0);
			else if(i==3)
				reverse(1);
			
			// ��� ȣ��
			dfs(loopCnt+1);
			
			// board�� ���� ���·� ������
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					board[j][k]=copyBoard[j][k];
		}
	}
	
	static void reverse(int dir) {	// block�� ���������� �ٲٴ� �Լ�
		switch(dir) {
		case 0:	// ���� reverse
			for(int i=0; i<n; i++) {
				for(int j=0; j<n/2; j++) {
					int tmp=board[j][i];
					board[j][i]=board[n-1-j][i];
					board[n-1-j][i]=tmp;
				}
			}
			break;
		case 1:	// ���� reverse
			for(int i=0; i<n; i++) {
				for(int j=0; j<n/2; j++) {
					int tmp=board[i][j];
					board[i][j]=board[i][n-1-j];
					board[i][n-1-j]=tmp;
				}
			}
			break;
		}
	}
	
	static void moveBlock(int dir) { // block�� �����̴� ����
		Queue<Integer> q=new LinkedList<Integer>();
		switch(dir) {
		case 0:	// ��
			for(int j=0; j<n; j++) {
				// �� ������ ������ ����
				for(int i=0; i<n; i++) {
					// �ش� ������ 0�� �ƴ� ������ ť�� �ְ�, board�� ��� 0���� �����.
					if(board[i][j]!=0) {
						q.add(board[i][j]);
						board[i][j]=0;
					}
				}
				
				int idx=0;
				int popData=0;
				while(!q.isEmpty()) {
					popData=q.poll();
					if(board[idx][j]==0) {
						board[idx][j]=popData;
					}
					else if(board[idx][j]==popData) {
						board[idx][j]*=2;
						idx++;
					}
					else {
						board[++idx][j]=popData;
					}
				}
			}
			break;
		case 1:	// ��
			for(int i=0; i<n; i++) {
				// �� ������ ������ ����
				for(int j=0; j<n; j++) {
					// �ش� ������ 0�� �ƴ� ������ ť�� �ְ�, board�� ��� 0���� �����.
					if(board[i][j]!=0) {
						q.add(board[i][j]);
						board[i][j]=0;
					}
				}
				
				int idx=0;
				int popData=0;
				while(!q.isEmpty()) {
					popData=q.poll();
					if(board[i][idx]==0) {
						board[i][idx]=popData;
					}
					else if(board[i][idx]==popData) {
						board[i][idx]*=2;
						idx++;
					}
					else {
						board[i][++idx]=popData;
					}
				}
			}
			break;
		}
	}
}













