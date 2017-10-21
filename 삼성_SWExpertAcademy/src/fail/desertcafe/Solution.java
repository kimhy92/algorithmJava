/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package fail.desertcafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static int[][] desertType;
	static boolean[][] isVisit;
	static boolean[] eatedDesert=new boolean[101];	// 디저트 타입은 1~100
	static int[][] dir={{-1,-1},{-1,1},{1,-1},{1,1}};	// 대각선 좌표만큼 떨어진 행열값.
	// 순서대로 상좌대각선, 상우대각선, 하좌대각선, 하우대각선
	
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
	
	// return : 디저트를 가장 많이 먹을 수 있는 경우일 때, 먹을 수 있는 디저트 수를 리턴
	// 만약 디저트를 먹을 수 없다면 -1을 리턴
	public static int solve() {
		int max=-1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 기준점이 바뀌면 isVisit 새로 생성 (혹은 초기화로 해도 됨)
				isVisit=new boolean[N][N];
				// 먹어본 디저트 초기화
				eatedDesert=new boolean[101];
				
				// 모서리 점일 때에는 탐색 못하니까 continue
				if( (i==0&&j==0) || (i==0&&j==N-1) || (i==N-1&&j==0) || (i==N-1&&j==N-1) )
					continue;
				
				max=Math.max(max,search(true,true,i,j,i,j,0));
			}
		}
		
		return max;
	}
	
	/*
	시작점을 기준으로 디저트를 가장 많이 먹을 수 있는 경우의 그 디저트 수를 리턴
	isStart : 최초로 호출된 경우에만 true이고 재귀호출시 false
	startRow : 시작 행, startCol : 시작 열
	row : 현재 탐색하는 행, col : 현재 탐색하는 열
	 */
	public static int search(boolean isStart, boolean isNextStart, int startRow, int startCol, int row, int col, int cafeCnt) {
		// 기저사례 : 시작점으로 돌아온 경우 현재까지 방문한 카페 수를 리턴
		if(!isStart && (startRow==row) && (startCol==col)) {
			return cafeCnt-1;	// 시작점은 이미 방문해서 개수로 체크했으니까 cafeCnt-1을 리턴해줌. (재귀호출할 때 +1해서 호출했으므로)
		}
		
		isVisit[row][col]=true;
		eatedDesert[desertType[row][col]]=true;
		int max=-1;	// 카페를 하나도 방문하지 못하면 return -1 해주어야 하기 때문에 초기값을 -1로 설정
		
		// 백트래킹 할것이기 때문에 방문상태를 이후에 복구하기 위해 일단 임시배열에 저장
		boolean[][] tmpVisit=new boolean[N][N];
		boolean[] tmpEatedDesert=new boolean[101];
		cloneAry(tmpVisit,isVisit);
		cloneAry(tmpEatedDesert,eatedDesert);
		
		for(int i=0; i<dir.length; i++) { 
			// dir 방향으로 탐색(재귀호출)을 하는데,
			// 1. 배열 인덱스 안의 값인지 확인
			// 2. 아직 방문하지 않았는지 확인. (단, 방문했더라도 시작점이면 방문가능)
			// 3. 이미 먹어본 디저트인지 확인
			int nextRow=row+dir[i][0];
			int nextCol=col+dir[i][1];
//			if(checkRange(nextRow,nextCol) && !checkVisited(startRow,startCol,nextRow,nextCol) && !eatedDesert[desertType[nextRow][nextCol]]) {
//				max=Math.max(max, search(false,startRow,startCol,nextRow,nextCol,cafeCnt+1));
//				cloneAry(isVisit,tmpVisit);		// 탐색 후에는 방문상태를 이전상태로 다시 복구
//				cloneAry(eatedDesert,tmpEatedDesert);
//			}
			if(checkRange(nextRow,nextCol) && checkVisited(startRow,startCol,nextRow,nextCol) && !checkEated(startRow,startCol,row,col) && !isNextStart) {
				if(!isStart) {
					max=Math.max(max, search(false,false,startRow,startCol,nextRow,nextCol,cafeCnt+1));
				} else {
					max=Math.max(max, search(false,true,startRow,startCol,nextRow,nextCol,cafeCnt+1));
				}
				cloneAry(isVisit,tmpVisit);		// 탐색 후에는 방문상태를 이전상태로 다시 복구
				cloneAry(eatedDesert,tmpEatedDesert);
			}
		}
		
		return max;
	}
	
	// arr2를 arr1에 복사
	public static void cloneAry(boolean[][] arr1, boolean[][] arr2) {
		for(int i=0; i<arr1.length; i++)
			for(int j=0; j<arr1[i].length; j++)
				arr1[i][j]=arr2[i][j];
	}
	public static void cloneAry(boolean[] arr1, boolean[] arr2) {
		for(int i=0; i<arr1.length; i++)
			arr1[i]=arr2[i];
	}
	
	// 탐색하려는 행열이 배열 인덱스 안의 값인지 체크
	// 배열 인덱스 안의 값이면 return true, 아니면 return false
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=N)
			return false;
		return true;
	}
	// 아직 방문하지 않았는지 확인. (단, 방문했더라도 시작점이면 방문가능)
	// 방문하지 않았거나 시작점이면 return false.(이후 방문가능), 반대의 경우에는 return true
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




