/**
 * @ClassName : Solution
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 17.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package success.deserter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
이 문제를 dfs탐색에 가지치기 활용해서 하려 했는데, 그렇게 하면 안 된다.
우선 가지치기를 하지 않으면 시간제한 초과 발생
가지치기를 할 때 -> 기존에 방문했을 때의 경과시간이 현재 방문할 때의 경과시간보다 작을 때에만 가지치기가 가능한데,
이 걸 체크하는 것은 또 매우 복잡하고 비효율적이며 구현하다 말았다.

bfs로 해야 한다.(Solution2 참조)
bfs로 하면 상태공간트리가 시간단위이니까 나중에 터널을 중복방문해도 시간이 더 걸렸다는게 보장이 된다.
그래서 그 부분을 따로 체크할 필요없이 visit만 알면 된다.

 */
public class Solution {
	/*
	N : 지하 터널 지도의 세로 크기
	M : 가로 크기
	R : 맨홀 뚜껑이 위치한장소의 세로 위치
	C : 가로 위치
	L : 탈출 후 소요된 시간
	 */
	static int N,M,R,C,L;
	static int[][] map;
	static boolean[][] isVisited;	// 방문했는지 체크하는 배열
	static int[][][] road={		// road[i-1][0][0] = 터널 타입이 i일 때, 현재 row,col을 기준으로 움직일 수 있는 값  
			{{-1,0},{1,0},{0,-1},{0,1}},	// 상하좌우
			{{-1,0},{1,0}},					// 상하
			{{0,-1},{0,1}},					// 좌우
			{{-1,0},{0,1}},					// 상우
			{{1,0},{0,1}},					// 하우
			{{1,0},{0,-1}},					// 하좌
			{{-1,0},{0,-1}}// 상좌
	};
	
	
	/* 유의사항
	체크하려는 곳이 map 배열 안의 범위인지 체크, 터널인지 체크, 터널이 서로 연결되어 있는지 체크
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int x=1; x<=tc; x++) {
			String[] s=br.readLine().split(" ");
			
			N=Integer.parseInt(s[0]);
			M=Integer.parseInt(s[1]);
			R=Integer.parseInt(s[2]);
			C=Integer.parseInt(s[3]);
			L=Integer.parseInt(s[4]);
			map=new int[N][M];
			isVisited=new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				s=br.readLine().split(" ");
				for(int j=0; j<M; j++)
					map[i][j]=Integer.parseInt(s[j]);
			}
			
			// 시작 위치. (1시간은 무조건 맨홀이고, 1<=L<=20 이기 때문에, 상태공간트리의 root node는 시작위치의 터널로 잡아도 무방)
			System.out.println("#"+x+" " + solve());
		}
	}
	
	/*
	return : 탈주범이 있을 수 있는 가능한 경로의 수
	 */
	public static int solve() {
		search(1,R,C);	// 1시간 경과시에는 무조건 맨홀 뚜껑이 위치한 곳에 있기 때문에 최초 호출시 매개변수를 이렇게 줘도 무방
		
		// 방문한 터널 개수를 체크해서 리턴
		int cnt=0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(isVisited[i][j]==true)
					cnt++;
		
		return cnt;
	}
	
	/*
	l : 현재까지 소요된 시간
	row : 현재 탐색 중인 터널의 행
	col : 현재 탐색 중인 터널의 열
	isVisited[][] 배열에 경과 시간 내에 탐색한 터널을 체크한다.
	dfs로 탐색하되, 이미 방문했으면 가지치기한다.
	 */
	public static void search(int l, int row, int col) {
		// 기저 사례가 가지치기보다 먼저 와야 한다.
		// 시간이 완료되면 뭐 더 체크할 것도 없이 현재 터널 체크하고 리턴시키면 된다.
		// 기저 사례 : 경과시간이 모두 도달했으면 종료
		// l==L일 때까지는 탐색해야 하므로 비교조건은 l>L로 잡는다.		
		if(l==L) {
			isVisited[row][col]=true;
			return;
		}
		// 기존에 방문했을 때의 경과시간보다 현재의 경과시간이 더 큰 경우에만 끝낸다.
		// dfs 방식이기 때문에 경과시간 체크를 해주지 않으면, 더 큰 시간이 흘러서 방문한 경우도 탐색을 안하게 되서
		// 잘못된 결과가 나올 수 있다.
		if(isVisited[row][col]) {
			return;
		}
		
		isVisited[row][col]=true;
		// 터널인 경우, 그 종류에 따라 재귀호출
		for(int i=0; i<road[map[row][col]-1].length; i++) {
			int nextRow=row+road[map[row][col]-1][i][0];
			int nextCol=col+road[map[row][col]-1][i][1];
			// 터널이 이어져 있으면서, 새로 탐색하려는 곳이 map 배열 안의 범위이고, 터널이면서 서로 연결되어 있는 경우에만 재귀 호출한다.
			if(checkRange(nextRow,nextCol) && isConntedTunnel(map[nextRow][nextCol], nextRow, nextCol, row, col))
				search(l+1,nextRow,nextCol);
		}
	}
	
	public static boolean checkRange(int row, int col) {
		if(row<0 || row>=N || col<0 || col>=M)
			return false;
		return true;
	}
	
	public static boolean isConntedTunnel(int roadType, int nextRow, int nextCol, int row, int col) {
		// 우선, 다음으로 탐색하려는 곳이 터널이 아니라면 return false
		if(roadType<1 || roadType>7)
			return false;
		
		// 터널인 경우에는 서로 연결되어 있는지 확인한다.
		// 1. map[row][col] -> map[nextRow][nextCol] 이 열려있는지,
		// 2. map[nextRow][nextCol] -> map[row][col] 이 열려있는지
		// 이렇게 2개 체크해야 하는데, 1번째 경우는 이미 열려있다고 보장이 된다. (road 배열에 열려있는 경우만 넣었으므로)
		// 따라서 2번째 조건만 검사하면 된다.
		for(int i=0; i<road[roadType-1].length; i++) {
			if(nextRow+road[roadType-1][i][0]==row && nextCol+road[roadType-1][i][1]==col)
				return true;
		}
		
		return false;
	}
}









