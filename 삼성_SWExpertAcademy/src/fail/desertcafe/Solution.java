/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : ������, ������, ��������
 */ 

package fail.desertcafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static int[][] desertType;
	static boolean[][] isVisit;
	static boolean[] eatedDesert=new boolean[101];	// ����Ʈ Ÿ���� 1~100
	static int[][] dir={{-1,-1},{-1,1},{1,-1},{1,1}};	// �밢�� ��ǥ��ŭ ������ �࿭��.
	// ������� ���´밢��, ���밢��, ���´밢��, �Ͽ�밢��
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=tc ;i++) {
			N=Integer.parseInt(br.readLine());
			desertType=new int[N][N];
			for(int j=0; j<N; j++) {
				String[] s=br.readLine().split(" ");
				for(int k=0; k<N; k++)
					desertType[j][k]=Integer.parseInt(s[k]);
			}
			
			System.out.println("#"+i+" "+solve());
		}
	}
	
	// return : ����Ʈ�� ���� ���� ���� �� �ִ� ����� ��, ���� �� �ִ� ����Ʈ ���� ����
	// ���� ����Ʈ�� ���� �� ���ٸ� -1�� ����
	public static int solve() {
		int max=-1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// �������� �ٲ�� isVisit ���� ���� (Ȥ�� �ʱ�ȭ�� �ص� ��)
				isVisit=new boolean[N][N];
				// �Ծ ����Ʈ �ʱ�ȭ
				eatedDesert=new boolean[101];
				
				// �𼭸� ���� ������ Ž�� ���ϴϱ� continue
				if( (i==0&&j==0) || (i==0&&j==N-1) || (i==N-1&&j==0) || (i==N-1&&j==N-1) )
					continue;
				
				max=Math.max(max,search(true,true,i,j,i,j,0));
			}
		}
		
		return max;
	}
	
	/*
	�������� �������� ����Ʈ�� ���� ���� ���� �� �ִ� ����� �� ����Ʈ ���� ����
	isStart : ���ʷ� ȣ��� ��쿡�� true�̰� ���ȣ��� false
	startRow : ���� ��, startCol : ���� ��
	row : ���� Ž���ϴ� ��, col : ���� Ž���ϴ� ��
	 */
	public static int search(boolean isStart, boolean isNextStart, int startRow, int startCol, int row, int col, int cafeCnt) {
		// ������� : ���������� ���ƿ� ��� ������� �湮�� ī�� ���� ����
		if(!isStart && (startRow==row) && (startCol==col)) {
			return cafeCnt-1;	// �������� �̹� �湮�ؼ� ������ üũ�����ϱ� cafeCnt-1�� ��������. (���ȣ���� �� +1�ؼ� ȣ�������Ƿ�)
		}
		
		isVisit[row][col]=true;
		eatedDesert[desertType[row][col]]=true;
		int max=-1;	// ī�並 �ϳ��� �湮���� ���ϸ� return -1 ���־�� �ϱ� ������ �ʱⰪ�� -1�� ����
		
		// ��Ʈ��ŷ �Ұ��̱� ������ �湮���¸� ���Ŀ� �����ϱ� ���� �ϴ� �ӽù迭�� ����
		boolean[][] tmpVisit=new boolean[N][N];
		boolean[] tmpEatedDesert=new boolean[101];
		cloneAry(tmpVisit,isVisit);
		cloneAry(tmpEatedDesert,eatedDesert);
		
		for(int i=0; i<dir.length; i++) { 
			// dir �������� Ž��(���ȣ��)�� �ϴµ�,
			// 1. �迭 �ε��� ���� ������ Ȯ��
			// 2. ���� �湮���� �ʾҴ��� Ȯ��. (��, �湮�ߴ��� �������̸� �湮����)
			// 3. �̹� �Ծ ����Ʈ���� Ȯ��
			int nextRow=row+dir[i][0];
			int nextCol=col+dir[i][1];
//			if(checkRange(nextRow,nextCol) && !checkVisited(startRow,startCol,nextRow,nextCol) && !eatedDesert[desertType[nextRow][nextCol]]) {
//				max=Math.max(max, search(false,startRow,startCol,nextRow,nextCol,cafeCnt+1));
//				cloneAry(isVisit,tmpVisit);		// Ž�� �Ŀ��� �湮���¸� �������·� �ٽ� ����
//				cloneAry(eatedDesert,tmpEatedDesert);
//			}
			if(checkRange(nextRow,nextCol) && checkVisited(startRow,startCol,nextRow,nextCol) && !checkEated(startRow,startCol,row,col) && !isNextStart) {
				if(!isStart) {
					max=Math.max(max, search(false,false,startRow,startCol,nextRow,nextCol,cafeCnt+1));
				} else {
					max=Math.max(max, search(false,true,startRow,startCol,nextRow,nextCol,cafeCnt+1));
				}
				cloneAry(isVisit,tmpVisit);		// Ž�� �Ŀ��� �湮���¸� �������·� �ٽ� ����
				cloneAry(eatedDesert,tmpEatedDesert);
			}
		}
		
		return max;
	}
	
	// arr2�� arr1�� ����
	public static void cloneAry(boolean[][] arr1, boolean[][] arr2) {
		for(int i=0; i<arr1.length; i++)
			for(int j=0; j<arr1[i].length; j++)
				arr1[i][j]=arr2[i][j];
	}
	public static void cloneAry(boolean[] arr1, boolean[] arr2) {
		for(int i=0; i<arr1.length; i++)
			arr1[i]=arr2[i];
	}
	
	// Ž���Ϸ��� �࿭�� �迭 �ε��� ���� ������ üũ
	// �迭 �ε��� ���� ���̸� return true, �ƴϸ� return false
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=N)
			return false;
		return true;
	}
	// ���� �湮���� �ʾҴ��� Ȯ��. (��, �湮�ߴ��� �������̸� �湮����)
	// �湮���� �ʾҰų� �������̸� return false.(���� �湮����), �ݴ��� ��쿡�� return true
	public static boolean checkVisited(int startRow, int startCol, int row, int col) {
		if(!isVisit[row][col] || (startRow==row && startCol==col) )
			return false;
		return true;
	}
	
	public static boolean checkEated(int startRow, int startCol, int row, int col) {
		if(!eatedDesert[desertType[row][col]] || (startRow==row && startCol==col))
			return false;
		return true;
	}
}




