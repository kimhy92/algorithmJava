/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 8.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ��� : ���

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
		map=new int[n][m];	// map �迭 ����
		command=new int[k];	// ����� ������ ���� �迭 ����
		
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
			if(!checkRange(command[i]))	// �̵� �� ��ǥ�� ���� �ٱ��� ��� �ƹ��͵� ���� �ʴ´�.
				continue;
			
			// 1:��, 2:��, 3:��, 4:��
			// ���⺰��, ������ 0�� ���->������=�ֻ����� / ������0 �ƴ� ���->�ֻ�����=������
			// ex) �������� �ֻ����� ������ ������ �ֻ������� right�� �ظ��� �ȴ�.
			// ��, ���⺰ ����(�ֻ������̳� �������� �ٲٴ� ����)�� �� �Ŀ��� �ֻ����� ���Ž����־�� �Ѵ�.
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
	// 1:��, 2:��, 3:��, 4:��
	// n�� m��
	// 1, 2 -> �̵� �� 0 ~ m-1���� üũ
	// 3, 4 -> �̵� �� 0 ~ n-1���� üũ
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
	/* �������� �־��� �ֻ��� �������� ���� ���� ��Ī
	  2					back
	4 1 3		left	top		right
	  5					front
	  6					bottom
	 */
	// ��ü ���� ���縦 ���� Cloneable ����
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
