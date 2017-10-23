/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 21.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package laboratory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Dir {
	int row,col;

	public Dir(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}

public class Solution {
	static int[][] map;
	static int N,M;
	// 상, 하, 좌, 우
	static Dir[] dir={new Dir(-1,0),new Dir(1,0), new Dir(0,-1),new Dir(0,1)};
	static int[] selectedWall=new int[3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s[]=br.readLine().split(" ");
		N=Integer.parseInt(s[0]);
		M=Integer.parseInt(s[1]);
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			s=br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(s[j]);
			}
		}
		
		System.out.println(solve(N*M,3));
	}

	// n : 전체 원소의 개수
	// r : 현재 선택된 벽의 개수
	// 1~64까지의 숫자 중 3개를 고른다.
	// return값 : 현재 map 상태에서 안전영역의 개수
	public static int solve(int n, int r) {
		int res=0;
		int cnt=0;
		int[][] tmpMap=new int[N][M];	// 최초의 map 상태 저장
		
		if(r==0) {	// 하나의 조합이 되었으면 map을 바꾸고 진행
			// map을 임시저장한다. (최초의 map 상태)
			cloneAry(tmpMap,map);
			
			// 선택된 3개를 벽으로 바꾼다.
			for(int i=0; i<3; i++)
				map[selectedWall[i]/M][selectedWall[i]%M]=1;
//				System.out.print(selectedWall[i]+" ");
//			System.out.println();
			
			// moveVirus를 호출한다. 시작점은 바이러스가 있는 위치이다. (바이러스를 퍼지게 한다)
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(map[i][j]==2)
						moveVirus(new Dir(i,j));
			
			// 안전영역의 개수를 센다.
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(map[i][j]==0)
						cnt++;
			
			// 안전영역이 기존의 최대값과 비교하여 더 큰지 확인하고 바꿔준다.
			res=Math.max(res, cnt);
			
			// map을 복구시킨다.
			cloneAry(map,tmpMap);
		}
		else if(n<r) {	// nCr인데 n이 r보다 작은 건 말이 안됨.
			return 0;
		}
		else {
			selectedWall[r-1]=n-1;
			if(map[(n-1)/M][(n-1)%M]==0) {	// 빈 칸인 경우에만 벽으로 바꿀 수 있다.
				res=Math.max(res, solve(n-1,r-1));	// 선택하는 경우
			}
			res=Math.max(res, solve(n-1,r));	// 선택하지 않은 경우
		}
		
		return res;
	}
	
	// 현재 맵 상태에서 dfs를 통해 바이러스를 퍼지게 한다.
	public static void moveVirus(Dir cur) {
		// 상,하,좌,우 각 방향에 대해서 map[][]의 값이 0(빈칸)이라면, 2(바이러스)로 바꾸고 재귀탐색 진행
		for(int i=0; i<dir.length; i++) {
			int nextRow=cur.row+dir[i].row;
			int nextCol=cur.col+dir[i].col;
			if(checkRange(nextRow, nextCol) && map[nextRow][nextCol]==0) {
				map[nextRow][nextCol]=2;
				moveVirus(new Dir(nextRow,nextCol));
			}
		}
	}
	
	// row,col이 배열 인덱스 범위 내의 값인지 확인. 배열 안의 값이면 return true
	public static boolean checkRange(int row, int col) {
		if(row<0||row>=N||col<0||col>=M)
			return false;
		return true;
	}
	// arr2를 arr1에 복사시킨다.
	public static void cloneAry(int[][] arr1, int[][] arr2) {
		for(int i=0; i<arr1.length; i++)
			for(int j=0; j<arr1[i].length; j++)
				arr1[i][j]=arr2[i][j];
	}
}


