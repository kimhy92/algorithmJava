/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 13.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package test;

public class Main {
	static int[][] board=new int[][]{{1,2,3,4},{4,5,6,7},{7,8,9,10},{11,12,13,14}};
	static int n=4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int i=0;
//		for(i=0; i<5; i++)
//			System.out.println(i);
//		System.out.println(i);
//		System.out.println("=======");
//		for(i=0; i<5; ++i)
//			System.out.println(i);
//		System.out.println(i);
		
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
		
		System.out.println();
		reverse(0);
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
		
		System.out.println();
		reverse(0);
		reverse(1);
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
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
	
	public static void reverse(int[][] board, int dir) {
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
