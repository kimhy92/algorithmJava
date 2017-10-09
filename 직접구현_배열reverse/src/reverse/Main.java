/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 13.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package reverse;

public class Main {

	public static void main(String[] args) {		
		int[][] board=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
		
		System.out.println();
		reverse(board,0);
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
		
		System.out.println();
		reverse(board,0);
		reverse(board,1);
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
	}
	
	public static void reverse(int[][] board, int dir) {
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
