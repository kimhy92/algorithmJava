/**
 * @ClassName : Solution2
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 18.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package success.deserter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
이 문제를 처음에는 dfs탐색에 가지치기 활용해서 하려 했는데, 그렇게 하면 안 된다. (Solution.java -> dfs로 풀다가 너무 복잡한 걸 알고 구현은 안했음. 이해는 했음)
우선 가지치기를 하지 않으면 시간제한 초과 발생
가지치기를 할 때 -> 기존에 방문했을 때의 경과시간이 현재 방문할 때의 경과시간보다 작을 때에만 가지치기가 가능한데,
이 걸 체크하는 것은 또 매우 복잡하고 비효율적이며 구현하다 말았다.

bfs로 해야 한다.(Solution2 참조)
bfs로 하면 상태공간트리가 시간단위이니까 나중에 터널을 중복방문해도 시간이 더 걸렸다는게 보장이 된다.
그래서 그 부분을 따로 체크할 필요없이 visit만 알면 된다.

dfs와 bfs를 통해 완전탐색을 하는 경우 -> 뭘 쓰던 수행시간 똑같으니 상관없을 수 있다.
하지만 가지치기를 한다? -> dfs와 bfs의 가지치기 조건이 달라질 수 있다. 더 적절한 걸 써야 한다.
 */

class Location {
	int row, col, l;

	public Location(int row, int col, int l) {
		super();
		this.row = row;
		this.col = col;
		this.l = l;
	}
}

public class Solution2 {
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
		// 방문한 터널 개수를 체크해서 리턴
		int cnt=0;
		
		Queue<Location> q=new LinkedList<Location>();
		
		q.offer(new Location(R,C, 1));	// 시작 위치를 넣는다.
		Location pop=null;
		while(!q.isEmpty()) {
			pop=q.poll();
			if(pop.l>L)
				break;
			
			if(isVisited[pop.row][pop.col])
				continue;
			
			isVisited[pop.row][pop.col]=true;
			cnt++;
			
			for(int i=0; i<road[map[pop.row][pop.col]-1].length; i++) {
				int nextRow=pop.row+road[map[pop.row][pop.col]-1][i][0];
				int nextCol=pop.col+road[map[pop.row][pop.col]-1][i][1];
				// 터널이 이어져 있으면서, 새로 탐색하려는 곳이 map 배열 안의 범위이고, 터널이면서 서로 연결되어 있는 경우에 큐에 넣는다.
				if(checkRange(nextRow,nextCol) && isConntedTunnel(map[nextRow][nextCol], nextRow, nextCol, pop.row, pop.col) && !isVisited[nextRow][nextCol]) {
					q.offer(new Location(nextRow,nextCol, pop.l+1));
				}
			}
		}
		
		return cnt;
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
