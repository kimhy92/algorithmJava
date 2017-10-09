/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 8.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 결과 : 통과

public class Main {
	static int n,m,k;
	static int[][] map;
	static int[] command;
	static Location cur=new Location();
	static Dice dice=new Dice();
	
	public static void main(String[] args) throws IOException {	
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		cur.row=Integer.parseInt(st.nextToken());
		cur.col=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		map=new int[n][m];	// map 배열 선언
		command=new int[k];	// 명령의 개수에 맞춰 배열 선언
		
		// Input
		for(int i=0; i<n; i++) {
			StringTokenizer st2=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++)
				map[i][j]=Integer.parseInt(st2.nextToken());
		}
		StringTokenizer st2=new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++)
			command[i]=Integer.parseInt(st2.nextToken());
	
		// Solve
		solve();
	}

	public static void solve() {
		for(int i=0; i<command.length; i++) {
			if(!checkRange(command[i]))	// 이동 후 좌표가 지도 바깥인 경우 아무것도 하지 않는다.
				continue;
			
			// 1:동, 2:서, 3:북, 4:남
			// 방향별로, 지도가 0인 경우->지도값=주사위값 / 지도가0 아닌 경우->주사위값=지도값
			// ex) 동쪽으로 주사위를 굴리면 기존의 주사위에서 right가 밑면이 된다.
			// 단, 방향별 연산(주사위값이나 지도값을 바꾸는 연산)을 한 후에는 주사위를 갱신시켜주어야 한다.
			switch(command[i]) {
			case 1:
				cur.col++;
				if(map[cur.row][cur.col]==0)
					map[cur.row][cur.col]=dice.right;
				else {
					dice.right=map[cur.row][cur.col];
					map[cur.row][cur.col]=0;
				}
				break;
			case 2:
				cur.col--;
				if(map[cur.row][cur.col]==0)
					map[cur.row][cur.col]=dice.left;
				else {
					dice.left=map[cur.row][cur.col];
					map[cur.row][cur.col]=0;
				}
				break;
			case 3:
				cur.row--;
				if(map[cur.row][cur.col]==0)
					map[cur.row][cur.col]=dice.back;
				else {
					dice.back=map[cur.row][cur.col];
					map[cur.row][cur.col]=0;
				}
				break;
			case 4:
				cur.row++;
				if(map[cur.row][cur.col]==0)
					map[cur.row][cur.col]=dice.front;
				else {
					dice.front=map[cur.row][cur.col];
					map[cur.row][cur.col]=0;
				}
				break;
			}
			renewalDice(command[i]);
			System.out.println(dice.top);
		}
	}
	
	public static void renewalDice(int dir) {
		Dice tmp=(Dice)dice.clone();
		
		switch(dir) {
		case 1:
			dice.bottom=tmp.right;
			dice.top=tmp.left;
			dice.right=tmp.top;
			dice.left=tmp.bottom;
			break;
		case 2:
			dice.bottom=tmp.left;
			dice.top=tmp.right;
			dice.right=tmp.bottom;
			dice.left=tmp.top;
			break;
		case 3:
			dice.bottom=tmp.back;
			dice.top=tmp.front;
			dice.front=tmp.bottom;
			dice.back=tmp.top;
			break;
		case 4:
			dice.bottom=tmp.front;
			dice.top=tmp.back;
			dice.front=tmp.top;
			dice.back=tmp.bottom;
			break;
		}
	}
	// 1:동, 2:서, 3:북, 4:남
	// n행 m열
	// 1, 2 -> 이동 후 0 ~ m-1인지 체크
	// 3, 4 -> 이동 후 0 ~ n-1인지 체크
	public static boolean checkRange(int dir) {
		switch(dir) {
		case 1:
			if(cur.col+1>=m)
				return false;
			break;
		case 2:
			if(cur.col-1<0)
				return false;
			break;
		case 3:
			if(cur.row-1<0)
				return false;
			break;
		case 4:
			if(cur.row+1>=n)
				return false;
			break;
		}
		return true;
	}
	
	public static class Location {
		int row, col;

		public Location() {
			super();
		}

		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
	/* 문제에서 주어진 주사위 전개도와 내가 붙인 명칭
	  2					back
	4 1 3		left	top		right
	  5					front
	  6					bottom
	 */
	// 객체 깊은 복사를 위해 Cloneable 구현
	public static class Dice implements Cloneable {
		int top,bottom,left,right,front,back;

		public Dice() {
			super();
		}

		public Dice(int top, int bottom, int left, int right, int front, int back) {
			super();
			this.top = top;
			this.bottom = bottom;
			this.left = left;
			this.right = right;
			this.front = front;
			this.back = back;
		}
		
		protected Object clone() {
			try{
				Dice dice = (Dice)super.clone();
                return dice;
			}
			catch(CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
			return null;
		}
	}
}
