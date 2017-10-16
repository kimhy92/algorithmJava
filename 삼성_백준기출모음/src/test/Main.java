/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 14.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2048(Easy) 복습
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
			// tree depth가 5이면 현재 board의 block 중 최대값을 찾아서 기존의 최대값과 비교한다.
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					max=Math.max(max, board[i][j]);
			return;
		}
		
		for(int i=0; i<4; i++) {
			// 현재의 보드 상태 저장
			int[][] copyBoard=new int[n][n];
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					copyBoard[j][k]=board[j][k];
			
			switch(i) {
			case 1:	// 하
				reverse(0);
				
			case 0:	// 상
				moveBlock(0);
				break;
			case 3:	// 우
				reverse(1);
			case 2:	// 좌
				moveBlock(1);
				break;
			}
			// 하/우 인 경우 세로/가로 reverse했으니 다시 원래대로 돌려준다.
			if(i==1)
				reverse(0);
			else if(i==3)
				reverse(1);
			
			// 재귀 호출
			dfs(loopCnt+1);
			
			// board를 원래 상태로 돌린다
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					board[j][k]=copyBoard[j][k];
		}
	}
	
	static void reverse(int dir) {	// block을 역방향으로 바꾸는 함수
		switch(dir) {
		case 0:	// 세로 reverse
			for(int i=0; i<n; i++) {
				for(int j=0; j<n/2; j++) {
					int tmp=board[j][i];
					board[j][i]=board[n-1-j][i];
					board[n-1-j][i]=tmp;
				}
			}
			break;
		case 1:	// 가로 reverse
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
	
	static void moveBlock(int dir) { // block을 움직이는 연산
		Queue<Integer> q=new LinkedList<Integer>();
		switch(dir) {
		case 0:	// 상
			for(int j=0; j<n; j++) {
				// 열 단위로 나눠서 연산
				for(int i=0; i<n; i++) {
					// 해당 열에서 0이 아닌 값들을 큐에 넣고, board를 모두 0으로 만든다.
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
		case 1:	// 좌
			for(int i=0; i<n; i++) {
				// 열 단위로 나눠서 연산
				for(int j=0; j<n; j++) {
					// 해당 열에서 0이 아닌 값들을 큐에 넣고, board를 모두 0으로 만든다.
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













