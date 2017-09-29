/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 29.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] graph;
	static int[][] res;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		res = new int[n][n];
		visited = new int[n];
		Arrays.fill(visited, 0);
		for(int i=0; i<n; i++) {
			Arrays.fill(res[i], 0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				graph[i][j]=Integer.parseInt(st.nextToken());
		}
		
		solve();
	}
	
	// 문제해결시 유의점
	// 정점 i와 j가 i->j 로 직접 연결된 정점 뿐만 아니라, i->k->j 와 같이 간접적으로 연결된 곳도 고려해야 함
	// 자기 자신으로 다시 올 수 있는지를 고려해야 함 (방향 그래프이기 때문에 자기 자신으로 돌아올 수 있는지. 즉, cycle인지 아닌지) -> 시작점일 때에는 visited[i]를 1로 하지 않으면 해결
	public static void solve() {
		for(int i=0; i<n; i++) {
			Arrays.fill(visited, 0);
			dfsRecur(i, true);
			for(int j=0; j<n; j++)	// 여기가 핵심. 기존의 dfs와 다른 점. i를 기준으로 방문한 모든 곳은 연결되어 있는 점이므로 res[i][j]=visited[j]
				res[i][j]=visited[j];
		}
				
		for(int[] line : res) {
			for(int ele : line)
				System.out.print(ele+" ");
			System.out.println();
		}
	}
	
	public static void dfsRecur(int cur, boolean isStart) {
		if(!isStart)
			visited[cur]=1;
		for(int i=0; i<n; i++)
			if(graph[cur][i]!=0 && visited[i]==0)
				dfsRecur(i, false);
	}
}
