/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 8.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */

package two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 12100 : 2048 (Easy) 게임


	-내가 풀 때 유의점
1. 한칸씩만 비교하는 것이 아니라 끝까지 갈 수 있는지 비교해야 한다.
2. 횟수가 증가할수록, 값은 같거나 더 커진다. 즉, 작아지는 경우는 없기 때문에 최종 횟수에서 모든 경우를 검사하면 된다.(4^5)
3. 만약 작아지는 경우가 있는 문제라면 따로 memoization이 필요할 것이다.
4. dfs인 이유
움직임을 그래프로 표현했을 때,
				상				하				좌				우
			상  하  좌  우		상  하  좌  우		 상  하  좌  우		  상  하  좌  우
		     상하......
		  상하....
		상....
이렇게 되는데, 상상상상상 일 때 board의 최대값, 상상상상하 일 때 board의 최대값 ,.... 을 구해서 그 중에서
최대값을 구하는 방법이기 때문에
dfs로 탐색해야 한다.
5. 블록을 움직이는 연산은 위방향 이동 / 왼쪽방향 이동일 때에만 한다. 아래방향/오른쪽방향은 각각 세로reverse, 가로reverse해서 하/좌와 같이 연산한다.

*/

/*  http://baactree.tistory.com/12
1. 깊이가 최대 5이고 하나의 상태공간에서 상하좌우 4개의 상태공간으로 전이 가능하다. 
    다음 상태공간을 만드는데 O(N2)O(N2)이 걸리므로, O(N245)O(N245) 로 해결 가능하다.
2. 각 행(또는 열)에 대해 0이 아닌 블록들을 모은 후 2개씩 묶어서 합치는게 가능하면 합친다. 
    결과를 다시 해당 열에 복사한다.
3. 구현 팁으로는 상, 하 // 좌, 우는 각각 순서만 반대로 넣으면 되고 세로와 가로는 행과 열만 바꾸어 넣으면 된다.

 * 구현시 유의할 점
- 상, 하, 좌, 우를 따로 구현 하였는데 규칙이 다르게 구현 되어있음.
- 2 0 2 같이 중간에 0이 끼인 경우를 생각하지 않음.
- dfs 한 후에 다시 복귀할 때 원래 상태로 되돌리지 않음.
- max값을 찾는 시점이 잘못됨. ( 최종 depth가 5일 때 찾아야함 )
- (2 2 4) -> (4 4) 와 같이 이미 결합된 블럭은 한 턴에 다시 결합 하지 않는 규칙을 고려하지 않음*/
public class Main {
	static int n;
	static int NONE=0;				// 블록에 숫자가 없는 경우
	static int max=0;				// 결과값(최대 5회 이동으로 만들 수 있는 최대값)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		
		int[][] board=new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				board[i][j]=Integer.parseInt(st.nextToken());;
				if(max<board[i][j])		// 아무것도 움직이지 못할 때에도 max값구하기 위함
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
			// max 값 비교는 cnt가 5일 때만 하면 된다.
			// 이 문제의 경우 값이 더 작아지는 경우는 없기 때문에 5회 이동했을 때 board에 존재하는 값 중
			// 최대값을 구하면 된다.
			// 그리고 여기서 구하는 max중 최초 값은, 가능한 board[][]의 여러 경우의 중 하나의 경우일 때 board중 최대값이다.
			// 하지만 재귀가 반복되면서 기존의 board에서 구한 최대값과, 현재 board에서 구한 최대값을 비교하게 된다.
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					max=Math.max(max, board[i][j]);
			return;
		}
		
		// 각 for문별로 상/하/좌/우에 해당
		// 이렇게 해줌으로써 상상상상상/ 상상상상하 / 상상상상좌 / 상상상상우 이렇게 재귀가 반복된다.
		for(int i=0; i<4; i++) {
			int[][] copyBoard=new int[n][n];
			// 현재의 board 상태 저장
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					copyBoard[j][k]=board[j][k];
					
			// move[i]에 맞게 보드의 블럭들을 움직인다.(상 or 하 or 좌 or 우)
			switch(i) {
			case 1:	// 하. board 배열을 세로로 reverse만 해주고, case 0으로 넘어가서 상과 같은 연산 수행
				reverse(board,0);
			case 0:	// 상
				moveBlock(board, 0);
				break;
			case 3:	// 우
				reverse(board,1);
			case 2:	// 좌
				moveBlock(board, 1);
				break;
			}
			
			// 하,우 인 경우 reverse를 해주어서 board가 바뀌었기 때문에 다시 원래대로 해준다.
			if(i==1)
				reverse(board,0);
			else if(i==3)
				reverse(board,1);
			
			// 움직인 board를 넘겨주어서 재귀 (다음 상태로 dfs)
			solve(cnt+1, board);
			
			// board를 다시 원래 상태로 돌려준다.
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					board[j][k]=copyBoard[j][k];
		}
		
	}
	
	public static void moveBlock(int[][] board, int dir) {	// 0: 상 방향으로 이동, 1:좌 방향으로 이동
		Queue<Integer> q=new LinkedList<Integer>();
		switch(dir) {
		case 0:	// 상
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
		case 1:	// 좌
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
	
	public static void reverse(int[][] board, int dir) {	// 0: 세로reverse, 1:가로reverse
		int tmp=0;
		switch(dir) {
		case 0:		// 세로 reverse
			for(int i=0; i<board[0].length; i++) {
				for(int j=0; j<board.length/2; j++) {	// 배열의 개수가 홀수건 짝수건 board.length/2 만큼 진행하니까 상관없음
					tmp=board[j][i];
					board[j][i]=board[board.length-j-1][i];
					board[board.length-j-1][i]=tmp;
				}
			}
			break;
		case 1:		// 가로 reverse
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

