/**
 * @ClassName : DFS
 * @Author : Kim HyunYong
 * @Date : 2017. 10. 8.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package dfsbfs;

public class DFSBFS {
	static int[] visited=new int[1<<10];	//1024개
	static int n=100;
	static int[][] graph=new int[n][n];	// 인접 행렬 그래프
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// stack으로도 구현 가능한데 좀 이상했음 (시작점 s와 인접한 정점 a, b가 있을 때 a로부터 dfs를 했는데 ,
	// s-a-c-f-b 로 연결되는 sub tree가 있다고 치자. b는 dfs에 의해서 f에 연결된 걸로 탐색된 것이지만
	// b는 시작정점 s와도 인접하다.
	// 이 때 stack으로 하면 push할 때 visited를 1로 하는데 그러면 s-a-c-f-b가 아니라 s-a-c-f만 탐색하게 된다.
	// 재귀로 하면 s-a-c-f-b가 탐색된다.
	public static void dfs(int cur){ 
		visited[cur]=1;
		System.out.print(cur+ " ");
		for(int i=0; i<n; i++) {
			if(graph[cur][i]==1 && visited[i]==0) {
				dfs(i);
				System.out.println();	// 새로운 연결그래프 탐색시 줄바꿈
			}
		}
	} 
	
	// bfs는 queue로 구현
	
}
