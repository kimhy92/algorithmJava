/**
 * @ClassName : Main
 * @Author : Kim HyunYong
 * @Date : 2017. 9. 28.
 * @Description : -
 * @Version : 1.0
 * @History : 수정일, 수정자, 수정내용
 */ 

package baekjoon.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m,start;
	static int[][] graph;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		start=Integer.parseInt(st.nextToken());
		
		graph = new int[n+1][n+1];	// index 1부터 사용
		visited = new int[n+1];
		
		// init
		for(int i=0; i<=n; i++) {
			Arrays.fill(graph[i], 0);
			visited[i]=0;
		}
		
		// input
		for(int i=0; i<m; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			graph[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())]=1;
		}
		
		// solve
		dfs();
		for(int i=0; i<=n; i++)
			visited[i]=0;
		bfs();
	}
	
	// back tracking 쓰지 않고 바로 출력. back tracking 쓰려면 이전 정점이 어디인지를 저장할 메모리 필요
	public static void dfs() {
		for(int i=1; i<=n; i++)
			if(visited[i]==0)
				dfsRecur(i);
		System.out.println();
	}
	
	public static void dfsRecur(int cur) {
		visited[cur]=1;
		System.out.print(cur+" ");
		for(int i=1; i<=n; i++)
			if(graph[cur][i]!=0 && visited[i]==0)
				dfsRecur(i);
	}
	
	// back tracking 쓰지 않고 바로 출력. back tracking 쓰려면 이전 정점이 어디인지를 저장할 메모리 필요
	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int popData=0;
		q.add(start);
		visited[start]=1;
		
		while(!q.isEmpty()) {
			popData=q.poll();
			
			System.out.print(popData+" ");
			for(int i=1; i<=n; i++) {
				if(graph[popData][i]!=0 && visited[i]==0) {
					q.add(i);
					visited[i]=1;
				}
			}
		}
	}
}

/* non_recursive
// back tracking 쓰지 않고 바로 출력. back tracking 쓰려면 이전 정점이 어디인지를 저장할 메모리 필요
	public static void dfs() {
		Stack<Integer> st = new Stack<Integer>();
		int popData=0;
		st.push(start);
	
		while(!st.isEmpty()) {
			popData=st.pop();
			
			// push할 때 visited를 체크할 때의 문제점
			// 예제의 경우에,
			// 1과 2,4,3은 각각 인접한 정점이여서 push할 때 체크가 된다.
			// 하지만 4의 경우, 1과 인접하기도 하지만 2와도 인접하기 때문에
			// 1다음에 접근하는 것이 아니라 2 다음 접근해야 한다. (dfs이기 때문에)
			// 그런데 push 할 때 체크해버리면 1 2 4 3 으로 방문하는 것이 아니라 1 2 3 4 로 되어 버린다.
			// 따라서 dfs에서는 pop할 때 visited를 바꾸어주고,
			// bfs는 enqueue할 때 visited를 바꾸어 주면 될 듯
			if(visited[popData]==0)
				visited[popData]=1;
			else
				continue;
			
			System.out.print(popData+" ");
			// 인접한 정점이 여러 개인 경우, 더 값이 작은 정점부터 방문
			// dfs의 경우 작은 정점부터 방문하려면, pop이 되는 것이 더 작은 값이어야 하므로 큰 숫자부터 검사한다.
			for(int i=n; i>=1; i--) {
				if(graph[popData][i]!=0 && visited[i]==0)
					st.push(i);
			}
		}
		System.out.println();
	}


*/